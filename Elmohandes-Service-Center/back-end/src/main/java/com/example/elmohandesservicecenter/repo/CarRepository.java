package com.example.elmohandesservicecenter.repo;

import com.example.elmohandesservicecenter.model.Account;
import com.example.elmohandesservicecenter.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findByCustomer_Id(Long customerId);
    List<Car> findByChassisNumberContainingIgnoreCase(String chassisNumber);
    Car findByChassisNumber (String chassisNumber);
}
