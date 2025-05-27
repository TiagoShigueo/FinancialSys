package br.com.financialsys.backend.datainit;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.BankRepository;
import br.com.financialsys.backend.repository.UserRepository;

@Component
public class BankDataInitializer implements ApplicationRunner {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    String username = "demo";

    @Override
    public void run(ApplicationArguments args) {
        var userOpt = userRepository.findByName(username);
        if (userOpt.isPresent() && bankRepository.count() == 0) {
            User user = userOpt.get();

            List<Bank> demoBanks = getDemoBanks(user);
            bankRepository.saveAll(demoBanks);
            System.out.println("Bancos de demonstração criadas com sucesso");
        } else {
            System.out.println("Banco já existe ou usuário não encontrado! );");
        }
    }

    private List<Bank> getDemoBanks(User user) {
        return List.of(
                buildBank(user, "Banco do Brasil", new BigDecimal("150")),
                buildBank(user, "Caixa", new BigDecimal("1000")));
    }

    private Bank buildBank(User user, String name, BigDecimal initialBlance) {
        Bank b = new Bank();
        b.setUser(user);
        b.setName(name);
        b.setInitialBalance(initialBlance);
        return b;
    }

    // @Override
    // public void run(ApplicationArguments args) {
    // var userOpt = userRepository.findByName(username);
    // if (userOpt.isPresent() && bankRepository.count() == 0) {
    // Bank bank = new Bank();

    // bank.setName(bankName);
    // bank.setInitialBalance(initialBalance);
    // bank.setUser(userOpt.get());

    // bankRepository.save(bank);
    // System.out.println("Banco criado com sucesso!");

    // } else {
    // System.out.println("Banco já existe ou não tem usuário criado!");
    // }
    // }

}
