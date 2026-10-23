package org.example.businesslogic.banktransfer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Transfer implements BaseEntity {

    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private TransferStatus status;
    private String failureReason;
    private LocalDateTime createdAt;
}
