package com.kafka.project.api.controller;

import com.kafka.project.api.dto.CustomerRequestDTO;
import com.kafka.project.api.dto.CustomerResponseDTO;
import com.kafka.project.domain.service.CustomerService;
import com.kafka.project.infrastructure.kafka.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private CustomerController customerController;


    @Test
    void shouldReturnListOfCustomersWhenGetAll() {
        CustomerResponseDTO customer = new CustomerResponseDTO();
        customer.setId(1L);
        customer.setName("Test");
        customer.setAge(20);
        customer.setEmail("test@email.com");
        customer.setCellPhone("123456789");

        when(customerService.getAll()).thenReturn(List.of(customer));

        ResponseEntity<List<CustomerResponseDTO>> response = customerController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());

        verify(customerService, times(1)).getAll();
    }

    @Test
    void shouldSendMessageToKafkaWhenPost() {
        CustomerRequestDTO request = new CustomerRequestDTO();
        request.setName("Test");
        request.setAge(20);
        request.setEmail("test@email.com");
        request.setCellPhone("123456789");

        ResponseEntity<Void> response = customerController.post(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());

        verify(kafkaProducer, times(1)).execute(request);
    }
}
