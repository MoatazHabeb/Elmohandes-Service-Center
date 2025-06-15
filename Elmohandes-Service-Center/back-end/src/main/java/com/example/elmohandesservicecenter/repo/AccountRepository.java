package com.example.elmohandesservicecenter.repo;

import com.example.elmohandesservicecenter.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByTeam_Id(Long teamId);
}
