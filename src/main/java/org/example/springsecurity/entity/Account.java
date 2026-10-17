package org.example.springsecurity.entity;
import org.example.springsecurity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity @Table(name="accounts", uniqueConstraints=@UniqueConstraint(columnNames="accountNumber"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true) private String accountNumber;
 @Column(nullable=false, precision=19, scale=2) private BigDecimal balance;
 @Column(nullable=false) private String currency;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private AccountStatus status;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="customer_id", nullable=false) private Customer customer;
}
