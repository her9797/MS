//package com.ms.back.kafka.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ms.back.chatting.dto.MsgDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class KafkaProducerService {
//
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private final ObjectMapper objectMapper;
//
//    @Autowired
//    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.objectMapper = objectMapper;
//    }
//
//    /** 카프카를 이용한 메시지 전송 */
//    public void sendMessage(MsgDTO msgDTO) {
//
//        try {
//            // MsgDTO 객체를 JSON 문자열로 변환
//            String messageJson = objectMapper.writeValueAsString(msgDTO);
//            // Kafka에 JSON 메시지 전송
//            kafkaTemplate.send(msgDTO.getTopic(), messageJson);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}
