package org.example.springsecurity.security;
import org.example.springsecurity.repository.CustomerRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
 private final CustomerRepository customers;
 public CustomUserDetailsService(CustomerRepository customers){this.customers=customers;}
 @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  var customer=customers.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Invalid email or password"));
  return new CustomUserDetails(customer);
 }
}
