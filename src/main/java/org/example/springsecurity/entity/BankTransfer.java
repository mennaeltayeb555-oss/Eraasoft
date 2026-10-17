package org.example.springsecurity.entity;
import org.example.springsecurity.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity @Table(name="transfers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankTransfer {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="from_account_id", nullable=false) private Account fromAccount;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="to_account_id", nullable=false) private Account toAccount;
 @Column(nullable=false, precision=19, scale=2) private BigDecimal amount;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private TransferStatus status;
 @Column(nullable=false, updatable=false) @Builder.Default private LocalDateTime createdAt = LocalDateTime.now();
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="created_by", nullable=false) private Customer createdBy;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="approved_by") private Customer approvedBy;
}
