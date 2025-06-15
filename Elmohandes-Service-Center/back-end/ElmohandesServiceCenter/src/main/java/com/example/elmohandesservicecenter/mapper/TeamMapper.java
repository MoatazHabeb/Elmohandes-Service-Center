package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.TeamDto;
import com.example.elmohandesservicecenter.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeamMapper {
    TeamMapper TEAM_MAPPER = Mappers.getMapper(TeamMapper.class);
    Team toEntity(TeamDto teamDto);
    List<Team> toEntityList(List<TeamDto> teamDtos);


    TeamDto toDto(Team team);
    List<TeamDto> toDtoList( List<Team> teams);
}
