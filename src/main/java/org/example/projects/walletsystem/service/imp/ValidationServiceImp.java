package org.example.walletsystem.service.imp;

import org.example.walletsystem.model.Account;
import org.example.walletsystem.model.EWalletSystem;
import org.example.walletsystem.service.ValidationService;

/**
 * Implements all validation logic for the e-wallet system.
 * Needs access to EWalletSystem to check for duplicate usernames/phones.
 */
public class ValidationServiceImp implements ValidationService {

    private final EWalletSystem eWalletSystem;

    public ValidationServiceImp(EWalletSystem eWalletSystem) {
        this.eWalletSystem = eWalletSystem;
    }

    // ─── Username ─────────────────────────────────────────────────────────────

    @Override
    public void validateUsername(String username) throws Exception {
        // Must not be null and must have at least 3 characters
        if (username == null || username.length() < 3)
            throw new Exception("Username must be at least 3 characters");

        // First character must be uppercase (e.g. "Ali" not "ali")
        if (!Character.isUpperCase(username.charAt(0)))
            throw new Exception("Username must start with an uppercase letter");

        // Check no existing account uses this username
        boolean exists = eWalletSystem.getAccounts()
                .stream()
                .anyMatch(acc -> acc.getUsername().equals(username));

        if (exists)
            throw new Exception("Username already exists");
    }

    // ─── Password ─────────────────────────────────────────────────────────────

    @Override
    public void validatePassword(String password) throws Exception {
        if (password == null || password.length() < 6)
            throw new Exception("Password must be at least 6 characters");
    }

    // ─── Age ──────────────────────────────────────────────────────────────────

    @Override
    public void validateAge(int age) throws Exception {
        if (age < 18)
            throw new Exception("You must be at least 18 years old");
    }

    // ─── Phone ────────────────────────────────────────────────────────────────

    @Override
    public void validatePhone(String phone) throws Exception {
        // Must be exactly 11 digits (e.g. Egyptian mobile format)
        if (!phone.matches("\\d{11}"))
            throw new Exception("Phone number must be exactly 11 digits");

        // Check no existing account uses this phone
        boolean exists = eWalletSystem.getAccounts()
                .stream()
                .anyMatch(acc -> acc.getPhonenumber().equals(phone));

        if (exists)
            throw new Exception("Phone number is already registered");
    }

    // ─── Amount ───────────────────────────────────────────────────────────────

    @Override
    public void validateAmount(double amount) throws Exception {
        if (amount <= 0)
            throw new Exception("Amount must be greater than 0");
    }

    // ─── Null Check ───────────────────────────────────────────────────────────

    @Override
    public void checkAccountNotNull(Account account) throws Exception {
        if (account == null)
            throw new Exception("No account is currently logged in");
    }

    // ─── Deposit ──────────────────────────────────────────────────────────────

    @Override
    public void validateDepositAmount(double amount) throws Exception {
        // Minimum deposit is 100 (business rule)
        if (amount < 100)
            throw new Exception("Minimum deposit amount is 100");
    }

    // ─── Withdraw ─────────────────────────────────────────────────────────────

    @Override
    public void validateWithdrawAmount(double amount) throws Exception {
        if (amount <= 0)
            throw new Exception("Withdrawal amount must be greater than 0");
    }
}