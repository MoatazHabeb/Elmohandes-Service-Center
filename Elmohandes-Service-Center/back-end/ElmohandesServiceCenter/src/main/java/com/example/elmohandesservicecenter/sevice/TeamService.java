package com.example.elmohandesservicecenter.sevice;

import com.example.elmohandesservicecenter.dto.TeamDto;

import java.util.List;

public interface TeamService {
    TeamDto createMemberOfTeam(TeamDto teamDto);
    List<TeamDto> getAllTeam();

    TeamDto updateTeamById(TeamDto teamDto);
    TeamDto getTeamById(Long teamId);
}
