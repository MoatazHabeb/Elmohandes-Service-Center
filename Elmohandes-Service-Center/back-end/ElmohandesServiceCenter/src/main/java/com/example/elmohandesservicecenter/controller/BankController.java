package com.example.elmohandesservicecenter.controller;

import com.example.elmohandesservicecenter.dto.BankDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.sevice.BankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {
    @Autowired
    private BankService bankService;
    @PostMapping("/addField")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<BankDto> addField(@RequestBody BankDto bankDto) throws RuntimeException {
        return ResponseEntity.ok(bankService.addField(bankDto));
    }

    @PostMapping("/updateField")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<BankDto> updateField(@RequestBody BankDto bankDto) throws RuntimeException {
        return ResponseEntity.ok(bankService.updateField(bankDto));
    }

    @DeleteMapping("/deleteField/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<Void> deleteField(@PathVariable Long id) throws RuntimeException {
        bankService.deleteField(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getFields")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<BankDto>> getFields() throws RuntimeException {
        return ResponseEntity.ok(bankService.getFields());
    }
    @GetMapping("/getFieldById")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<BankDto> getFieldById(@PathVariable Long id) throws RuntimeException {
        return ResponseEntity.ok(bankService.getFieldById(id));
    }
}
