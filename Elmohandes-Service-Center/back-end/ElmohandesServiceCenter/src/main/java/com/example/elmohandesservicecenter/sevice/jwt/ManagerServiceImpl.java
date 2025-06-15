package com.example.elmohandesservicecenter.sevice.jwt;

import com.example.elmohandesservicecenter.config.jwt.TokenHandler;
import com.example.elmohandesservicecenter.dto.jwt.ManagerDto;
import com.example.elmohandesservicecenter.mapper.ManagerMapper;
import com.example.elmohandesservicecenter.model.managermodel.Manager;
import com.example.elmohandesservicecenter.model.managermodel.Rols;
import com.example.elmohandesservicecenter.repo.jwt.ManagerRepository;
import com.example.elmohandesservicecenter.repo.jwt.RolsRepository;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private RolsRepository rolsRepository;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Manager getManagerbyEmail(String email) throws SystemException {

        Manager manager = managerRepository.findByEmail(email);


        if (manager == null) {

            throw new RuntimeException("error.invalid.email");
        }

        return manager;
    }

    @Override
    public Manager checkManagerExistByToken(String token) {
        String email = tokenHandler.getSubject(token);

        if (Objects.isNull(email)) {
            return null;
        }

        return managerRepository.findByEmail(email);
    }

    @Override
    public void createUserManager(ManagerDto managerDto) throws SystemException {

        if (managerDto.getId() != null) {
            throw new SystemException("id must be null");
        }
        Manager managerExits = managerRepository.findByEmail(managerDto.getEmail());
        if (managerExits != null) {
            throw new RuntimeException("error.clientExist");
        }

        Manager manager = ManagerMapper.MANAGER_MAPPER.toEntity(managerDto);
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        Rols rols = rolsRepository.findByCode("ROLE_USER");
        if (rols == null) {
            throw new SystemException("role not exist");
        }
        List<Rols> roles = List.of(rols);
        manager.setRols(roles);
        managerRepository.save(manager);
    }


    @Override
    public ManagerDto getManagerById(Long id) {
        Manager manager = managerRepository.findById(id).get();
        return ManagerMapper.MANAGER_MAPPER.toDto(manager);
    }
}
