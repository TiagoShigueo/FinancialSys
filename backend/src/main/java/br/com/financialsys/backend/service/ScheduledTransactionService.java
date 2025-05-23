package br.com.financialsys.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.dto.ScheduledTransactionDTO;
import br.com.financialsys.backend.mapper.ScheduledTransactionMapper;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.ScheduledTransaction;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.BankRepository;
import br.com.financialsys.backend.repository.ScheduledTransactionRepository;
import br.com.financialsys.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ScheduledTransactionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ScheduledTransactionRepository scheduledTransactionRepository;

    public ScheduledTransactionDTO createScheduledTransaction(ScheduledTransactionDTO scheduledTransactionDto,
            String username) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        Bank bank = bankRepository.findById(scheduledTransactionDto.getBankId())
                .orElseThrow(() -> new EntityNotFoundException("O banco não foi encontrado"));

        ScheduledTransaction scheduledTransaction = new ScheduledTransaction();
        scheduledTransaction = ScheduledTransactionMapper.toEntity(scheduledTransactionDto, user, bank);

        ScheduledTransaction saved = scheduledTransactionRepository.save(scheduledTransaction);

        ScheduledTransactionDTO dto = new ScheduledTransactionDTO();
        dto = ScheduledTransactionMapper.toDTO(saved);
        return dto;
    }

    public List<ScheduledTransactionDTO> getUserScheduledTransactions(String username) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        List<ScheduledTransaction> scheduledTransactions = scheduledTransactionRepository
                .findAllByUser_IdUser(user.getIdUser());
        List<ScheduledTransactionDTO> scheduledTransactionsDTO = scheduledTransactions.stream()
                .map(ScheduledTransactionMapper::toDTO).toList();
        return scheduledTransactionsDTO;
    }
}
