package com.example.elmohandesservicecenter.repo.jwt;

import com.example.elmohandesservicecenter.model.managermodel.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByEmail(String email);
}
