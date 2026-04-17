package com.wallet.api.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "goals")
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable= false)
    private String description; //Ex: Reserva de emergência

    @Column(nullable= false)
    private BigDecimal targetAmount; // Valor alvo (Ex: 10000.00)

    @Column(name= "current_amount")
    private BigDecimal savedAmount = BigDecimal.ZERO; // Valor já Guardado

    // Método aulixar para o Dashboard
    public BigDecimal getProgressPercentage() {
        if(targetAmount.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        return savedAmount.divide(targetAmount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
    }

}
