package org.example.walletsystem.service;

import org.example.walletsystem.model.Account;
/**
 * Defines the core account operations for the e-wallet system.
 * Any class that implements this interface must provide all these behaviors.
 */
public interface AccountService {

    /** Registers a new account. Returns true if successful, false otherwise. */
    boolean createAccount(Account account);

    /** Finds and returns an account by username + password. Returns null if not found. */
    Account login(String username, String password);

    /** Adds money to the given account's balance. */
    void deposit(Account account, double amount) throws Exception;

    /** Subtracts money from the given account's balance. */
    void withdraw(Account account, double amount) throws Exception;

    /** Moves money from one account to another by username. */
    void transfer(Account from, String toUsername, double amount) throws Exception;

    /** Updates the account password after verifying the old one. */
    void changePassword(Account account, String oldPass, String newPass) throws Exception;
}