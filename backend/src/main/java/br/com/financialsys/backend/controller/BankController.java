package br.com.financialsys.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financialsys.backend.dto.BalanceDTO;
import br.com.financialsys.backend.dto.BankDTO;
import br.com.financialsys.backend.service.BalanceService;
import br.com.financialsys.backend.service.BankService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/createBank")
    public ResponseEntity<BankDTO> createBank(@RequestBody BankDTO bankdto, Principal principal) {
        String username = principal.getName();
        BankDTO createdBankdto = new BankDTO();
        createdBankdto = bankService.createBank(bankdto, username);
        return ResponseEntity.ok(createdBankdto);
    }

    @GetMapping("/getAllBanks")
    public List<BankDTO> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/getAllBalances")
    public List<BalanceDTO> getAllBalances(Principal principal) {
        return balanceService.getAllBalances(principal.getName());
    }

}
