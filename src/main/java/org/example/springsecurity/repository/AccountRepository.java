package org.example.springsecurity.repository;
import org.example.springsecurity.entity.Account;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;
import jakarta.persistence.LockModeType;
public interface AccountRepository extends JpaRepository<Account,Long> {
 Optional<Account> findByAccountNumber(String accountNumber);
 List<Account> findByCustomer_Id(Long customerId);
 @Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select a from Account a where a.id=:id")
 Optional<Account> findByIdForUpdate(@Param("id") Long id);
}
