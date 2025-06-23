package br.com.financialsys.backend.dto;

import java.time.LocalDate;
import java.util.List;

public record PaymentDateGroupDTO(LocalDate paymentDate,
        List<ScheduledTransactionDTO> transactions) {

}
