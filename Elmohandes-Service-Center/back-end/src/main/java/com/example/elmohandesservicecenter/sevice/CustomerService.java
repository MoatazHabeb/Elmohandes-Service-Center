package com.example.elmohandesservicecenter.sevice;

import com.example.elmohandesservicecenter.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(CustomerDto customerDto);
    void deleteCustomer(Long id);
    List<CustomerDto> getCustomers();
    CustomerDto findByName(String name);
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> searchByName(String name);
}
