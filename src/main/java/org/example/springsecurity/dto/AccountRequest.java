package org.example.springsecurity.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record AccountRequest(@NotBlank String accountNumber, @NotNull @DecimalMin("0.00") BigDecimal balance, @NotBlank String currency, @NotNull Long customerId) {}
