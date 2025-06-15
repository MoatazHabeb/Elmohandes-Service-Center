package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.jwt.ManagerDto;
import com.example.elmohandesservicecenter.dto.jwt.RolsDto;
import com.example.elmohandesservicecenter.model.managermodel.Manager;
import com.example.elmohandesservicecenter.model.managermodel.Rols;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:53:00+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class ManagerMapperImpl implements ManagerMapper {

    @Override
    public Manager toEntity(ManagerDto managerDto) {
        if ( managerDto == null ) {
            return null;
        }

        Manager manager = new Manager();

        manager.setId( managerDto.getId() );
        manager.setEmail( managerDto.getEmail() );
        manager.setPassword( managerDto.getPassword() );
        manager.setPhoneNumber( managerDto.getPhoneNumber() );
        manager.setFullname( managerDto.getFullname() );
        manager.setAge( managerDto.getAge() );
        manager.setRols( rolsDtoListToRolsList( managerDto.getRols() ) );

        return manager;
    }

    @Override
    public List<Manager> toEntityList(List<ManagerDto> managerDtos) {
        if ( managerDtos == null ) {
            return null;
        }

        List<Manager> list = new ArrayList<Manager>( managerDtos.size() );
        for ( ManagerDto managerDto : managerDtos ) {
            list.add( toEntity( managerDto ) );
        }

        return list;
    }

    @Override
    public ManagerDto toDto(Manager manager) {
        if ( manager == null ) {
            return null;
        }

        ManagerDto managerDto = new ManagerDto();

        managerDto.setId( manager.getId() );
        managerDto.setFullname( manager.getFullname() );
        managerDto.setEmail( manager.getEmail() );
        managerDto.setPhoneNumber( manager.getPhoneNumber() );
        managerDto.setPassword( manager.getPassword() );
        managerDto.setAge( manager.getAge() );
        managerDto.setRols( rolsListToRolsDtoList( manager.getRols() ) );

        return managerDto;
    }

    @Override
    public List<ManagerDto> toDtoList(List<Manager> managers) {
        if ( managers == null ) {
            return null;
        }

        List<ManagerDto> list = new ArrayList<ManagerDto>( managers.size() );
        for ( Manager manager : managers ) {
            list.add( toDto( manager ) );
        }

        return list;
    }

    protected Rols rolsDtoToRols(RolsDto rolsDto) {
        if ( rolsDto == null ) {
            return null;
        }

        Rols rols = new Rols();

        rols.setId( rolsDto.getId() );
        rols.setCode( rolsDto.getCode() );

        return rols;
    }

    protected List<Rols> rolsDtoListToRolsList(List<RolsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Rols> list1 = new ArrayList<Rols>( list.size() );
        for ( RolsDto rolsDto : list ) {
            list1.add( rolsDtoToRols( rolsDto ) );
        }

        return list1;
    }

    protected RolsDto rolsToRolsDto(Rols rols) {
        if ( rols == null ) {
            return null;
        }

        RolsDto rolsDto = new RolsDto();

        if ( rols.getId() != null ) {
            rolsDto.setId( rols.getId() );
        }
        rolsDto.setCode( rols.getCode() );

        return rolsDto;
    }

    protected List<RolsDto> rolsListToRolsDtoList(List<Rols> list) {
        if ( list == null ) {
            return null;
        }

        List<RolsDto> list1 = new ArrayList<RolsDto>( list.size() );
        for ( Rols rols : list ) {
            list1.add( rolsToRolsDto( rols ) );
        }

        return list1;
    }
}
