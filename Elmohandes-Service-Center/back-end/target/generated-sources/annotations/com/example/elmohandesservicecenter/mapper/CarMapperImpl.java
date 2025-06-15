package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.CarDto;
import com.example.elmohandesservicecenter.model.Car;
import com.example.elmohandesservicecenter.model.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-03T14:09:57+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class CarMapperImpl implements CarMapper {

    @Override
    public Car toEntity(CarDto carDto) {
        if ( carDto == null ) {
            return null;
        }

        Car car = new Car();

        car.setCustomer( carDtoToCustomer( carDto ) );
        car.setId( carDto.getId() );
        car.setChassisNumber( carDto.getChassisNumber() );
        car.setBoardNumber( carDto.getBoardNumber() );
        car.setModel( carDto.getModel() );
        car.setEngineNumber( carDto.getEngineNumber() );

        return car;
    }

    @Override
    public List<Car> toEntityList(List<CarDto> carDtos) {
        if ( carDtos == null ) {
            return null;
        }

        List<Car> list = new ArrayList<Car>( carDtos.size() );
        for ( CarDto carDto : carDtos ) {
            list.add( toEntity( carDto ) );
        }

        return list;
    }

    @Override
    public CarDto toDto(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setCustomer_id( carCustomerId( car ) );
        carDto.setId( car.getId() );
        carDto.setChassisNumber( car.getChassisNumber() );
        carDto.setBoardNumber( car.getBoardNumber() );
        carDto.setModel( car.getModel() );
        carDto.setEngineNumber( car.getEngineNumber() );

        return carDto;
    }

    @Override
    public List<CarDto> toDtoList(List<Car> cars) {
        if ( cars == null ) {
            return null;
        }

        List<CarDto> list = new ArrayList<CarDto>( cars.size() );
        for ( Car car : cars ) {
            list.add( toDto( car ) );
        }

        return list;
    }

    protected Customer carDtoToCustomer(CarDto carDto) {
        if ( carDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( carDto.getCustomer_id() );

        return customer;
    }

    private Long carCustomerId(Car car) {
        if ( car == null ) {
            return null;
        }
        Customer customer = car.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
