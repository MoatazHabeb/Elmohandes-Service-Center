package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.BankDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.model.Bank;
import com.example.elmohandesservicecenter.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);
    Customer toEntity(CustomerDto customerDto);
    List<Customer> toEntityList(List<CustomerDto> customerDtos);


    CustomerDto toDto(Customer customer);
    List<CustomerDto> toDtoList( List<Customer> customers);
}
