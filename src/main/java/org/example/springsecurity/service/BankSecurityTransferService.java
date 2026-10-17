package org.example.springsecurity.service;
import org.example.springsecurity.dto.*;
import org.example.springsecurity.enums.AccountStatus;
import org.example.springsecurity.enums.TransferStatus;
import org.example.springsecurity.entity.*;
import org.example.springsecurity.enums.*;
import org.example.springsecurity.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;
@Service
public class BankSecurityTransferService {
 private final BankTransferRepository transfers; private final AccountRepository accounts; private final CustomerService customers;
 public BankSecurityTransferService(BankTransferRepository t, AccountRepository a, CustomerService c){transfers=t;accounts=a;customers=c;}
 @Transactional @PreAuthorize("hasAuthority('TRANSFER_CREATE') or hasAuthority('ROLE_ADMIN')")
 public TransferResponse create(TransferRequest req){
  if(req.amount()==null||req.amount().compareTo(BigDecimal.ZERO)<=0)throw new IllegalArgumentException("Amount must be greater than zero");
  if(req.fromAccountId().equals(req.toAccountId()))throw new IllegalArgumentException("Source and destination accounts must differ");
  Account from=accounts.findByIdForUpdate(req.fromAccountId()).orElseThrow(()->new NoSuchElementException("Source account not found"));
  Account to=accounts.findByIdForUpdate(req.toAccountId()).orElseThrow(()->new NoSuchElementException("Destination account not found"));
  if(from.getStatus()!=AccountStatus.ACTIVE||to.getStatus()!=AccountStatus.ACTIVE)throw new IllegalArgumentException("Both accounts must be ACTIVE");
  if(!from.getCurrency().equals(to.getCurrency()))throw new IllegalArgumentException("Account currencies must match");
  if(!from.getCustomer().getId().equals(customers.getCurrentCustomer().getId()) &&
     !org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN")))
    throw new AccessDeniedException("You can only transfer from your own account");
  if(from.getBalance().compareTo(req.amount())<0)throw new IllegalArgumentException("Insufficient balance");
  return toResponse(transfers.save(BankTransfer.builder().fromAccount(from).toAccount(to).amount(req.amount()).status(TransferStatus.PENDING).createdBy(customers.getCurrentCustomer()).build()));
 }
 @Transactional(readOnly=true) @PreAuthorize("hasAuthority('TRANSFER_READ') or hasAuthority('ROLE_ADMIN')")
 public TransferResponse get(Long id){
  BankTransfer t=transfers.findById(id).orElseThrow(()->new NoSuchElementException("Transfer not found"));
  var auth=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
  boolean privileged=auth.getAuthorities().stream().anyMatch(a->Set.of("ROLE_ADMIN","ROLE_BANK_MANAGER","ROLE_AUDITOR").contains(a.getAuthority()));
  if(!privileged&&!t.getCreatedBy().getId().equals(customers.getCurrentCustomer().getId()))throw new AccessDeniedException("You can only read your own transfers");
  return toResponse(t);
 }
 @Transactional @PreAuthorize("hasAuthority('TRANSFER_APPROVE') or hasAuthority('ROLE_ADMIN')")
 public TransferResponse approve(Long id){
  BankTransfer t=transfers.findById(id).orElseThrow(()->new NoSuchElementException("Transfer not found"));
  if(t.getStatus()!=TransferStatus.PENDING) {
      throw new IllegalArgumentException("Only PENDING transfers can be approved");
  }
  Account from=accounts.findByIdForUpdate(t.getFromAccount().getId()).orElseThrow();
  Account to=accounts.findByIdForUpdate(t.getToAccount().getId()).orElseThrow();
  if(from.getStatus()!=AccountStatus.ACTIVE||to.getStatus()!=AccountStatus.ACTIVE) {
      throw new IllegalArgumentException("Both accounts must be ACTIVE");
  }
  if(from.getBalance().compareTo(t.getAmount())<0)throw new IllegalArgumentException("Insufficient balance at approval time");
  from.setBalance(from.getBalance().subtract(t.getAmount()));to.setBalance(to.getBalance().add(t.getAmount()));
  t.setStatus(TransferStatus.COMPLETED);t.setApprovedBy(customers.getCurrentCustomer());
  return toResponse(t);
 }
 public TransferResponse toResponse(BankTransfer t){return new TransferResponse(t.getId(),t.getFromAccount().getId(),t.getToAccount().getId(),t.getAmount(),t.getStatus(),t.getCreatedAt(),t.getCreatedBy().getId());}
}
