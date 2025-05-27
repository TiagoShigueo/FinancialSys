package br.com.financialsys.backend.datainit;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MainDataInitializer implements ApplicationRunner {
    private final UserDataInitializer userDataInitializer;
    private final BankDataInitializer bankDataInitializer;
    private final TransactionDataInitializer transactionDataInitializer;
    private final ScheduledTransactionDataInitializer scheduledTransactionDataInitializer;

    @Override
    public void run(ApplicationArguments args) {
        userDataInitializer.run(args);
        bankDataInitializer.run(args);
        transactionDataInitializer.run(args);
        scheduledTransactionDataInitializer.run(args);
    }
}
