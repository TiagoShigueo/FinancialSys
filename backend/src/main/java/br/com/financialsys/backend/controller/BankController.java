package br.com.financialsys.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financialsys.backend.dto.BankDTO;
import br.com.financialsys.backend.model.Bank;
import br.com.financialsys.backend.service.BankService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/createBank")
    public ResponseEntity<BankDTO> createBank(@RequestBody Bank bank, Principal principal) {
        String username = principal.getName();
        Bank createdBank = bankService.createBank(bank, username);
        BankDTO bankDTO = new BankDTO(
                createdBank.getIdBank(),
                createdBank.getName(),
                createdBank.getUser().getName());
        return ResponseEntity.ok(bankDTO);
    }

    @GetMapping("/getAllBanks")
    public List<BankDTO> getAllBanks() {
        return bankService.getAllBanks();
    }
}
