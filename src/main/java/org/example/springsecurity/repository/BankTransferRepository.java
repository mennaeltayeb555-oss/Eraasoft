package org.example.springsecurity.repository;
import org.example.springsecurity.entity.BankTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface BankTransferRepository extends JpaRepository<BankTransfer,Long> {
 List<BankTransfer> findByCreatedBy_Id(Long customerId);
}
