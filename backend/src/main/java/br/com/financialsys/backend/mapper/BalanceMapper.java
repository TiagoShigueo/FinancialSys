package br.com.financialsys.backend.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.financialsys.backend.dto.BalanceDTO;
import br.com.financialsys.backend.model.Bank;

@Component
public class BalanceMapper {
    public static BalanceDTO toDTO(Bank bank, BigDecimal balance) {
        BalanceDTO dto = new BalanceDTO();
        dto.setBankId(bank.getIdBank());
        dto.setBankName(bank.getName());
        dto.setBalance(balance);
        return dto;
    }

    public static List<BalanceDTO> toDTOList(Map<Bank, BigDecimal> bankBalances) {
        return bankBalances.entrySet().stream().map(entry -> toDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
