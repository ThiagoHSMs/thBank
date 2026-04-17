package com.wallet.api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wallet.api.dto.DashboardDTO;
import com.wallet.api.dto.GoalSummaryDTO;
import com.wallet.api.model.Account;
import com.wallet.api.model.Transaction;
import com.wallet.api.model.TransactionType;
import com.wallet.api.repository.AccountRepository;
import com.wallet.api.repository.GoalRepository;
import com.wallet.api.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final GoalRepository goalRepository;

public DashboardDTO getDashboardData(Integer month, Integer year) {
        // Se não informar mês/ano, usa o atual
        LocalDate referenceDate = (month != null && year != null)
                ? LocalDate.of(year, month, 1)
                : LocalDate.now();

        // Convertendo para LocalDateTime para cobrir o dia inteiro
        LocalDateTime start = referenceDate.withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = referenceDate.withDayOfMonth(referenceDate.lengthOfMonth()).atTime(23, 59, 59);

        // 1. Saldo Atual
        BigDecimal totalBalance = accountRepository.findAll().stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. Transações do Período
        List<Transaction> periodTransactions = transactionRepository.findByPeriod(start, end);

        BigDecimal monthlyExpenses = periodTransactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal monthlyRevenues = periodTransactions.stream()
                .filter(t -> t.getType() == TransactionType.REVENUE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. Gastos por Categoria (Corrigido para usar periodTransactions e BigDecimal)
        Map<String, BigDecimal> expensesByCategory = periodTransactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE && t.getCategory() != null)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.mapping(
                                Transaction::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));

        // 4. Progresso das Metas
        List<GoalSummaryDTO> goals = goalRepository.findAll().stream()
                .map(g -> new GoalSummaryDTO(g.getDescription(), g.getProgressPercentage()))
                .toList();

        return DashboardDTO.builder()
                .totalBalance(totalBalance)
                .monthlyExpenses(monthlyExpenses)
                .monthlyRevenues(monthlyRevenues)
                .expensesByCategory(expensesByCategory)
                .goals(goals)
                .creditCardLimit(new BigDecimal("5000.00"))
                .closingDate("25/04")
                .build();
                
        }
}