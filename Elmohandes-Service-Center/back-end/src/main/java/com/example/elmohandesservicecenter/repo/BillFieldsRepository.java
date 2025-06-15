package com.example.elmohandesservicecenter.repo;

import com.example.elmohandesservicecenter.model.Bank;
import com.example.elmohandesservicecenter.model.BillFields;
import com.example.elmohandesservicecenter.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillFieldsRepository extends JpaRepository<BillFields,Long> {
    List<BillFields> findByBill_Id(Long billId);
    void deleteBillFieldsByBill_Id(Long billId);
}
