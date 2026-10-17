package org.example.springsecurity.dto;
import org.example.springsecurity.enums.AccountStatus;
import java.math.BigDecimal;
public record AccountResponse(Long id, String accountNumber, BigDecimal balance, String currency, AccountStatus status, Long customerId) {}
