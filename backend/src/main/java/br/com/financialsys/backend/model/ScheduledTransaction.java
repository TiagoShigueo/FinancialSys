package br.com.financialsys.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "scheduled_transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scheduled_transaction")
    private Long idScheduledTransaction;

    @Column(name = "recurence_type")
    private RecurenceType recurenceType;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "total_installments")
    private int totalInstallments;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id_bank", nullable = false)
    private Bank bankId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    private User userId;
}
