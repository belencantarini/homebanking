package com.ar.bankingonline.application.services;

import com.ar.bankingonline.api.dtos.AccountDto;
import com.ar.bankingonline.api.mappers.AccountMapper;
import com.ar.bankingonline.domain.models.Account;
import com.ar.bankingonline.infrastructure.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ar.bankingonline.domain.exceptions.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<AccountDto> getAccounts() {
        List<Account> accounts = repository.findAll();
        return accounts.stream().map(AccountMapper::accountToDto).toList();
    }


    public AccountDto createAccount(AccountDto account) {
        return AccountMapper.accountToDto(repository.save(AccountMapper.dtoToAccount(account)));
    }

    public AccountDto getAccountById(Long id) {
        return AccountMapper.accountToDto(repository.findById(id).get());
    }

    public AccountDto updateAccount(Long id, AccountDto account) {
        Optional<Account> accountCreated = repository.findById(id);
        if (accountCreated.isPresent()) {
            Account entity = accountCreated.get();
            Account accountUpdated = AccountMapper.dtoToAccount(account);
            accountUpdated.setId(entity.getId());
            Account saved = repository.save(accountUpdated);
            return AccountMapper.accountToDto(saved);
        } else {
            throw new AccountNotFoundException("No se ha encontrado la cuenta con id: " + id);
        }
    }


    public String deleteAccount(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Se ha eliminado la cuenta con id: " + id;
        } else {
            return "No se ha eliminado la cuenta con id: " + id + " porque no existe";
        }
    }

    // Agregar métodos de ingreso y egreso de dinero y realizacion de transferencia
    public BigDecimal withdraw(BigDecimal amount, Long idOrigin){
        // primero: Obtenemos la cuenta
        Account account = repository.findById(idOrigin).orElse(null);
        // segundo: debitamos el valor del amount con el amount de esa cuenta (validar si hay dinero disponible)
        if (account.getBalance().subtract(amount).intValue() > 0){
            account.setBalance(account.getBalance().subtract(amount));
            repository.save(account);
        }
        // tercero: devolvemos esa cantidad
        return account.getBalance().subtract(amount);
    }

    public BigDecimal addAmountToAccount(BigDecimal amount, Long idOrigin){
        // primero: Obtenemos la cuenta
        Account account = repository.findById(idOrigin).orElse(null);
        // segundo: acreditamos el valor del amount con el amount de esa cuenta
        account.setBalance(account.getBalance().add(amount));
        repository.save(account);
        // tercero: devolvemos esa cantidad
        return amount;
    }

}
