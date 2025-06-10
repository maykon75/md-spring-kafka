package com.kafka.project.infrastructure.kafka;

import com.kafka.project.api.dto.CustomerRequestDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

@Component
public class KafkaProducer {
    private static Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${kafka.topic.customer}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void execute(CustomerRequestDTO payload) {
        log.info("Send {}", payload);
        kafkaTemplate.send(topic, payload);
    }
}
