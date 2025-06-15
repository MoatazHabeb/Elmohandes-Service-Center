package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.BillDto;
import com.example.elmohandesservicecenter.dto.BillFieldsDto;
import com.example.elmohandesservicecenter.model.Bill;
import com.example.elmohandesservicecenter.model.BillFields;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BillFieldsMapper {
    BillFieldsMapper BILL_FIELDS_MAPPER = Mappers.getMapper(BillFieldsMapper.class);
    @Mapping(target = "bill.id", source = "bill_id")
    BillFields toEntity(BillFieldsDto dto);

    @Mapping(source = "bill.id", target = "bill_id")
    BillFieldsDto toDto(BillFields entity);

    List<BillFields> toEntityList(List<BillFieldsDto> dtoList);

    List<BillFieldsDto> toDtoList(List<BillFields> entityList);
}
