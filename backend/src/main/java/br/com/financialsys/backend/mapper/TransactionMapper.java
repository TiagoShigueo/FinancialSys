package br.com.financialsys.backend.mapper;

import br.com.financialsys.backend.dto.TransactionDTO;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.Transaction;
import br.com.financialsys.backend.model.User;

public class TransactionMapper {
    public static Transaction toEntity(TransactionDTO dto, User user, Bank origin, Bank destination) {
        Transaction t = new Transaction();
        t.setDate(dto.getDate());
        t.setAmount(dto.getAmount());
        t.setCategory(dto.getCategory());
        t.setDescription(dto.getDescription());
        t.setTransactionType(dto.getTransactionType());
        t.setUser(user);
        t.setOriginBank(origin);
        if (destination != null) {
            t.setDestinationBank(destination);
        } else {
            t.setDestinationBank(null);
        }
        return t;
    }

    public static TransactionDTO toDTO(Transaction t) {
        TransactionDTO dto = new TransactionDTO();
        dto.setDate(t.getDate());
        dto.setAmount(t.getAmount());
        dto.setCategory(t.getCategory());
        dto.setDescription(t.getDescription());
        dto.setTransactionType(t.getTransactionType());
        dto.setOriginBankId(t.getOriginBank().getIdBank());
        if (t.getDestinationBank() != null) {
            dto.setDestinationBankId(t.getDestinationBank().getIdBank());
        } else {
            dto.setDestinationBankId(null);
        }
        return dto;
    }
}
