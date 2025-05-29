package br.com.financialsys.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.financialsys.backend.model.RecurenceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledTransactionDTO {
    private Long idScheduledTransaction;
    private RecurenceType recurenceType;
    private LocalDate paymentDate;
    private int totalInstallments;
    private BigDecimal amount;
    private String description;
    private String category;
    private Long bankId;
    // private Long userId;
}
