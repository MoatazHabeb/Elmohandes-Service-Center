package com.example.elmohandesservicecenter.sevice.impl;

import com.example.elmohandesservicecenter.dto.CarDto;
import com.example.elmohandesservicecenter.dto.CustomerDto;
import com.example.elmohandesservicecenter.mapper.CarMapper;
import com.example.elmohandesservicecenter.mapper.CustomerMapper;
import com.example.elmohandesservicecenter.model.Car;
import com.example.elmohandesservicecenter.model.Customer;
import com.example.elmohandesservicecenter.repo.CarRepository;
import com.example.elmohandesservicecenter.sevice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public CarDto addCar(CarDto carDto) {

        Car car = carRepository.save(CarMapper.CAR_MAPPER.toEntity(carDto));

        return CarMapper.CAR_MAPPER.toDto(car);
    }

    @Override
    public CarDto updateCar(CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(carDto.getId());
        if (optionalCar.isEmpty()) {
            throw new RuntimeException("Car not found with ID: " + carDto.getId());
        }
        Car car = optionalCar.get();
        car.setChassisNumber(carDto.getChassisNumber());
        car.setBoardNumber(carDto.getBoardNumber());
        car.setEngineNumber(carDto.getEngineNumber());
        car.setModel(carDto.getModel());

        Car savedCar = carRepository.save(car);

        return CarMapper.CAR_MAPPER.toDto(savedCar);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDto> findCarByIdOfCustomer(Long id) {
        List<Car> cars = carRepository.findByCustomer_Id(id);

        return CarMapper.CAR_MAPPER.toDtoList(cars);
    }

    @Override
    public List<CarDto> getCars() {
        List<Car> cars = carRepository.findAll();

        return CarMapper.CAR_MAPPER.toDtoList(cars);
    }

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = optionalCar.get();

        return   CarMapper.CAR_MAPPER.toDto(car);
    }
    @Override
    public List<CarDto> searchByChassisNumber(String chassisNumber) {
        List<Car> cars = carRepository.findByChassisNumberContainingIgnoreCase(chassisNumber);
        return cars.stream().map(car -> {
            CarDto dto = new CarDto();
            dto.setId(car.getId());
            dto.setBoardNumber(car.getBoardNumber());
            dto.setChassisNumber(car.getChassisNumber());
            dto.setEngineNumber(car.getEngineNumber());
            dto.setModel(car.getModel());
            dto.setCustomer_id(car.getCustomer().getId());
            return dto;
        }).toList();
    }

    @Override
    public List<CustomerDto> getCustomerByCar(String chassisNumber) {
        List<Customer> customers = new ArrayList<>();

        List<Car> cars = carRepository.findByChassisNumberContainingIgnoreCase(chassisNumber);
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            customers.add(car.getCustomer());
        }

        return CustomerMapper.CUSTOMER_MAPPER.toDtoList(customers);
    }
}
