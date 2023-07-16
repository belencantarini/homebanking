package com.ar.bankingonline.api.mappers;

import com.ar.bankingonline.api.dtos.AccountDto;
import com.ar.bankingonline.domain.models.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {
    public Account dtoToAccount(AccountDto dto){
        Account account = new Account();
        account.setNumber(dto.getNumber());
        account.setAmount(dto.getAmount());
        return account;
    }

    public AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setNumber(account.getNumber());
        dto.setAmount(account.getAmount());
        return dto;
    }
}
