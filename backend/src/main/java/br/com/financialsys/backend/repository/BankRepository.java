package br.com.financialsys.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.financialsys.backend.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByName(String name);

    boolean existsByNameAndUser_IdUser(String name, Long idUser);

    List<Bank> findAllByUser_IdUser(Long idUser);
}
