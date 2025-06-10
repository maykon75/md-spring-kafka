package com.kafka.project.api.controller;


import com.kafka.project.api.dto.CustomerRequestDTO;
import com.kafka.project.api.dto.CustomerResponseDTO;
import com.kafka.project.domain.service.CustomerService;
import com.kafka.project.infrastructure.kafka.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/kafka")
public class CustomerController {

    private final CustomerService customerService;
    private final KafkaProducer kafkaProducer;

    public CustomerController(final CustomerService customerService, final KafkaProducer kafkaProducer) {
        this.customerService = customerService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDTO>> getAll(){
        return ResponseEntity.ok().body(customerService.getAll());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> post(@RequestBody CustomerRequestDTO customerRequestDTO){

        kafkaProducer.execute(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
