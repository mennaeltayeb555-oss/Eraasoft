package org.example.businesslogic.banktransfer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.banktransfer.dto.TransferRequest;
import org.example.businesslogic.banktransfer.model.Account;
import org.example.businesslogic.banktransfer.model.Transfer;
import org.example.businesslogic.banktransfer.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
@RequiredArgsConstructor
public class BankController {

    private final TransferService transferService;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return transferService.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Long id) {
        return transferService.getAccount(id);
    }

    @GetMapping("/transfers")
    public List<Transfer> getTransfers() {
        return transferService.getTransfers();
    }

    @PostMapping("/transfers")
    @ResponseStatus(HttpStatus.CREATED)
    public Transfer transfer(@Valid @RequestBody TransferRequest request) {
        return transferService.transfer(request);
    }
}
