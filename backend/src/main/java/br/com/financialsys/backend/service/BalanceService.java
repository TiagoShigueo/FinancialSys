package br.com.financialsys.backend.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.dto.BalanceDTO;
import br.com.financialsys.backend.mapper.BalanceMapper;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.Transaction;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.BankRepository;
import br.com.financialsys.backend.repository.TransactionRepository;
import br.com.financialsys.backend.repository.UserRepository;

@Service
public class BalanceService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankRepository bankRepository;

    public List<BalanceDTO> getAllBalances(String username) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        List<Transaction> transactions = transactionRepository.findAllByUser_IdUser(user.getIdUser());

        Map<Bank, BigDecimal> bankBalances = new HashMap<>();

        List<Bank> banks = bankRepository.findAllByUser_IdUser(user.getIdUser());

        BigDecimal value;
        Bank bank;
        for (Bank b : banks) {
            value = b.getInitialBalance();
            bank = b;

            bankBalances.merge(bank, value, BigDecimal::add);
        }

        for (Transaction t : transactions) {
            switch (t.getTransactionType()) {
                case Entrada:
                    value = t.getAmount();
                    bank = t.getOriginBank();
                    break;
                case Saída:
                    value = t.getAmount().negate();
                    bank = t.getOriginBank();
                    break;
                case Transferência:
                    // Desconto no banco de origem
                    bankBalances.merge(t.getOriginBank(), t.getAmount().negate(), BigDecimal::add);

                    // Soma no banco destino
                    bankBalances.merge(t.getDestinationBank(), t.getAmount(), BigDecimal::add);
                    continue;
                default:
                    continue;
            }
            bankBalances.merge(bank, value, BigDecimal::add);
        }
        List<BalanceDTO> result = BalanceMapper.toDTOList(bankBalances);

        // Posso puxar o nome do banco também. Talvez colocar no Balance DTO

        return result;
    }

    // Outra ideia é saldo do banco específico
}
