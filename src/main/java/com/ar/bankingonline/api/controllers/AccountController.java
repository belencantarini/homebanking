package com.ar.bankingonline.api.controllers;

import com.ar.bankingonline.api.dtos.AccountDto;
import com.ar.bankingonline.application.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        List<AccountDto> accounts = service.getAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.getAccountById(id));
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto dto) {
        return ResponseEntity.status(201).body(service.createAccount(dto));
    }

    @PutMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto account){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, account));
    }


    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(200).body(service.deleteAccount(id));
    }
}
