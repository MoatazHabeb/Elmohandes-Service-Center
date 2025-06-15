package com.example.elmohandesservicecenter.sevice.impl;

import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.mapper.CustomerMapper;
import com.example.elmohandesservicecenter.model.Customer;
import com.example.elmohandesservicecenter.repo.CustomerRepository;
import com.example.elmohandesservicecenter.sevice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
       if(customerRepository.existsByName(customerDto.getName())){
           throw new RuntimeException("error.nameExist");
       }
        if (customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber())) {
            throw new RuntimeException("error.phoneExists");
        }
        Customer customer = customerRepository.save(CustomerMapper.CUSTOMER_MAPPER.toEntity(customerDto));

        return CustomerMapper.CUSTOMER_MAPPER.toDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getId());

        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer not found with ID: " + customerDto.getId());
        }

        Customer customer = optionalCustomer.get();
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.CUSTOMER_MAPPER.toDto(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
         customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();

        customers.sort(Comparator.comparing(Customer::getName, String.CASE_INSENSITIVE_ORDER));

        return CustomerMapper.CUSTOMER_MAPPER.toDtoList(customers);
    }

    @Override
    public CustomerDto findByName(String name) {
        Customer customer = customerRepository.findByName(name);
        if(!customerRepository.existsByName(name)){
            throw new RuntimeException("error.nameDoesntExist");
        }
        return CustomerMapper.CUSTOMER_MAPPER.toDto(customer);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.get();

        return  CustomerMapper.CUSTOMER_MAPPER.toDto(customer);
    }

    @Override
    public List<CustomerDto> searchByName(String name) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);
        return customers.stream()
                .map(c -> new CustomerDto(c.getId(), c.getName(), c.getPhoneNumber()))
                .toList();
    }
}
