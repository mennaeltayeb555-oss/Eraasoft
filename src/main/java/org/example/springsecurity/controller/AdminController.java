package org.example.springsecurity.controller;
import org.example.springsecurity.dto.CustomerResponse;
import org.example.springsecurity.service.CustomerService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/admin/customers")
public class AdminController {
 private final CustomerService service;
 public AdminController(CustomerService s){service=s;}
 @PutMapping("/{id}/block") public CustomerResponse block(@PathVariable Long id){return service.setStatus(id,"block");}
 @PutMapping("/{id}/disable") public CustomerResponse disable(@PathVariable Long id){return service.setStatus(id,"disable");}
 @PutMapping("/{id}/enable") public CustomerResponse enable(@PathVariable Long id){return service.setStatus(id,"enable");}
}
