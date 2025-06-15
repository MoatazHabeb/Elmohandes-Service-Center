package com.example.elmohandesservicecenter.sevice.impl;

import com.example.elmohandesservicecenter.dto.AccountDto;
import com.example.elmohandesservicecenter.mapper.AccountMapper;
import com.example.elmohandesservicecenter.model.Account;
import com.example.elmohandesservicecenter.model.Team;
import com.example.elmohandesservicecenter.repo.AccountRepository;
import com.example.elmohandesservicecenter.repo.TeamRepository;
import com.example.elmohandesservicecenter.sevice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Override
    public AccountDto createBalance(AccountDto accountDto) {

        Optional<Team> optionalTeam = teamRepository.findById(accountDto.getTeam_id());
        Team team = optionalTeam.get();
        team.setBalance(team.getBalance() + accountDto.getNewBalance());
        teamRepository.save(team);


        Account account = accountRepository.save(AccountMapper.ACCOUNT_MAPPER.toEntity(accountDto));

        return AccountMapper.ACCOUNT_MAPPER.toDto(account);
    }

    @Override
    public List<AccountDto> getAccount(Long teamId) {
        List<Account> accountList = accountRepository.findByTeam_Id(teamId);


        return AccountMapper.ACCOUNT_MAPPER.toDtoList(accountList);
    }
}
