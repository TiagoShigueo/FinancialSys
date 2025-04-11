package br.com.financialsys.backend.mapper;

import br.com.financialsys.backend.dto.BankDTO;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.User;

public class BankMapper {
    public static Bank toEntity(BankDTO dto, User user) {
        Bank b = new Bank();
        b.setIdBank(dto.getIdBank());
        b.setName(dto.getName());
        b.setUser(user);
        return b;
    }

    public static BankDTO toDTO(Bank bank) {
        BankDTO dto = new BankDTO();
        dto.setIdBank(bank.getIdBank());
        dto.setName(bank.getName());
        return dto;
    }
}
