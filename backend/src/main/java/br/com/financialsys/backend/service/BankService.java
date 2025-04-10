package br.com.financialsys.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.exception.BankAlreadyExistsException;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.repository.BankRepository;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank createBank(Bank bank) {
        if (bankRepository.existsByName(bank.getName())) {
            throw new BankAlreadyExistsException("O banco com o nome" + bank.getName() + " já existe.");
        }
        bankRepository.save(bank);
        return bank;
    }

}
