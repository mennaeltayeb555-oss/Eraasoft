package org.example.walletsystem.service.imp;

import org.example.walletsystem.model.Account;
import org.example.walletsystem.service.AccountService;
import org.example.walletsystem.service.ApplicationService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Controls the main application flow — menus, user input, and routing to services.
 * This is the "controller" layer: it takes input from the user and calls the right service.
 */
public class ApplicationServiceImp implements ApplicationService {

    // Handles all account-related operations (signup, login, deposit, etc.)
    private final AccountService accountService = new AccountServiceImp();

    // One shared Scanner instance for the entire class — avoids resource leaks
    private final Scanner scanner = new Scanner(System.in);

    // Tracks the currently logged-in account (null if no one is logged in)
    private Account currentAccount;

    // ─── Start / Main Menu ────────────────────────────────────────────────────

    @Override
    public void start() throws Exception {
        System.out.println("Welcome to Pay Now Wallet");

        int invalidAttempts = 0;

        while (true) {
            System.out.println("\nPlease Choose An Option:");
            System.out.println("1. Login    2. Signup    3. Exit");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter a valid number!");
                scanner.nextLine(); // Clear invalid input from buffer
                invalidAttempts++;

                // Block the user after 4 failed attempts
                if (invalidAttempts >= 4) {
                    System.out.println("Too many invalid attempts. Please contact admin.");
                    break;
                }
                continue;
            }

            switch (choice) {
                case 1 -> login();
                case 2 -> signup();
                case 3 -> {
                    System.out.println("Thank you for using Pay Now. Goodbye!");
                    return;
                }
                default -> {
                    System.out.println("❌ Invalid choice. Please choose 1, 2, or 3.");
                    invalidAttempts++;
                }
            }

            if (invalidAttempts >= 4) {
                System.out.println("Too many invalid attempts. Please contact admin.");
                break;
            }
        }
    }

    // ─── Signup ───────────────────────────────────────────────────────────────

    private void signup() throws Exception {
        System.out.println("Enter Username:");
        String username = scanner.next();

        System.out.println("Enter Password:");
        String password = scanner.next();

        int age;
        try {
            System.out.println("Enter Age:");
            age = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("❌ Invalid age input!");
            scanner.nextLine(); // Clear bad input
            return;
        }

        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.next();

        Account account = new Account(username, password, phoneNumber, age);
        boolean isCreated = accountService.createAccount(account);

        if (isCreated) {
            System.out.println("✅ Account created successfully!");
            currentAccount = account;
            mainProfile();
        }
    }

    // ─── Login ────────────────────────────────────────────────────────────────

    private void login() throws Exception {
        String username;
        String password;

        // Keep asking until valid username length is entered
        while (true) {
            System.out.println("Enter Username:");
            username = scanner.next();
            if (username.length() >= 3) break;
            System.out.println("❌ Username must be at least 3 characters");
        }

        // Keep asking until valid password length is entered
        while (true) {
            System.out.println("Enter Password:");
            password = scanner.next();
            if (password.length() >= 6) break;
            System.out.println("❌ Password must be at least 6 characters");
        }

        Account account = accountService.login(username, password);

        if (account != null) {
            System.out.println("✅ Login successful! Welcome, " + account.getUsername());
            currentAccount = account;
            mainProfile();
        } else {
            System.out.println("❌ Invalid username or password");
        }
    }

    // ─── Main Profile Menu ────────────────────────────────────────────────────

    private void mainProfile() throws Exception {
        while (true) {
            System.out.println("\n1. Deposit    2. Withdraw    3. Transfer    4. Show Details    5. Logout");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter a valid number!");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> deposit();
                case 2 -> withdraw();
                case 3 -> transfer();
                case 4 -> showDetails();
                case 5 -> {
                    System.out.println("Logged out successfully.");
                    currentAccount = null;
                    return;
                }
                default -> System.out.println("❌ Invalid choice");
            }
        }
    }

    // ─── Deposit ──────────────────────────────────────────────────────────────

    private void deposit() {
        double amount;
        try {
            // BUG FIX: Original code never read the amount from user (was always 0)
            System.out.println("Enter deposit amount:");
            amount = scanner.nextDouble();
            accountService.deposit(currentAccount, amount);
            System.out.println("✅ Deposit successful!");
            System.out.println("New balance: " + currentAccount.getBalance());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ─── Withdraw ─────────────────────────────────────────────────────────────

    private void withdraw() {
        double amount;
        try {
            System.out.println("Enter withdrawal amount:");
            amount = scanner.nextDouble();
            accountService.withdraw(currentAccount, amount);
            System.out.println("✅ Withdrawal successful!");
            System.out.println("New balance: " + currentAccount.getBalance());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ─── Transfer ─────────────────────────────────────────────────────────────

    private void transfer() {
        try {
            System.out.println("Enter recipient username:");
            String toUsername = scanner.next();

            System.out.println("Enter amount to transfer:");
            double amount = scanner.nextDouble();

            accountService.transfer(currentAccount, toUsername, amount);
            System.out.println("✅ Transfer successful!");
            System.out.println("New balance: " + currentAccount.getBalance());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ─── Show Account Details ─────────────────────────────────────────────────

    private void showDetails() {
        System.out.println("\n------ Account Details ------");
        System.out.println("Username : " + currentAccount.getUsername());
        System.out.println("Phone    : " + currentAccount.getPhonenumber());
        System.out.println("Age      : " + currentAccount.getAge());
        System.out.println("Balance  : " + currentAccount.getBalance());
        System.out.println("-----------------------------");
    }
}