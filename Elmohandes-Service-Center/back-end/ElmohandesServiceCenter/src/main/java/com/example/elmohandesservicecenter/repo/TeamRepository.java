package com.example.elmohandesservicecenter.repo;

import com.example.elmohandesservicecenter.model.Team;
import com.example.elmohandesservicecenter.model.managermodel.Rols;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
