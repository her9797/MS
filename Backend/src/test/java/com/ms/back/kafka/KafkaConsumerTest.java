package com.ms.back.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KafkaConsumerTest {

    private Producer producer;
    private Consumer consumer;

    @BeforeEach
    public void setUp() {

        // Kafka 서버가 실행 중이어야만 함 (CMD에서 따로 서버 올림)
        producer = new Producer("localhost:9092", "test");
        consumer = new Consumer("localhost:9092", "test-group", "test");
    }

    @Test
    @DisplayName("Kafka TEST")
    public void testProduceAndConsumeMessage() {

        // 메시지 전송
        producer.sendMessage("key", "value");

        // 메시지 수신
        ConsumerRecord<String, String> record = consumer.pollMessage();

        // 검증
        assertEquals("key", record.key());
        assertEquals("value", record.value());
    }

    @AfterEach
    public void tearDown() {
        if (producer != null) {
            producer.close();
        }
        if (consumer != null) {
            consumer.close();
        }
    }

}
