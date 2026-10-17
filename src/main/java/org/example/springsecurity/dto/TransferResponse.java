package org.example.springsecurity.dto;
import org.example.springsecurity.enums.TransferStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public record TransferResponse(Long id, Long fromAccountId, Long toAccountId, BigDecimal amount, TransferStatus status, LocalDateTime createdAt, Long createdById) {}
