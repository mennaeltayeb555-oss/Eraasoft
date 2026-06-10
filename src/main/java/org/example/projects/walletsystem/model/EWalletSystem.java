package org.example.walletsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as the in-memory "database" for the e-wallet system.
 * Holds the wallet name and the list of all registered accounts.
 *
 * NOTE: In a real app, this would be replaced by a database (e.g. MySQL, PostgreSQL).
 */
public class EWalletSystem {

    // The name of the wallet product shown to users
    private final String name = "Pay Now";

    // In-memory list of all accounts — acts as a fake database
    private List<Account> accounts = new ArrayList<>();

    // ─── Constructor ─────────────────────────────────────────────────────────

    /**
     * Initializes the system and seeds a default admin account.
     */
    public EWalletSystem() {
        seedAdminAccount();
    }

    // ─── Private Helpers ─────────────────────────────────────────────────────

    /**
     * Creates and adds a default admin account on system startup.
     * This was previously misplaced inside Account.java — moved here where it belongs.
     */
    private void seedAdminAccount() {
        Account admin = new Account("IAM", "IAM123", "01000000000", 30);
        admin.setAdmin(true);
        accounts.add(admin);
    }

    // ─── Getters & Setters ───────────────────────────────────────────────────

    public String getName() { return name; }

    public List<Account> getAccounts() { return accounts; }

    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }
}