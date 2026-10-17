package org.example.springsecurity.repository;
import org.example.springsecurity.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CustomerRepository extends JpaRepository<Customer,Long> {
 Optional<Customer> findByEmail(String email);
 boolean existsByEmail(String email);
}
