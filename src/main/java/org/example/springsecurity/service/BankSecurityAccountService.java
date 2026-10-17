package org.example.springsecurity.service;
import org.example.springsecurity.dto.*;
import org.example.springsecurity.entity.*;
import org.example.springsecurity.enums.AccountStatus;
import org.example.springsecurity.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
public class BankSecurityAccountService {
 private final AccountRepository accounts; private final CustomerRepository customers; private final CustomerService customerService;
 public BankSecurityAccountService(AccountRepository a, CustomerRepository c, CustomerService cs){accounts=a;customers=c;customerService=cs;}
 @Transactional @PreAuthorize("hasAuthority('ACCOUNT_CREATE') or hasAuthority('ROLE_ADMIN')")
 public AccountResponse create(AccountRequest req){
  if(accounts.findByAccountNumber(req.accountNumber()).isPresent()) throw new IllegalArgumentException("Account number already exists");
  Customer c=customers.findById(req.customerId()).orElseThrow(()->new NoSuchElementException("Customer not found"));
  return toResponse(accounts.save(Account.builder().accountNumber(req.accountNumber()).balance(req.balance()).currency(req.currency()).status(AccountStatus.ACTIVE).customer(c).build()));
 }
 @Transactional(readOnly=true) @PreAuthorize("hasAuthority('ACCOUNT_READ') or hasAuthority('ROLE_ADMIN')")
 public AccountResponse get(Long id){
  Account a=accounts.findById(id).orElseThrow(()->new NoSuchElementException("Account not found"));
  var auth=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
  boolean admin=auth.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));
  boolean manager=auth.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_BANK_MANAGER"));
  boolean auditor=auth.getAuthorities().stream().anyMatch(x->x.getAuthority().equals("ROLE_AUDITOR"));
  if(!admin&&!manager&&!auditor&& !a.getCustomer().getId().equals(customerService.getCurrentCustomer().getId())) throw new org.springframework.security.access.AccessDeniedException("You do not own this account");
  return toResponse(a);
 }
 @Transactional(readOnly=true) @PreAuthorize("hasAuthority('ACCOUNT_READ') or hasAuthority('ROLE_ADMIN')")
 public List<AccountResponse> mine(){return accounts.findByCustomer_Id(customerService.getCurrentCustomer().getId()).stream().map(this::toResponse).toList();}
 @Transactional @PreAuthorize("hasAuthority('ACCOUNT_UPDATE') or hasAuthority('ROLE_ADMIN')")
 public AccountResponse update(Long id, String currency, AccountStatus status){
  Account a=accounts.findById(id).orElseThrow(()->new NoSuchElementException("Account not found"));a.setCurrency(currency);a.setStatus(status);return toResponse(a);
 }
 @Transactional @PreAuthorize("hasAuthority('ACCOUNT_DELETE') or hasAuthority('ROLE_ADMIN')")
 public void delete(Long id){if(!accounts.existsById(id))throw new NoSuchElementException("Account not found");accounts.deleteById(id);}
 public AccountResponse toResponse(Account a){return new AccountResponse(a.getId(),a.getAccountNumber(),a.getBalance(),a.getCurrency(),a.getStatus(),a.getCustomer().getId());}
}
