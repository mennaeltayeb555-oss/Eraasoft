package org.example.walletsystem.service;

import org.example.walletsystem.model.Account;

/**
 * Defines all validation rules used across the e-wallet system.
 * Keeps validation logic separate from business logic (Single Responsibility Principle).
 */
public interface ValidationService {

    /** Validates username: min 3 chars, starts with uppercase, not already taken. */
    void validateUsername(String username) throws Exception;

    /** Validates password: minimum 6 characters. */
    void validatePassword(String password) throws Exception;

    /** Validates age: must be 18 or older. */
    void validateAge(int age) throws Exception;

    /** Validates phone: must be exactly 11 digits, not already registered. */
    void validatePhone(String phone) throws Exception;

    /** Generic amount check: must be greater than 0. */
    void validateAmount(double amount) throws Exception;

    /** Ensures the given account object is not null before any operation. */
    void checkAccountNotNull(Account account) throws Exception;

    /** Deposit-specific: minimum deposit is 100. */
    void validateDepositAmount(double amount) throws Exception;

    /** Withdraw-specific: amount must be greater than 0. */
    void validateWithdrawAmount(double amount) throws Exception;
}