package com.example.elmohandesservicecenter.mapper;

import com.example.elmohandesservicecenter.dto.AccountDto;
import com.example.elmohandesservicecenter.model.Account;
import com.example.elmohandesservicecenter.model.Team;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T15:22:38+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setTeam( accountDtoToTeam( accountDto ) );
        account.setId( accountDto.getId() );
        account.setReason( accountDto.getReason() );
        account.setNewBalance( accountDto.getNewBalance() );
        account.setDateOfReason( accountDto.getDateOfReason() );

        return account;
    }

    @Override
    public List<Account> toEntityList(List<AccountDto> accountDtos) {
        if ( accountDtos == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( accountDtos.size() );
        for ( AccountDto accountDto : accountDtos ) {
            list.add( toEntity( accountDto ) );
        }

        return list;
    }

    @Override
    public AccountDto toDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setTeam_id( accountTeamId( account ) );
        accountDto.setId( account.getId() );
        accountDto.setReason( account.getReason() );
        accountDto.setNewBalance( account.getNewBalance() );
        accountDto.setDateOfReason( account.getDateOfReason() );

        return accountDto;
    }

    @Override
    public List<AccountDto> toDtoList(List<Account> accountList) {
        if ( accountList == null ) {
            return null;
        }

        List<AccountDto> list = new ArrayList<AccountDto>( accountList.size() );
        for ( Account account : accountList ) {
            list.add( toDto( account ) );
        }

        return list;
    }

    protected Team accountDtoToTeam(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Team team = new Team();

        team.setId( accountDto.getTeam_id() );

        return team;
    }

    private Long accountTeamId(Account account) {
        if ( account == null ) {
            return null;
        }
        Team team = account.getTeam();
        if ( team == null ) {
            return null;
        }
        Long id = team.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
