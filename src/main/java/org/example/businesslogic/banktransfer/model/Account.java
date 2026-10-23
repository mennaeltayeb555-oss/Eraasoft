package org.example.businesslogic.banktransfer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.businesslogic.common.repository.BaseEntity;

import java.math.BigDecimal;

// BigDecimal is used for money because double can lose small amounts
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements BaseEntity {

    private Long id;
    private String ownerName;
    private BigDecimal balance;
    private AccountType type;
    private boolean active;
}
