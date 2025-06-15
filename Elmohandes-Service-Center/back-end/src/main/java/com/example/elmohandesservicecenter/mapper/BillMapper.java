package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.BillDto;
import com.example.elmohandesservicecenter.model.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BillMapper {
    BillMapper BILL_MAPPER = Mappers.getMapper(BillMapper.class);
    @Mapping(target = "customer.id", source = "customer_id")
    @Mapping(target = "car.id", source = "car_id")
    Bill toEntity(BillDto billDto);

    List<Bill> toEntityList(List<BillDto> billDtos);

    @Mapping(source = "customer.id", target = "customer_id")
    @Mapping(source = "car.id", target = "car_id")
    BillDto toDto(Bill bill);

    List<BillDto> toDtoList(List<Bill> bills);
}
