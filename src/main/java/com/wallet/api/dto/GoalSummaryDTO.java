package com.wallet.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoalSummaryDTO {
    private String description;
    private BigDecimal progressPorcentage;
}
