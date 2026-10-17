package org.example.springsecurity.controller;
import org.example.springsecurity.dto.*;
import org.example.springsecurity.security.*;
import org.example.springsecurity.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/auth")
public class AuthController {
 private final CustomerService customers; private final AuthenticationManager manager;
 public AuthController(CustomerService c,AuthenticationManager m){customers=c;manager=m;}
 @PostMapping("/register") public CustomerResponse register(@Valid @RequestBody RegisterRequest req){return customers.register(req);}
 @PostMapping("/login") public Map<String,Object> login(@Valid @RequestBody LoginRequest req){
  var auth=manager.authenticate(new UsernamePasswordAuthenticationToken(req.email().trim().toLowerCase(),req.password()));
  SecurityContextHolder.getContext().setAuthentication(auth);
  var user=(CustomUserDetails)auth.getPrincipal();
  return Map.of("authenticated",true,"customerId",user.getId(),"email",user.getUsername(),"name",user.getFullName(),"roles",user.getRoleNames(),"authorities",user.getAuthorities().stream().map(a->a.getAuthority()).sorted().toList(),
    "note","This project currently uses HTTP Basic authentication for protected requests; send the same credentials using Postman Authorization > Basic Auth.");
 }
 @GetMapping("/me") public Map<String,Object> me(){
  var auth=SecurityContextHolder.getContext().getAuthentication();
  if(auth==null||!(auth.getPrincipal() instanceof CustomUserDetails user))throw new org.springframework.security.authentication.BadCredentialsException("Authentication required");
  return Map.of("customerId",user.getId(),"email",user.getUsername(),"name",user.getFullName(),"roles",user.getRoleNames(),"authorities",user.getAuthorities().stream().map(a->a.getAuthority()).sorted().toList());
 }
}
