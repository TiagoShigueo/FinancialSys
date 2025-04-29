package br.com.financialsys.backend.dto;

import java.math.BigDecimal;

import br.com.financialsys.backend.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySummaryDTO {
    private int year;
    private int month;
    private TransactionType transactionType;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
}
