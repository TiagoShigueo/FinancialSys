package br.com.financialsys.backend.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.dto.CategorySummaryDTO;
import br.com.financialsys.backend.mapper.ReportMapper;
import br.com.financialsys.backend.model.Transaction;
import br.com.financialsys.backend.model.TransactionType;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.TransactionRepository;
import br.com.financialsys.backend.repository.UserRepository;

@Service
public class ReportService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<CategorySummaryDTO> getIncomeCategorySummary(String username) {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        List<Transaction> transactions = transactionRepository.findAllByUser_IdUser(user.getIdUser());

        Map<String, BigDecimal> categoryTotals = transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.Entrada)
                .collect(Collectors.groupingBy(Transaction::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)));
        List<CategorySummaryDTO> categorySummaryDTO = ReportMapper.toDTOList(categoryTotals);

        return categorySummaryDTO;
    }

}
