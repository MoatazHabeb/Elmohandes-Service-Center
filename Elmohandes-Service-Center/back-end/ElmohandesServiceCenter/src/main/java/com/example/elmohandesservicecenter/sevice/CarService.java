package com.example.elmohandesservicecenter.sevice;

import com.example.elmohandesservicecenter.dto.CarDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;

import java.util.List;

public interface CarService {
    CarDto addCar(CarDto carDto);
    CarDto updateCar(CarDto carDto);
    void deleteCar(Long id);
    List<CarDto> findCarByIdOfCustomer(Long id);
    List<CarDto> getCars();
    CarDto getCarById(Long id);
    List<CarDto> searchByChassisNumber(String chassisNumber);
    List<CustomerDto> getCustomerByCar(String chassisNumber);
}
