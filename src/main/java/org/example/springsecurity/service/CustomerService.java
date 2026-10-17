package org.example.springsecurity.service;
import org.example.springsecurity.dto.*;
import org.example.springsecurity.entity.*;
import org.example.springsecurity.repository.*;
import org.example.springsecurity.security.CustomUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
public class CustomerService {
 private final CustomerRepository customers; private final RoleRepository roles; private final PasswordEncoder encoder;
 public CustomerService(CustomerRepository c,RoleRepository r,PasswordEncoder e){customers=c;roles=r;encoder=e;}
 @Transactional
 public CustomerResponse register(RegisterRequest req){
  if(customers.existsByEmail(req.email())) throw new IllegalArgumentException("Email is already registered");
  Role customerRole=roles.findByName("CUSTOMER").orElseThrow(()->new IllegalStateException("CUSTOMER role is not seeded"));
  Customer c=Customer.builder().fullName(req.fullName().trim()).email(req.email().trim().toLowerCase())
   .password(encoder.encode(req.password())).phone(req.phone().trim()).roles(new HashSet<>(Set.of(customerRole))).build();
  return toResponse(customers.save(c));
 }
 public Customer getCurrentCustomer(){
  var auth=SecurityContextHolder.getContext().getAuthentication();
  if(auth==null || !(auth.getPrincipal() instanceof CustomUserDetails user)) throw new BadCredentialsException("Authentication required");
  return customers.findById(user.getId()).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
 }
 @PreAuthorize("hasAuthority('CUSTOMER_READ') or hasAuthority('ROLE_ADMIN')")
 public CustomerResponse getById(Long id){return toResponse(customers.findById(id).orElseThrow(()->new NoSuchElementException("Customer not found")));}
 @Transactional @PreAuthorize("hasAuthority('CUSTOMER_UPDATE') or hasAuthority('ROLE_ADMIN')")
 public CustomerResponse update(Long id, String fullName, String phone){
  Customer c=customers.findById(id).orElseThrow(()->new NoSuchElementException("Customer not found"));
  c.setFullName(fullName);c.setPhone(phone);return toResponse(c);
 }
 @Transactional @PreAuthorize("hasAuthority('ROLE_ADMIN')")
 public CustomerResponse setStatus(Long id, String action){
  Customer c=customers.findById(id).orElseThrow(()->new NoSuchElementException("Customer not found"));
  switch(action){case "block" -> c.setAccountNonLocked(false);case "disable" -> c.setEnabled(false);case "enable" -> {c.setEnabled(true);c.setAccountNonLocked(true);}default -> throw new IllegalArgumentException("Unknown action");}
  return toResponse(c);
 }
 public CustomerResponse toResponse(Customer c){return new CustomerResponse(c.getId(),c.getFullName(),c.getEmail(),c.getPhone(),c.isEnabled(),c.getRoles().stream().map(Role::getName).collect(java.util.stream.Collectors.toSet()),c.getCreatedAt());}
}
