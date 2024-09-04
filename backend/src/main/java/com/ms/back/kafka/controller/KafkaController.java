package com.ms.back.kafka.controller;

import com.ms.back.chatting.dto.MsgDTO;
import com.ms.back.kafka.service.KafkaProducerService;
import com.ms.back.kafka.service.KafkaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaController(KafkaService kafkaService, KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/messages")
    public String createKafka(@RequestBody @Valid MsgDTO messageDTO) {

        // KafkaProducerService를 사용하여 메시지를 Kafka에 전송
        kafkaProducerService.sendMessage(messageDTO);
        return "Message sent to Kafka topic: " + messageDTO.getTopic();
    }

}
