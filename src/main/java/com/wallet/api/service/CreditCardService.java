package com.wallet.api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wallet.api.dto.CreditCardBillDTO;
import com.wallet.api.model.Account;
import com.wallet.api.model.AccountType;
import com.wallet.api.model.Transaction;
import com.wallet.api.repository.AccountRepository;
import com.wallet.api.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public CreditCardBillDTO getBillDetails(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Conta nao Encontrada"));

        if (account.getType() != AccountType.CREDITO) {
            throw new IllegalArgumentException("Esta conta nao e um cartao de credito");
        }

        LocalDate today = LocalDate.now();
        
        // Define a data de fechamento do mes atual
        LocalDate currentMonthClosing = today.withDayOfMonth(account.getClosingDay());

        // Define os limites da fatura
        LocalDate openingRange = today.isAfter(currentMonthClosing)
                                  ? currentMonthClosing.plusDays(1)
                                  : currentMonthClosing.minusMonths(1).plusDays(1);

        LocalDate closingRange = today.isAfter(currentMonthClosing)
                                  ? currentMonthClosing.plusMonths(1)
                                  : currentMonthClosing;

        List<Transaction> allTransactions = transactionRepository.findByAccountId(accountId);
        
// Cálculo simplificado e corrigido
BigDecimal openBillValue = allTransactions.stream()
        .filter(t -> {
            LocalDate transactionDate = t.getDate().toLocalDate();
            return !transactionDate.isBefore(openingRange) && !transactionDate.isAfter(closingRange);
        })
        .map(Transaction::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CreditCardBillDTO.builder()
                .accountName(account.getName())
                .openBillValue(openBillValue)
                .nextClosingDate(closingRange)
                .transactions(allTransactions)
                .build();
    }
}