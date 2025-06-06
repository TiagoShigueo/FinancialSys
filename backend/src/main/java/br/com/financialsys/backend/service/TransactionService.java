package br.com.financialsys.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.dto.TransactionDTO;
import br.com.financialsys.backend.dto.TransactionResponseDTO;
import br.com.financialsys.backend.mapper.TransactionMapper;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.Transaction;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.BankRepository;
import br.com.financialsys.backend.repository.TransactionRepository;
import br.com.financialsys.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionService {
        @Autowired
        private TransactionRepository transactionRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BankRepository bankRepository;

        public TransactionDTO createTransaction(TransactionDTO transactiondto, String username) {

                User user = userRepository.findByName(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

                Bank originBank = bankRepository.findById(transactiondto.getOriginBankId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "O banco de origem não foi encontrado."));

                Bank destinationBank = null;
                if (transactiondto.getDestinationBankId() != null) {
                        destinationBank = bankRepository.findById(transactiondto.getDestinationBankId())
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                        "O banco de destino não foi encontrado."));
                }

                Transaction transaction = new Transaction();
                transaction = TransactionMapper.toEntity(transactiondto, user, originBank, destinationBank);

                Transaction saved = transactionRepository.save(transaction);

                TransactionDTO dto = new TransactionDTO();
                dto = TransactionMapper.toDTO(saved);
                return dto;
        }

        public List<TransactionResponseDTO> getUserTransactions(String username) {
                User user = userRepository.findByName(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
                List<Transaction> transaction = transactionRepository.findAllByUser_IdUser(user.getIdUser());
                List<TransactionResponseDTO> dto = transaction.stream().map(TransactionMapper::toResponseDTO).toList();
                return dto;
        }
}
