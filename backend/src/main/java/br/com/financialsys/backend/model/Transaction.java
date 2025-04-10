package br.com.financialsys.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private TransactionType transactionType;
    private double amount;
    private String description;
    private int categoryId;
    private int originBankId;
    private int destinationBankId;

}
