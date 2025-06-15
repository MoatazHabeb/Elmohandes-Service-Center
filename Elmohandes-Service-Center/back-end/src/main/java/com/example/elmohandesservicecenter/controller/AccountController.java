package com.example.elmohandesservicecenter.controller;

import com.example.elmohandesservicecenter.dto.AccountDto;
import com.example.elmohandesservicecenter.dto.TeamDto;
import com.example.elmohandesservicecenter.sevice.AccountService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/createbalance")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<AccountDto> createBalance(@RequestBody AccountDto accountDto) throws SystemException {
        return ResponseEntity.ok(accountService.createBalance(accountDto));
    }

    @GetMapping("/getAccount/{teamId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<AccountDto>> getAccount(@PathVariable Long teamId) throws SystemException {
        return ResponseEntity.ok(accountService.getAccount(teamId));
    }
}
