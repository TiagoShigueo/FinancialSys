package br.com.financialsys.backend.datainit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.Transaction;
import br.com.financialsys.backend.model.TransactionType;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.BankRepository;
import br.com.financialsys.backend.repository.TransactionRepository;
import br.com.financialsys.backend.repository.UserRepository;

@Component
public class TransactionDataInitializer implements ApplicationRunner {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BankRepository bankRepository;

        @Autowired
        private TransactionRepository transactionRepository;

        String username = "demo";
        String bank2 = "Caixa";

        @Override
        public void run(ApplicationArguments args) {
                var userOpt = userRepository.findByName(username);
                var bankOpt1 = bankRepository.findAll().stream().findFirst();
                var bankOpt2 = bankRepository.findByName(bank2);

                if (userOpt.isPresent() && bankRepository.count() == 2 && transactionRepository.count() == 0) {
                        User user = userOpt.get();
                        Bank bank = bankOpt1.get();
                        Bank bank2 = bankOpt2.get();

                        List<Transaction> demoTransactions = getDemoTransactions(user, bank, bank2);

                        transactionRepository.saveAll(demoTransactions);
                        System.out.println("Transações de demonstração criadas com sucesso!");
                } else {
                        System.out.println("Transação já existe, usuário ou banco não encontrado! );");
                }
        }

        private List<Transaction> getDemoTransactions(User user, Bank bank, Bank bank2) {
                return List.of(
                                buildTransaction(TransactionType.Entrada, LocalDate.now().minusDays(5), "Salário",
                                                "Trabalho",
                                                new BigDecimal("3500.00"), bank, null, user),
                                buildTransaction(TransactionType.Saída, LocalDate.now().minusDays(3), "Aluguel",
                                                "Moradia",
                                                new BigDecimal("1200.00"), bank, null, user),
                                buildTransaction(TransactionType.Saída, LocalDate.now().minusDays(2), "Mercado",
                                                "Alimentação",
                                                new BigDecimal("400.00"), bank, null, user),
                                buildTransaction(TransactionType.Entrada, LocalDate.now().minusDays(1), "Freelance",
                                                "Extra",
                                                new BigDecimal("800.00"), bank, null, user),
                                buildTransaction(TransactionType.Saída, LocalDate.now(), "Internet", "Serviços",
                                                new BigDecimal("100.00"), bank, null, user),
                                buildTransaction(TransactionType.Transferência, LocalDate.now().minusDays(4),
                                                "Transferência",
                                                "Transferência", new BigDecimal("1000.00"), bank, bank2, user));

        }

        private Transaction buildTransaction(TransactionType type, LocalDate date, String desc, String category,
                        BigDecimal amount, Bank originBank, Bank destinationBank, User user) {
                Transaction tx = new Transaction();
                tx.setTransactionType(type);
                tx.setDate(date);
                tx.setDescription(desc);
                tx.setCategory(category);
                tx.setAmount(amount);
                tx.setOriginBank(originBank);
                tx.setDestinationBank(destinationBank);
                tx.setUser(user);
                return tx;
        }
}
