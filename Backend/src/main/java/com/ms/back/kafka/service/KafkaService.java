package com.ms.back.kafka.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String createKafka() {

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("test1", "테스트입니다");

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.info("Error msg : {}", ex.getMessage());
            } else {
                log.info("partition: {}, offset : {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        });
        return "{}";
    }
}
