package org.example.businesslogic.banktransfer.repository;

import org.example.businesslogic.banktransfer.model.Account;
import org.example.businesslogic.banktransfer.model.AccountType;
import org.example.businesslogic.common.repository.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class AccountRepository extends InMemoryRepository<Account> {

    public AccountRepository() {
        save(new Account(null, "Mona Adel", new BigDecimal("20000"), AccountType.SAVINGS, true));
        save(new Account(null, "Youssef Samir", new BigDecimal("5000"), AccountType.CURRENT, true));
        save(new Account(null, "Karim Tech LLC", new BigDecimal("500000"), AccountType.BUSINESS, true));
        save(new Account(null, "Nour Ibrahim", new BigDecimal("3000"), AccountType.SAVINGS, false));
    }
}
