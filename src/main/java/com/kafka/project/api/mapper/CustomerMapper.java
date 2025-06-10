package com.kafka.project.api.mapper;

import com.kafka.project.api.dto.CustomerRequestDTO;
import com.kafka.project.api.dto.CustomerResponseDTO;
import com.kafka.project.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    List<CustomerResponseDTO> toDtoList(List<Customer> customers);

    @Mapping(target = "id", ignore = true)
    Customer toDomain(CustomerRequestDTO customerRequestDTO);
}
