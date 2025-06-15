package com.example.elmohandesservicecenter.repo.jwt;

import com.example.elmohandesservicecenter.model.managermodel.Rols;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolsRepository extends JpaRepository<Rols, Long> {
    Rols findByCode(String code);
}
