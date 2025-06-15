package com.example.elmohandesservicecenter.controller;

import com.example.elmohandesservicecenter.dto.TeamDto;
import com.example.elmohandesservicecenter.sevice.TeamService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;


    @PostMapping("/creatememberofteam")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<TeamDto> createMemberOfTeam(@RequestBody TeamDto teamDto) throws SystemException {
        return ResponseEntity.ok(teamService.createMemberOfTeam(teamDto));
    }
    @GetMapping("/getallteam")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<TeamDto>> getAllTeam() throws SystemException {
        return ResponseEntity.ok(teamService.getAllTeam());
    }

    @GetMapping("/getteambyid/{teamId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<TeamDto> getTeamById(@PathVariable Long teamId) throws SystemException {
        return ResponseEntity.ok(teamService.getTeamById(teamId));
    }



    @PostMapping("/UpdateTeam")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<TeamDto> updateTeamById(@RequestBody TeamDto teamDto){
        return ResponseEntity.ok(teamService.updateTeamById(teamDto));
    }
}
