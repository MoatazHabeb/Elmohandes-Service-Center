package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.TeamDto;
import com.example.elmohandesservicecenter.model.Team;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T18:52:59+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class TeamMapperImpl implements TeamMapper {

    @Override
    public Team toEntity(TeamDto teamDto) {
        if ( teamDto == null ) {
            return null;
        }

        Team team = new Team();

        team.setId( teamDto.getId() );
        team.setName( teamDto.getName() );
        team.setPhoneNumber( teamDto.getPhoneNumber() );
        team.setAge( teamDto.getAge() );
        team.setImage( teamDto.getImage() );
        team.setBalance( teamDto.getBalance() );
        team.setActive( teamDto.isActive() );

        return team;
    }

    @Override
    public List<Team> toEntityList(List<TeamDto> teamDtos) {
        if ( teamDtos == null ) {
            return null;
        }

        List<Team> list = new ArrayList<Team>( teamDtos.size() );
        for ( TeamDto teamDto : teamDtos ) {
            list.add( toEntity( teamDto ) );
        }

        return list;
    }

    @Override
    public TeamDto toDto(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamDto teamDto = new TeamDto();

        teamDto.setId( team.getId() );
        teamDto.setName( team.getName() );
        teamDto.setPhoneNumber( team.getPhoneNumber() );
        teamDto.setAge( team.getAge() );
        teamDto.setImage( team.getImage() );
        teamDto.setBalance( team.getBalance() );
        teamDto.setActive( team.isActive() );

        return teamDto;
    }

    @Override
    public List<TeamDto> toDtoList(List<Team> teams) {
        if ( teams == null ) {
            return null;
        }

        List<TeamDto> list = new ArrayList<TeamDto>( teams.size() );
        for ( Team team : teams ) {
            list.add( toDto( team ) );
        }

        return list;
    }
}
