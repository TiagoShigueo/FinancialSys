package br.com.financialsys.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/createBank")
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(bankService.createBank(bank));
    }

    @GetMapping("/getAllBanks")
    public List<Bank> getAllBanks() {
        System.out.println("BankController teste");
        return bankService.getAllBanks();
    }

}
