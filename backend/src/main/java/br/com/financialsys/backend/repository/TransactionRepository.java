package br.com.financialsys.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financialsys.backend.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);

    // @Query("SELECT t FROM Transaction t WHERE t.user.idUser = :userId")
    // List<Transaction> findAllByUserId(@Param("userId") Long id);

    List<Transaction> findAllByUser_IdUser(Long id);
}
