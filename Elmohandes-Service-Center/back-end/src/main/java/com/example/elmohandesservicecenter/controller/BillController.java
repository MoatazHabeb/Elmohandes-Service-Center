package com.example.elmohandesservicecenter.controller;


import com.example.elmohandesservicecenter.controller.vm.AddBillRequest;
import com.example.elmohandesservicecenter.controller.vm.FinalBillVm;
import com.example.elmohandesservicecenter.dto.BillDto;
import com.example.elmohandesservicecenter.dto.BillFieldsDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.sevice.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/addBill")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<FinalBillVm> addBill(@RequestBody AddBillRequest request) {
        return ResponseEntity.ok(billService.addBill(request.getBillDto(), request.getBillFieldsDtos()));
    }
    @GetMapping("/getAllBills")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<FinalBillVm>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }


    @GetMapping("/getBillsByCustomerId/{customerId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<FinalBillVm>> getBillsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(billService.getBillsByCustomerId(customerId));
    }
    @DeleteMapping("/deleteBillById/{billId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> deleteBillById(@PathVariable Long billId) {
        billService.deleteBillById(billId);
        return ResponseEntity.ok().build();
    }
}
