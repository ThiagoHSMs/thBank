package com.wallet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.api.model.Account;
import com.wallet.api.model.Transaction;
import com.wallet.api.model.TransactionType;
import com.wallet.api.repository.AccountRepository;
import com.wallet.api.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Transaction createTransaction(Transaction transaction, Long accountId){
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        
        // No Java, BigDecimal não aceita os operadores + ou -
        // Usamos .subtract() e .add()
        if(transaction.getType() == TransactionType.EXPENSE){
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        } else {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
        }

        accountRepository.save(account);
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }
}