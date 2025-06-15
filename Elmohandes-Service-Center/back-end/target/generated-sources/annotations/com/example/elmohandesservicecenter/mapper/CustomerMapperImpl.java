package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.model.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:53:00+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDto.getId() );
        customer.setName( customerDto.getName() );
        customer.setPhoneNumber( customerDto.getPhoneNumber() );

        return customer;
    }

    @Override
    public List<Customer> toEntityList(List<CustomerDto> customerDtos) {
        if ( customerDtos == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( customerDtos.size() );
        for ( CustomerDto customerDto : customerDtos ) {
            list.add( toEntity( customerDto ) );
        }

        return list;
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setName( customer.getName() );
        customerDto.setPhoneNumber( customer.getPhoneNumber() );

        return customerDto;
    }

    @Override
    public List<CustomerDto> toDtoList(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( toDto( customer ) );
        }

        return list;
    }
}
