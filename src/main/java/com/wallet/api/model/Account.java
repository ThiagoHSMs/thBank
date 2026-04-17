package com.wallet.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ex: "NuConta", "Cartão Principal"

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccountType type; // CORRENTE, POUPANCA, CREDITO, INVESTIMENTO

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    // --- Campos específicos para Cartão de Crédito ---
    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Column(name = "closing_day")
    private Integer closingDay; // Dia que a fatura fecha

    @Column(name = "due_day")
    private Integer dueDay;    // Dia do vencimento

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}