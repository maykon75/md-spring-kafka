package com.kafka.project.infrastructure.kafka;

import com.kafka.project.api.dto.CustomerRequestDTO;
import com.kafka.project.domain.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final CustomerService customerService;

    public KafkaConsumer(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @KafkaListener(topics = "${kafka.topic.customer}",
                    groupId = "${kafka.consumer.group-id}")
    public void listen(CustomerRequestDTO customerRequestDTO){
        log.info("Listen {}", customerRequestDTO);
        customerService.post(customerRequestDTO);
    }
}
