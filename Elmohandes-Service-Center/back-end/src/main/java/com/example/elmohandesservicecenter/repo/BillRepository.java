package com.example.elmohandesservicecenter.repo;

import com.example.elmohandesservicecenter.model.Bill;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findByCustomer_Id(Long customerId);
}
