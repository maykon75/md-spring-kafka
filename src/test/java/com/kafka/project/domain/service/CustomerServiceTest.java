package com.kafka.project.domain.service;

import com.kafka.project.api.dto.CustomerRequestDTO;
import com.kafka.project.api.dto.CustomerResponseDTO;
import com.kafka.project.api.mapper.CustomerMapper;
import com.kafka.project.domain.model.Customer;
import com.kafka.project.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService customerService;


    @Test
    void shouldReturnListOfCustomersWhenGetAll() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setAge(20);
        customer.setEmail("test@email.com");
        customer.setCellPhone("123456789");

        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(1L);
        dto.setName("Test");
        dto.setAge(20);
        dto.setEmail("test@email.com");
        dto.setCellPhone("123456789");


        when(repository.findAll()).thenReturn(List.of(customer));
        when(mapper.toDtoList(List.of(customer))).thenReturn(List.of(dto));

        List<CustomerResponseDTO> result = customerService.getAll();

        assertEquals(1, result.size());

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDtoList(List.of(customer));
    }

    @Test
    void shouldSaveCustomerWhenPost() {
        CustomerRequestDTO requestDTO = new CustomerRequestDTO();
        requestDTO.setName("Test");
        requestDTO.setAge(20);
        requestDTO.setEmail("test@email.com");
        requestDTO.setCellPhone("123456789");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setAge(20);
        customer.setEmail("test@email.com");
        customer.setCellPhone("123456789");

        when(mapper.toDomain(requestDTO)).thenReturn(customer);

        customerService.post(requestDTO);

        verify(mapper, times(1)).toDomain(requestDTO);
        verify(repository, times(1)).save(customer);
    }
}
