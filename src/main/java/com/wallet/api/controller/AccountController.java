package com.wallet.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.Account;
import com.wallet.api.repository.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

@PostMapping
public Account create(@RequestBody Account account) {
    Account savedAccount = accountRepository.save(account);
    if (savedAccount == null) {
        throw new RuntimeException("Erro ao salvar conta");
    }
    return savedAccount;
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public List<Account> list(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return accountRepository.findByUserId(userId);
        }
        return accountRepository.findAll(); // Retorna tudo se não mandar o ID
    }
}