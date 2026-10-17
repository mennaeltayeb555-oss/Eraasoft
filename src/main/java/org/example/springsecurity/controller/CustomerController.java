package org.example.springsecurity.controller;
import org.example.springsecurity.dto.CustomerResponse;
import org.example.springsecurity.service.CustomerService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/customers")
public class CustomerController {
 private final CustomerService service;
 public CustomerController(CustomerService s){service=s;}
 @GetMapping("/{id}") public CustomerResponse get(@PathVariable Long id){return service.getById(id);}
 public record UpdateCustomerRequest(String fullName,String phone){}
 @PutMapping("/{id}") public CustomerResponse update(@PathVariable Long id,@RequestBody UpdateCustomerRequest req){return service.update(id,req.fullName(),req.phone());}
}
