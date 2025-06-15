package com.example.elmohandesservicecenter.sevice.jwt;

import com.example.elmohandesservicecenter.config.jwt.TokenHandler;
import com.example.elmohandesservicecenter.dto.jwt.ManagerLoginDto;
import com.example.elmohandesservicecenter.dto.jwt.TokenDto;
import com.example.elmohandesservicecenter.model.managermodel.Manager;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private ManagerService managerService;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto login(ManagerLoginDto managerLoginDto) throws SystemException {
        Manager manager = managerService.getManagerbyEmail(managerLoginDto.getEmail());

        if (!passwordEncoder.matches(managerLoginDto.getPassword(), manager.getPassword())) {
            throw new BadCredentialsException("error.userNotExist");
        }

        List<String> rolse = manager.getRols().stream().map(rols -> rols.getCode().substring(5)).collect(Collectors.toList());
        return new TokenDto(tokenHandler.createToken(manager), rolse);
    }

}
