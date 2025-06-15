package com.example.elmohandesservicecenter.sevice.impl;

import com.example.elmohandesservicecenter.dto.TeamDto;
import com.example.elmohandesservicecenter.mapper.TeamMapper;
import com.example.elmohandesservicecenter.model.Team;
import com.example.elmohandesservicecenter.repo.TeamRepository;
import com.example.elmohandesservicecenter.sevice.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Override
    public TeamDto createMemberOfTeam(TeamDto teamDto) {

        Team team = teamRepository.save(TeamMapper.TEAM_MAPPER.toEntity(teamDto));

        return TeamMapper.TEAM_MAPPER.toDto(team);
    }

    @Override
    public List<TeamDto> getAllTeam() {
        List<Team> teams = teamRepository.findAll();

        return TeamMapper.TEAM_MAPPER.toDtoList(teams);
    }

    @Override
    public TeamDto updateTeamById(TeamDto teamDto) {
        Optional<Team> optionalTeam = teamRepository.findById(teamDto.getId());
        Team team = optionalTeam.get();
        team.setName(teamDto.getName());
        team.setPhoneNumber(teamDto.getPhoneNumber());
        team.setAge(teamDto.getAge());
        team.setImage(teamDto.getImage());
        team.setBalance(teamDto.getBalance());
        team.setActive(teamDto.isActive());
        Team savedTeam = teamRepository.save(team);

        return  TeamMapper.TEAM_MAPPER.toDto(savedTeam);
    }

    @Override
    public TeamDto getTeamById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Team team = optionalTeam.get();

        return TeamMapper.TEAM_MAPPER.toDto(team);
    }
}
