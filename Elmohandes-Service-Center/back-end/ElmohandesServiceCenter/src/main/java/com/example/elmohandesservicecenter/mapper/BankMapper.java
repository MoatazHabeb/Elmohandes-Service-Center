package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.BankDto;
import com.example.elmohandesservicecenter.model.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BankMapper {
    BankMapper BANK_MAPPER = Mappers.getMapper(BankMapper.class);
    Bank toEntity(BankDto bankDto);
    List<Bank> toEntityList(List<BankDto> bankDtos);


    BankDto toDto(Bank bank);
    List<BankDto> toDtoList( List<Bank> banks);
}
