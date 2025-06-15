package com.example.elmohandesservicecenter.repo;

import com.example.elmohandesservicecenter.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {
    boolean existsByName(String name);
    Optional<Bank> findByName(String name);
}
