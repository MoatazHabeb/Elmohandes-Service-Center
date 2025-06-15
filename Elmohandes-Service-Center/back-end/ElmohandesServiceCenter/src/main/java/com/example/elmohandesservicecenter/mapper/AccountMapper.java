package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.AccountDto;
import com.example.elmohandesservicecenter.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface AccountMapper {
    AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);
    @Mapping(target = "team.id", source = "team_id")
    Account toEntity(AccountDto accountDto);

    List<Account> toEntityList(List<AccountDto> accountDtos);

    @Mapping(source = "team.id", target = "team_id")
    AccountDto toDto(Account account);

    List<AccountDto> toDtoList(List<Account> accountList);
}
