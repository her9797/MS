package com.ms.back.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.back.chatting.dto.MsgDTO;
import com.ms.back.chatting.entity.Message;
import com.ms.back.chatting.repository.MsgRepository;
import com.ms.back.chatting.webSocket.handler.SocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KafkaConsumerService {

    private final MsgRepository msgRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final SocketHandler socketHandler;

    @Autowired
    public KafkaConsumerService(MsgRepository msgRepository, ModelMapper modelMapper, ObjectMapper objectMapper, SocketHandler socketHandler) {
        this.msgRepository = msgRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.socketHandler = socketHandler;
    }

    private final List<MsgDTO> messageBatch = new ArrayList<>();
    private final int BATCH_SIZE = 100; // 배치 크기 설정

    /** Kafka에서 메시지를 읽어오는 메서드 */
    @KafkaListener(topics = "test1", groupId = "my-group")
    public void listen(String message) {
        log.info("Received message: {}", message);  // 메시지 로그 추가
        try {
            // String 메시지를 MsgDTO로 변환
            MsgDTO msgDTO = objectMapper.readValue(message, MsgDTO.class);
            synchronized (messageBatch) {
                messageBatch.add(msgDTO);
                // 비동기적으로 배치 작업 실행
                if (messageBatch.size() >= BATCH_SIZE) {
                    // 배치 크기에 도달하면 배치 작업을 비동기적으로 실행
                    CompletableFuture.runAsync(this::insertMessageByBatch);
                }
            }
            // 메시지를 웹소켓을 통해 클라이언트로 전송
            socketHandler.sendMessageToClients(message);
        } catch (IOException e) {
            log.error("Failed to parse message: {}", message, e);
        }
    }

    /** 배치 작업 메서드 */
    @Scheduled(fixedRate = 60000) // 5분마다 배치 저장
    public void insertMessageByBatch() {
        List<MsgDTO> batchToSave;
        synchronized (messageBatch) {
            if (messageBatch.isEmpty()) {
                log.info("No messages to save in batch.");
                return;
            }
            batchToSave = new ArrayList<>(messageBatch);
            messageBatch.clear();
        }
        log.info("Saving batch of size: {}", batchToSave.size());

        // 중복 메시지 제거
        Map<String, MsgDTO> uniqueMessages = batchToSave.stream()
                .collect(Collectors.toMap(
                        MsgDTO::getMsgContent, // 중복 기준: 메시지 내용
                        dto -> dto,
                        (existing, replacement) -> existing // 중복 메시지 처리: 기존 메시지를 유지
                ));

        List<MsgDTO> deduplicatedMessages = new ArrayList<>(uniqueMessages.values());

        // MsgDTO를 Message 객체로 변환
        List<Message> messages = deduplicatedMessages.stream()
                .map(dto -> modelMapper.map(dto, Message.class))
                .collect(Collectors.toList());

        try {
            // 트랜잭션을 사용하여 메시지를 저장
            msgRepository.saveAll(messages);
            log.info("Batch of size {} saved successfully.", messages.size());
        } catch (Exception e) {
            log.error("Failed to save batch of size {}", messages.size(), e);
            // 필요 시 재시도 로직 추가
        }
    }



}
