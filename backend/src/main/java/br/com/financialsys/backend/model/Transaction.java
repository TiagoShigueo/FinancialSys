package br.com.financialsys.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long idTransaction;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "origin_bank", referencedColumnName = "id_bank", nullable = false)
    private Bank originBank;

    @ManyToOne
    @JoinColumn(name = "destination_bank", referencedColumnName = "id_bank", nullable = true)
    private Bank destinationBank;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    private User user;

}
