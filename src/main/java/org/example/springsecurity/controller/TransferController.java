package org.example.springsecurity.controller;
import org.example.springsecurity.dto.*;
import org.example.springsecurity.service.BankSecurityTransferService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/transfers")
public class TransferController {
 private final BankSecurityTransferService service;
 public TransferController(BankSecurityTransferService s){service=s;}
 @PostMapping public TransferResponse create(@Valid @RequestBody TransferRequest req){return service.create(req);}
 @GetMapping("/{id}") public TransferResponse get(@PathVariable Long id){return service.get(id);}
 @PostMapping("/{id}/approve") public TransferResponse approve(@PathVariable Long id){return service.approve(id);}
}
