package br.com.financialsys.backend.service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.dto.CategorySummaryDTO;
import br.com.financialsys.backend.dto.MonthlySummaryDTO;
import br.com.financialsys.backend.exception.UserNotFoundException;
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

        // Talvez fazer uma função para puxar as transações, limpando 3 linhas de cada
        // função

        public List<CategorySummaryDTO> getIncomeCategorySummary(String username) {
                User user = userRepository.findByName(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
                List<Transaction> transactions = transactionRepository.findAllByUser_IdUser(user.getIdUser());

                Map<String, BigDecimal> categoryTotals = transactions.stream()
                                .filter(t -> t.getTransactionType() == TransactionType.Entrada)
                                .collect(Collectors.groupingBy(Transaction::getCategory,
                                                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount,
                                                                BigDecimal::add)));
                List<CategorySummaryDTO> categorySummaryDTO = ReportMapper.toDTOList(categoryTotals);

                return categorySummaryDTO;
        }

        public List<CategorySummaryDTO> getExpenseCategorySummary(String username) {
                User user = userRepository.findByName(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
                List<Transaction> transactions = transactionRepository.findAllByUser_IdUser(user.getIdUser());

                Map<String, BigDecimal> categoryTotals = transactions.stream()
                                .filter(t -> t.getTransactionType() == TransactionType.Saída)
                                .collect(Collectors.groupingBy(Transaction::getCategory,
                                                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount,
                                                                BigDecimal::add)));
                List<CategorySummaryDTO> categorySummaryDTO = ReportMapper.toDTOList(categoryTotals);

                return categorySummaryDTO;

        }

        public List<MonthlySummaryDTO> getMonthlySummary(String username) {
                User user = userRepository.findByName(username)
                                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
                List<Transaction> transactions = transactionRepository.findAllByUser_IdUser(user.getIdUser());

                List<Transaction> filteredTransactions = transactions.stream()
                                .filter(t -> t.getTransactionType() == TransactionType.Entrada
                                                || t.getTransactionType() == TransactionType.Saída)
                                .toList();

                Map<YearMonth, List<Transaction>> grouped = transactions.stream()
                                .filter(t -> t.getTransactionType() != TransactionType.Transferência)
                                .collect(Collectors.groupingBy(t -> YearMonth.from(t.getDate())));

                List<MonthlySummaryDTO> summaries = new ArrayList<>();

                for (Map.Entry<YearMonth, List<Transaction>> entry : grouped.entrySet()) {
                        YearMonth yearMonth = entry.getKey();
                        List<Transaction> monthlyTransactions = entry.getValue();

                        BigDecimal totalIncome = monthlyTransactions.stream()
                                        .filter(t -> t.getTransactionType() == TransactionType.Entrada)
                                        .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

                        BigDecimal totalExpense = monthlyTransactions.stream()
                                        .filter(t -> t.getTransactionType() == TransactionType.Saída)
                                        .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

                        MonthlySummaryDTO dto = new MonthlySummaryDTO();
                        dto.setYear(yearMonth.getYear());
                        dto.setMonth(yearMonth.getMonthValue());
                        dto.setTransactionType(null);
                        dto.setTotalIncome(totalIncome);
                        dto.setTotalExpense(totalExpense);

                        summaries.add(dto);
                }
                return summaries;

        }
}
