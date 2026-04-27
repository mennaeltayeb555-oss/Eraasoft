package org.example.walletsystem.service.imp;

import org.example.walletsystem.model.Account;
import org.example.walletsystem.model.EWalletSystem;
import org.example.walletsystem.model.Transaction;
import org.example.walletsystem.service.AccountService;
import org.example.walletsystem.service.ValidationService;

import java.util.stream.IntStream;

/**
 * Implements the core account operations: signup, login, deposit, withdraw,
 *                                       :transfer,change password.
 * Uses EWalletSystem as the in-memory data store (acts as a fake database).
 */
public class AccountServiceImp implements AccountService {

    // Shared in-memory data store — holds all accounts
    private static final EWalletSystem eWalletSystem = new EWalletSystem();

    // Handles all input validation rules
    private final ValidationService validationService = new ValidationServiceImp(eWalletSystem);

    // ─── Create Account (Signup) ──────────────────────────────────────────────

    @Override
    public boolean createAccount(Account account) {
        try {
            // Validate all fields before adding the account
            validationService.validateUsername(account.getUsername());
            validationService.validatePassword(account.getPassword());
            validationService.validateAge(account.getAge());
            validationService.validatePhone(account.getPhonenumber());

            eWalletSystem.getAccounts().add(account);
            return true;

        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
            return false;
        }
    }

    // ─── Login ────────────────────────────────────────────────────────────────

    @Override
    public Account login(String username, String password) {
        // Search for an account matching BOTH username AND password
        return eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUsername().equals(username) &&
                                acc.getPassword().equals(password))
                .findFirst()
                .orElse(null); // Returns null if no match found
    }

    // ─── Deposit ──────────────────────────────────────────────────────────────

    @Override
    public void deposit(Account account, double amount) throws Exception {
        validationService.checkAccountNotNull(account);
        validationService.validateDepositAmount(amount);

        // Find the account in the system by username
        Account stored = findAccountByUsername(account.getUsername());
        //check if account is deactivated donot do any operation
        if (!stored.isActive())
            throw new Exception("Account is inactive");
        //store the transaction after deposite
        stored.setBalance(stored.getBalance() + amount);
        stored.getTransactions().add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        stored.getUsername(),
                        null
                )
        );
    }

    // ─── Withdraw ─────────────────────────────────────────────────────────────

    @Override
    public void withdraw(Account account, double amount) throws Exception {
        validationService.checkAccountNotNull(account);
        validationService.validateWithdrawAmount(amount);

        Account stored = findAccountByUsername(account.getUsername());

        //check if account is deactivated donot do any operation
        if (!stored.isActive())
            throw new Exception("Account is inactive");

        // Ensure the account has sufficient funds
        if (stored.getBalance() < amount)
            throw new Exception("Insufficient balance");
        //stored transaction after withdraw

        stored.setBalance(stored.getBalance() - amount);
        stored.getTransactions().add(
                new Transaction(
                        "WITHDRAW",
                        amount,
                        stored.getUsername(),
                        null
                )
        );
    }

    // ─── Transfer ─────────────────────────────────────────────────────────────

    @Override
    public void transfer(Account from, String toUsername, double amount) throws Exception {
        if (from == null)
            throw new Exception("Sender account is null");

        if (!from.isActive())
            throw new Exception("Your account is inactive");


        if (amount <= 0)
            throw new Exception("Transfer amount must be greater than 0");

        if (from.getUsername().equals(toUsername))
            throw new Exception("Cannot transfer money to yourself");

        // Look up the recipient account
        Account to = eWalletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUsername().equals(toUsername))
                .findFirst()
                .orElseThrow(() -> new Exception("Recipient account not found"));

        if (from.getBalance() < amount)
            throw new Exception("Insufficient balance for transfer");

        // Deduct from sender, add to receiver
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        //store the transaction
        Transaction t = new Transaction(
                "TRANSFER",
                amount,
                from.getUsername(),
                to.getUsername()
        );

        from.getTransactions().add(t);
        to.getTransactions().add(t);
    }

    // ─── Change Password ──────────────────────────────────────────────────────

    @Override
    public void changePassword(Account account, String oldPass, String newPass) throws Exception {
        if (!account.getPassword().equals(oldPass))
            throw new Exception("Old password is incorrect");

        if (oldPass.equals(newPass))
            throw new Exception("New password must be different from the old one");

        if (newPass.length() < 6)
            throw new Exception("New password must be at least 6 characters");

        account.setPassword(newPass);
    }

    /**
     * Finds an account in the system by username.
     * Throws an exception if the account doesn't exist.
     */
    private Account findAccountByUsername(String username) throws Exception {
        return eWalletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new Exception("Account not found"));
    }
    // ─── deleteAccount ──────────────────────────────────────────────────────

    @Override
    public void deleteAccount(Account account) throws Exception {
        if (account == null)
            throw new Exception("Account is null");

        eWalletSystem.getAccounts()
                .removeIf(acc -> acc.getUsername().equals(account.getUsername()));
    }
    // ─── deactivateAccount ──────────────────────────────────────────────────────
    @Override
    public void deactivateAccount(Account account) throws Exception {

        if (account == null)
            throw new Exception("Account is null");

        Account stored = findAccountByUsername(account.getUsername());

        if (!stored.isActive())
            throw new Exception("Account already inactive");

        stored.setActive(false);
    }
}