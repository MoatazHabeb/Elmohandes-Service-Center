package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.BillDto;
import com.example.elmohandesservicecenter.model.Bill;
import com.example.elmohandesservicecenter.model.Car;
import com.example.elmohandesservicecenter.model.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-07T15:25:12+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class BillMapperImpl implements BillMapper {

    @Override
    public Bill toEntity(BillDto billDto) {
        if ( billDto == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setCustomer( billDtoToCustomer( billDto ) );
        bill.setCar( billDtoToCar( billDto ) );
        bill.setId( billDto.getId() );
        bill.setDateOfNow( billDto.getDateOfNow() );
        bill.setReceptionDate( billDto.getReceptionDate() );
        bill.setDeliveryDate( billDto.getDeliveryDate() );
        bill.setCustomerRepresentative( billDto.getCustomerRepresentative() );
        bill.setLastServiceDate( billDto.getLastServiceDate() );
        bill.setFactory( billDto.getFactory() );
        bill.setSpareParts( billDto.getSpareParts() );
        bill.setExternalWorks( billDto.getExternalWorks() );
        bill.setTaxes( billDto.getTaxes() );
        bill.setFactoryDiscount( billDto.getFactoryDiscount() );
        bill.setSparePartsDiscount( billDto.getSparePartsDiscount() );
        bill.setSpecialDiscount( billDto.getSpecialDiscount() );
        bill.setNetBill( billDto.getNetBill() );
        bill.setKilometers( billDto.getKilometers() );

        return bill;
    }

    @Override
    public List<Bill> toEntityList(List<BillDto> billDtos) {
        if ( billDtos == null ) {
            return null;
        }

        List<Bill> list = new ArrayList<Bill>( billDtos.size() );
        for ( BillDto billDto : billDtos ) {
            list.add( toEntity( billDto ) );
        }

        return list;
    }

    @Override
    public BillDto toDto(Bill bill) {
        if ( bill == null ) {
            return null;
        }

        BillDto billDto = new BillDto();

        billDto.setCustomer_id( billCustomerId( bill ) );
        billDto.setCar_id( billCarId( bill ) );
        billDto.setId( bill.getId() );
        billDto.setDateOfNow( bill.getDateOfNow() );
        billDto.setReceptionDate( bill.getReceptionDate() );
        billDto.setDeliveryDate( bill.getDeliveryDate() );
        billDto.setCustomerRepresentative( bill.getCustomerRepresentative() );
        billDto.setLastServiceDate( bill.getLastServiceDate() );
        billDto.setFactory( bill.getFactory() );
        billDto.setSpareParts( bill.getSpareParts() );
        billDto.setExternalWorks( bill.getExternalWorks() );
        billDto.setTaxes( bill.getTaxes() );
        billDto.setFactoryDiscount( bill.getFactoryDiscount() );
        billDto.setSparePartsDiscount( bill.getSparePartsDiscount() );
        billDto.setSpecialDiscount( bill.getSpecialDiscount() );
        billDto.setNetBill( bill.getNetBill() );
        billDto.setKilometers( bill.getKilometers() );

        return billDto;
    }

    @Override
    public List<BillDto> toDtoList(List<Bill> bills) {
        if ( bills == null ) {
            return null;
        }

        List<BillDto> list = new ArrayList<BillDto>( bills.size() );
        for ( Bill bill : bills ) {
            list.add( toDto( bill ) );
        }

        return list;
    }

    protected Customer billDtoToCustomer(BillDto billDto) {
        if ( billDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( billDto.getCustomer_id() );

        return customer;
    }

    protected Car billDtoToCar(BillDto billDto) {
        if ( billDto == null ) {
            return null;
        }

        Car car = new Car();

        car.setId( billDto.getCar_id() );

        return car;
    }

    private Long billCustomerId(Bill bill) {
        if ( bill == null ) {
            return null;
        }
        Customer customer = bill.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long billCarId(Bill bill) {
        if ( bill == null ) {
            return null;
        }
        Car car = bill.getCar();
        if ( car == null ) {
            return null;
        }
        Long id = car.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
