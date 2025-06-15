package com.example.elmohandesservicecenter.sevice.jwt;

import com.example.elmohandesservicecenter.dto.jwt.ManagerLoginDto;
import com.example.elmohandesservicecenter.dto.jwt.TokenDto;
import jakarta.transaction.SystemException;

public interface AuthService {
    TokenDto login(ManagerLoginDto managerLoginDto) throws SystemException;

}
