package com.example.elmohandesservicecenter.mapper;
import com.example.elmohandesservicecenter.dto.CarDto;
import com.example.elmohandesservicecenter.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {
    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);
    @Mapping(target = "customer.id", source = "customer_id")
    Car toEntity(CarDto carDto);

    List<Car> toEntityList(List<CarDto> carDtos);

    @Mapping(source = "customer.id", target = "customer_id")
    CarDto toDto(Car car);

    List<CarDto> toDtoList(List<Car> cars);
}