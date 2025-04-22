package br.com.financialsys.backend.model;

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
@Table(name = "banks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bank")
    private Long idBank;

    @Column(name = "name")
    private String name;

    @Column(name = "initial_balance")
    private double initialBalance;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    private User user;
}
