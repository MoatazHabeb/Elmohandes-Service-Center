package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.BillFieldsDto;
import com.example.elmohandesservicecenter.model.Bill;
import com.example.elmohandesservicecenter.model.BillFields;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:52:59+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class BillFieldsMapperImpl implements BillFieldsMapper {

    @Override
    public BillFields toEntity(BillFieldsDto dto) {
        if ( dto == null ) {
            return null;
        }

        BillFields billFields = new BillFields();

        billFields.setBill( billFieldsDtoToBill( dto ) );
        billFields.setId( dto.getId() );
        billFields.setField( dto.getField() );
        billFields.setQuantity( dto.getQuantity() );
        billFields.setPrice( dto.getPrice() );

        return billFields;
    }

    @Override
    public BillFieldsDto toDto(BillFields entity) {
        if ( entity == null ) {
            return null;
        }

        BillFieldsDto billFieldsDto = new BillFieldsDto();

        billFieldsDto.setBill_id( entityBillId( entity ) );
        billFieldsDto.setId( entity.getId() );
        billFieldsDto.setField( entity.getField() );
        billFieldsDto.setQuantity( entity.getQuantity() );
        billFieldsDto.setPrice( entity.getPrice() );

        return billFieldsDto;
    }

    @Override
    public List<BillFields> toEntityList(List<BillFieldsDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BillFields> list = new ArrayList<BillFields>( dtoList.size() );
        for ( BillFieldsDto billFieldsDto : dtoList ) {
            list.add( toEntity( billFieldsDto ) );
        }

        return list;
    }

    @Override
    public List<BillFieldsDto> toDtoList(List<BillFields> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BillFieldsDto> list = new ArrayList<BillFieldsDto>( entityList.size() );
        for ( BillFields billFields : entityList ) {
            list.add( toDto( billFields ) );
        }

        return list;
    }

    protected Bill billFieldsDtoToBill(BillFieldsDto billFieldsDto) {
        if ( billFieldsDto == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setId( billFieldsDto.getBill_id() );

        return bill;
    }

    private Long entityBillId(BillFields billFields) {
        if ( billFields == null ) {
            return null;
        }
        Bill bill = billFields.getBill();
        if ( bill == null ) {
            return null;
        }
        Long id = bill.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
