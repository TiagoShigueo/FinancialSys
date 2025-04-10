package br.com.financialsys.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.exception.BankAlreadyExistsException;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.BankRepository;
import br.com.financialsys.backend.repository.UserRepository;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Bank createBank(Bank bank, String username) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        bank.setUser(user);
        if (bankRepository.existsByName(bank.getName())) {
            throw new BankAlreadyExistsException("O banco com o nome " + bank.getName() + " já existe.");
        }

        return bankRepository.save(bank);
    }

}
