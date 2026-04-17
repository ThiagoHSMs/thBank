package com.wallet.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wallet.api.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Mude de LocalDate para LocalDateTime aqui:
    @Query("SELECT t FROM Transaction t WHERE t.date BETWEEN :start AND :end")
    List<Transaction> findByPeriod(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.date BETWEEN :start AND :end AND t.type = 'EXPENSE' GROUP BY t.category")
    List<Object[]> sumExpensesByCategory(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    
    List<Transaction> findByAccountId(Long accountId);
}