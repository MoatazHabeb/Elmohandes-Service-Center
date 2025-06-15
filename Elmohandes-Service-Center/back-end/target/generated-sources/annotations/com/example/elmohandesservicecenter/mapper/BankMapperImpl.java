package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.BankDto;
import com.example.elmohandesservicecenter.model.Bank;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:53:00+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class BankMapperImpl implements BankMapper {

    @Override
    public Bank toEntity(BankDto bankDto) {
        if ( bankDto == null ) {
            return null;
        }

        Bank bank = new Bank();

        bank.setId( bankDto.getId() );
        bank.setName( bankDto.getName() );
        bank.setQuantity( bankDto.getQuantity() );
        bank.setPrice( bankDto.getPrice() );

        return bank;
    }

    @Override
    public List<Bank> toEntityList(List<BankDto> bankDtos) {
        if ( bankDtos == null ) {
            return null;
        }

        List<Bank> list = new ArrayList<Bank>( bankDtos.size() );
        for ( BankDto bankDto : bankDtos ) {
            list.add( toEntity( bankDto ) );
        }

        return list;
    }

    @Override
    public BankDto toDto(Bank bank) {
        if ( bank == null ) {
            return null;
        }

        BankDto bankDto = new BankDto();

        bankDto.setId( bank.getId() );
        bankDto.setName( bank.getName() );
        bankDto.setQuantity( bank.getQuantity() );
        bankDto.setPrice( bank.getPrice() );

        return bankDto;
    }

    @Override
    public List<BankDto> toDtoList(List<Bank> banks) {
        if ( banks == null ) {
            return null;
        }

        List<BankDto> list = new ArrayList<BankDto>( banks.size() );
        for ( Bank bank : banks ) {
            list.add( toDto( bank ) );
        }

        return list;
    }
}
