package com.kafka.project.domain.service;

import com.kafka.project.api.dto.CustomerRequestDTO;
import com.kafka.project.api.dto.CustomerResponseDTO;
import com.kafka.project.api.mapper.CustomerMapper;
import com.kafka.project.infrastructure.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public CustomerService(final CustomerRepository repository, final CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CustomerResponseDTO> getAll() {
        return mapper.toDtoList(repository.findAll());
    }


    public void post(CustomerRequestDTO requestDto) {
        log.info("Sending {} to database", requestDto);
        repository.save(mapper.toDomain(requestDto));
    }
}
