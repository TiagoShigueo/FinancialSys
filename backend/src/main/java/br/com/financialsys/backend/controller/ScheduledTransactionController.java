package br.com.financialsys.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financialsys.backend.dto.ScheduledTransactionDTO;
import br.com.financialsys.backend.service.ScheduledTransactionService;

@RestController
@RequestMapping("/scheduled")
public class ScheduledTransactionController {
    @Autowired
    private ScheduledTransactionService scheduledTransactionService;

    @PostMapping("/createScheduled")
    public ResponseEntity<ScheduledTransactionDTO> createScheduled(
            @RequestBody ScheduledTransactionDTO scheduledTransactionDto, Principal principal) {
        String username = principal.getName();
        return ResponseEntity
                .ok(scheduledTransactionService.createScheduledTransaction(scheduledTransactionDto, username));
    }

    @GetMapping("/getUserScheduled")
    public List<ScheduledTransactionDTO> getUserScheduledTransactions(Principal principal) {
        String username = principal.getName();
        return scheduledTransactionService.getUserScheduledTransactions(username);
    }
}
