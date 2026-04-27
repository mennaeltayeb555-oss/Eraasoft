package org.example.walletsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user account in the e-wallet system.
 * Stores personal info, credentials, balance, and admin status.
 */
public class Account {

    private String username;
    private String password;
    private double balance;
    private String phoneNumber;
    private int age;
    private boolean isAdmin;
    private boolean isActive = true;

    // ─── Constructors ────────────────────────────────────────────────────────

    /** Default constructor (required for frameworks / serialization) */
    public Account() {}

    /**
     * Full constructor for creating a new user account.
     * Balance is always initialized to 0.0.
     */
    public Account(String username, String password, String phoneNumber, int age) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    /**
     * Minimal constructor — used for login lookup only.
     * Does NOT set phone or age.
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean isActive() {
        return isActive;
    }

    // ─── Getters & Setters ───────────────────────────────────────────────────

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getPhonenumber() { return phoneNumber; }
    public void setPhonenumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}