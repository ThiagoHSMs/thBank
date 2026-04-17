package com.wallet.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.wallet.api.model.Transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardBillDTO {
    private String accountName;
    private BigDecimal openBillValue;  //Fatura Atual (em Aberto)
    private BigDecimal lastClosedBillValue;  //Fatura que ja fechou e aguarda pagamento
    private LocalDate nextClosingDate;
    private List<Transaction> transactions;
}
