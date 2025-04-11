package br.com.financialsys.backend.dto;

import java.time.LocalDate;

import br.com.financialsys.backend.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long idTransaction;
    private TransactionType transactionType;
    private LocalDate date;
    private double amount;
    private String description;
    private String category;
    private Long originBankId;
    private Long destinationBankId;
}
