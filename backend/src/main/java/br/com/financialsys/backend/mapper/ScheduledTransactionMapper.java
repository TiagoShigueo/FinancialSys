package br.com.financialsys.backend.mapper;

import br.com.financialsys.backend.dto.ScheduledTransactionDTO;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.model.ScheduledTransaction;
import br.com.financialsys.backend.model.User;

public class ScheduledTransactionMapper {
    public static ScheduledTransaction toEntity(ScheduledTransactionDTO dto, User user, Bank bank) {
        ScheduledTransaction st = new ScheduledTransaction();
        st.setIdScheduledTransaction(dto.getIdScheduledTransaction());
        st.setRecurenceType(dto.getRecurenceType());
        st.setPaymentDate(dto.getPaymentDate());
        st.setTotalInstallments(dto.getTotalInstallments());
        st.setAmount(dto.getAmount());
        st.setDescription(dto.getDescription());
        st.setCategory(dto.getCategory());
        st.setBankId(bank);
        st.setUserId(user);
        return st;
    }

    public static ScheduledTransactionDTO toDTO(ScheduledTransaction st) {
        ScheduledTransactionDTO dto = new ScheduledTransactionDTO();
        dto.setIdScheduledTransaction(st.getIdScheduledTransaction());
        dto.setRecurenceType(st.getRecurenceType());
        dto.setPaymentDate(st.getPaymentDate());
        dto.setTotalInstallments(st.getTotalInstallments());
        dto.setAmount(st.getAmount());
        dto.setDescription(st.getDescription());
        dto.setCategory(st.getCategory());
        dto.setBankId(st.getBankId().getIdBank());
        dto.setUserId(st.getUserId().getIdUser());
        return dto;
    }
}
