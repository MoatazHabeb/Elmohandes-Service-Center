package com.example.elmohandesservicecenter.sevice;

import com.example.elmohandesservicecenter.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createBalance(AccountDto accountDto);
    List<AccountDto> getAccount(Long teamId);
}
