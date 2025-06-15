package com.example.elmohandesservicecenter.controller.jwt;

import com.example.elmohandesservicecenter.dto.jwt.ManagerDto;
import com.example.elmohandesservicecenter.dto.jwt.ManagerLoginDto;
import com.example.elmohandesservicecenter.dto.jwt.TokenDto;
import com.example.elmohandesservicecenter.sevice.jwt.AuthService;
import com.example.elmohandesservicecenter.sevice.jwt.ManagerService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/manager")
@Validated
public class ManagerController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ManagerService managerService;
    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody ManagerLoginDto managerLoginDto) throws SystemException {

        return ResponseEntity.ok(authService.login(managerLoginDto));
    }
    @PostMapping("/create-manager")
    ResponseEntity<Void> createManager(@RequestBody @Valid ManagerDto managerDto) throws SystemException {
        managerService.createUserManager(managerDto);
        return ResponseEntity.created(URI.create("/manager/addManagerWithRols")).build();
    }
}
