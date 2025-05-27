package br.com.financialsys.backend.datainit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.RecurenceType;
import br.com.financialsys.backend.model.ScheduledTransaction;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.BankRepository;
import br.com.financialsys.backend.repository.ScheduledTransactionRepository;
import br.com.financialsys.backend.repository.UserRepository;

@Component
public class ScheduledTransactionDataInitializer implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ScheduledTransactionRepository scheduledTransactionRepository;

    String username = "demo";

    @Override
    public void run(ApplicationArguments args) {
        var userOpt = userRepository.findByName(username);
        var bankOpt = bankRepository.findAll().stream().findFirst();

        if (userOpt.isPresent() && bankOpt.isPresent() && scheduledTransactionRepository.count() == 0) {
            User user = userOpt.get();
            Bank bank = bankOpt.get();
            List<ScheduledTransaction> demoScheduledTransactions = getDemoScheduledTransactions(user, bank);

            scheduledTransactionRepository.saveAll(demoScheduledTransactions);
            System.out.println("Transações agendadas de demonstração criadas com sucesso!");
        } else {
            System.out.println("Transações agendadas já exite, usuário ou banco não encontrado! );");
        }
    }

    private List<ScheduledTransaction> getDemoScheduledTransactions(User user, Bank bank) {
        return List.of(
                buildScheduledTransaction(RecurenceType.Diario, LocalDate.now().minusDays(10), 30,
                        new BigDecimal("12.00"), "Ônibus", "Transporte", bank, user),
                buildScheduledTransaction(RecurenceType.Mensal,
                        LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()),
                        12, new BigDecimal("44.90"), "Netflix", "Entretenimento", bank, user));

    }

    private ScheduledTransaction buildScheduledTransaction(RecurenceType recurenceType, LocalDate paymentDate,
            int totalInstallments, BigDecimal amount, String description, String category, Bank bank, User user) {
        ScheduledTransaction st = new ScheduledTransaction();
        st.setRecurenceType(recurenceType);
        st.setPaymentDate(paymentDate);
        st.setTotalInstallments(totalInstallments);
        st.setAmount(amount);
        st.setDescription(description);
        st.setCategory(category);
        st.setBankId(bank);
        st.setUserId(user);
        return st;

    }
}
