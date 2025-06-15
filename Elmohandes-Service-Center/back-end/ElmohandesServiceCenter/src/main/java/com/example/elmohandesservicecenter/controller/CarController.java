package com.example.elmohandesservicecenter.controller;

import com.example.elmohandesservicecenter.dto.CarDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.sevice.CarService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/addCar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) throws SystemException {
        return ResponseEntity.ok(carService.addCar(carDto));
    }

    @PostMapping("/updateCar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) throws SystemException {
        return ResponseEntity.ok(carService.updateCar(carDto));
    }

    @DeleteMapping("/deleteCar/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<Void> deleteCar(@PathVariable Long id) throws SystemException {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findCarByIdOfCustomer/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<CarDto>> findCarByIdOfCustomer(@PathVariable Long id) throws SystemException {
        return ResponseEntity.ok(carService.findCarByIdOfCustomer(id));
    }
    @GetMapping("/getCars")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<CarDto>> getCars() throws SystemException {
        return ResponseEntity.ok(carService.getCars());
    }
    @GetMapping("/getCarById/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<CarDto> getCarById(@PathVariable Long id) throws SystemException {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/searchCar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<CarDto>> searchCars(@RequestParam String chassisNumber) {
        return ResponseEntity.ok(carService.searchByChassisNumber(chassisNumber));
    }

    @GetMapping("/getCustomerByCar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<CustomerDto>> getCustomerByCar(@RequestParam String chassisNumber) {
        return ResponseEntity.ok(carService.getCustomerByCar(chassisNumber));
    }
}
