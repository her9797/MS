package com.ms.back.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KafkaStreamsTest {
    private TopologyTestDriver testDriver;
    private TestInputTopic<String, String> inputTopic;
    private TestOutputTopic<String, String> outputTopic;

    @BeforeEach
    public void setUp() {
        // Stream configuration
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-uppercase-stream-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> input = builder.stream("input-topic");

        // 데이터 스트림 값 변경 / input-topic 대문자 변환
        KStream<String, String> uppercased = input.mapValues(value -> value.toUpperCase());

        uppercased.to("output-topic", Produced.with(Serdes.String(), Serdes.String()));

        Topology topology = builder.build();
        testDriver = new TopologyTestDriver(topology, props);
        inputTopic = testDriver.createInputTopic("input-topic", Serdes.String().serializer(), Serdes.String().serializer());
        outputTopic = testDriver.createOutputTopic("output-topic", Serdes.String().deserializer(), Serdes.String().deserializer());
    }

    @Test
    public void streamsTest() {

        // 내가 이렇게 인풋했다고 가정
        inputTopic.pipeInput("key1", "value1");
        inputTopic.pipeInput("key2", "value2");

        // 보여지는건 다음과 같음
        assertEquals(new KeyValue<>("key1", "VALUE1"), outputTopic.readKeyValue());
        assertEquals(new KeyValue<>("key2", "VALUE2"), outputTopic.readKeyValue());
    }

    @AfterEach
    public void tearDown() {
        if (testDriver != null) {
            testDriver.close();
        }
    }

}
