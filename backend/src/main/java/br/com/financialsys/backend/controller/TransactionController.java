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

import br.com.financialsys.backend.dto.TransactionDTO;
import br.com.financialsys.backend.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactiondto,
            Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(transactionService.createTransaction(transactiondto, username));
    }

    @GetMapping("/getUserTransactions")
    public List<TransactionDTO> getUserTransactions(Principal principal) {
        String username = principal.getName();
        return transactionService.getUserTransactions(username);

    }
}
