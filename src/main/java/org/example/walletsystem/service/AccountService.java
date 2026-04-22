package org.example.walletsystem.service;

import org.example.walletsystem.model.Account;

public interface AccountService {
    boolean creatAccount (Account account);
    Account login(String username, String password);
    void deposite (Account account , double amount);
    void withdraw(Account account, double amount);
}
