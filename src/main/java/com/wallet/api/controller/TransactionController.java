package com.wallet.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.Transaction;
import com.wallet.api.repository.TransactionRepository;
import com.wallet.api.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    @PostMapping
    public ResponseEntity<Transaction> create (@RequestBody Transaction transaction, @RequestParam Long accountId){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transaction, accountId));
    }

    @GetMapping
    public List<Transaction> listAll() {
        System.out.println("Buscando transações no banco..."); // Isso vai aparecer no console do VS Code
        return transactionRepository.findAll();
}

// Opcional: Listar por conta específica (melhor para o Dashboard)
@GetMapping("/account/{accountId}")
public List<Transaction> listByAccount(@PathVariable Long accountId) {
    return transactionRepository.findByAccountId(accountId);
}
}
