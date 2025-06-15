package com.example.elmohandesservicecenter.controller;

import com.example.elmohandesservicecenter.dto.AccountDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.sevice.CustomerService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto) throws RuntimeException {
        return ResponseEntity.ok(customerService.addCustomer(customerDto));
    }
    @PostMapping("/updateCustomer")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) throws RuntimeException {
        return ResponseEntity.ok(customerService.updateCustomer(customerDto));
    }

    @DeleteMapping("/deleteCustomer/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws RuntimeException {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getCustomers")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<CustomerDto>> getCustomers() throws RuntimeException {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/findCustomerByName/{name}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<CustomerDto> findCustomerByName(@PathVariable String name) throws SystemException {
        return ResponseEntity.ok(customerService.findByName(name));
    }
    @GetMapping("/getCustomerById/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) throws RuntimeException {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDto>> searchCustomers(@RequestParam String name) {
        return ResponseEntity.ok(customerService.searchByName(name));
    }
}
