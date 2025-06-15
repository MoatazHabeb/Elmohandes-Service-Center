package com.example.elmohandesservicecenter.sevice.jwt;

import com.example.elmohandesservicecenter.dto.jwt.ManagerDto;
import com.example.elmohandesservicecenter.model.managermodel.Manager;
import jakarta.transaction.SystemException;

public interface ManagerService {
    Manager getManagerbyEmail(String email) throws SystemException;
    Manager checkManagerExistByToken(String token) throws SystemException;
    void createUserManager (ManagerDto managerDto) throws SystemException;
    ManagerDto getManagerById(Long id);
}
