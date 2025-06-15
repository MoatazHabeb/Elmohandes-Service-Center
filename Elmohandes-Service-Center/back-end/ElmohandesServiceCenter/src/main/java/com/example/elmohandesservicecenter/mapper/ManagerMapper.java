package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.jwt.ManagerDto;
import com.example.elmohandesservicecenter.model.managermodel.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ManagerMapper {
    ManagerMapper MANAGER_MAPPER = Mappers.getMapper(ManagerMapper.class);

    Manager toEntity(ManagerDto managerDto);
    List<Manager> toEntityList(List<ManagerDto> managerDtos);


    ManagerDto toDto(Manager manager);
    List<ManagerDto> toDtoList( List<Manager> managers);
}

