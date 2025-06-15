package com.example.elmohandesservicecenter.repo;

import com.example.elmohandesservicecenter.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByName(String name);
    Customer findByName(String name);
    boolean existsByPhoneNumber(String phoneNumber);
    List<Customer> findByNameContainingIgnoreCase(String name);
}
