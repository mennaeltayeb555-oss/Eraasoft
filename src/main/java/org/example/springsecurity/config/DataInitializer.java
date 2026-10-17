package org.example.springsecurity.config;

import org.example.springsecurity.entity.*;
import org.example.springsecurity.entity.Role;
import org.example.springsecurity.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
@Configuration
public class DataInitializer {
 @Bean CommandLineRunner seed(PermissionRepository permissions, RoleRepository roles, CustomerRepository customers, PasswordEncoder encoder) {
  return args -> {
   Map<String,Permission> p=new HashMap<>();
   for(String name:List.of("ACCOUNT_READ","ACCOUNT_CREATE","ACCOUNT_UPDATE","ACCOUNT_DELETE","TRANSFER_CREATE","TRANSFER_READ","TRANSFER_APPROVE","CUSTOMER_READ","CUSTOMER_UPDATE","REPORT_VIEW"))
    p.put(name,permissions.findByName(name).orElseGet(()->permissions.save(Permission.builder().name(name).build())));
   Map<String,List<String>> matrix=Map.of(
    "ADMIN",List.of("ACCOUNT_READ","ACCOUNT_CREATE","ACCOUNT_UPDATE","ACCOUNT_DELETE","TRANSFER_READ","TRANSFER_APPROVE","CUSTOMER_READ","CUSTOMER_UPDATE","REPORT_VIEW"),
    "BANK_MANAGER",List.of("ACCOUNT_READ","ACCOUNT_UPDATE","TRANSFER_READ","TRANSFER_APPROVE","CUSTOMER_READ","CUSTOMER_UPDATE"),
    "CUSTOMER",List.of("ACCOUNT_READ","TRANSFER_CREATE","TRANSFER_READ"),
    "AUDITOR",List.of("ACCOUNT_READ","TRANSFER_READ","REPORT_VIEW"));
   for(var entry:matrix.entrySet()){
    Role role=roles.findByName(entry.getKey()).orElseGet(()-> Role.builder().name(entry.getKey()).build());
    role.setPermissions(new HashSet<>());
    entry.getValue().forEach(name->role.getPermissions().add(p.get(name)));
    roles.save(role);
   }
   // Development-only bootstrap admin. Change these credentials before any real deployment.
   if(!customers.existsByEmail("admin@bank.local")){
    Role admin=roles.findByName("ADMIN").orElseThrow();
    customers.save(Customer.builder().fullName("System Admin").email("admin@bank.local")
      .password(encoder.encode("ChangeMe123!")).phone("0000000000").roles(new HashSet<>(Set.of(admin))).build());
   }
  };
 }
}
