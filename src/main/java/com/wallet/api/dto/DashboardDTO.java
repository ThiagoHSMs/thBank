package com.wallet.api.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardDTO {
    private BigDecimal totalBalance;      // Soma de todas as contas
    private BigDecimal monthlyExpenses;   // Gastos do mês atual
    private BigDecimal monthlyRevenues;   // Receitas do mês atual
    private Map<String, BigDecimal> expensesByCategory; // Gastos por categoria
    private List<GoalSummaryDTO> goals;   // Resumo das metas
    private BigDecimal creditCardLimit; // Valor total (R$ 5000)
    private String closingDate;         // Ex: "Fechamento dia 15"
}