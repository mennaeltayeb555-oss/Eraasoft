package org.example.walletsystem.service.imp;

import org.example.walletsystem.model.Account;
import org.example.walletsystem.service.AccountService;
import org.example.walletsystem.service.ApplicationService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationServiceImp implements ApplicationService {

    // Service object used to call business logic (signup, login)
    private AccountService accountService = new AccountServiceImp();

    // Use ONE scanner for the whole class (no need to create many objects)
    private Scanner scanner = new Scanner(System.in);
    private Account currentAccount;

    @Override
    public void start() {

        System.out.println("Welcome to Pay Now Wallet");

        int counter = 0;

        while (true) {
            System.out.println("Please Choose An Option");
            System.out.println("1.Login    2.Signup    3.Exit");

            int choice = 0;
            boolean exit = false;

            // Handle wrong input (like letters instead of numbers)
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // clear invalid input
                counter++;
                continue;
            }

            switch (choice) {
                case 1:
                    login();
                    break;

                case 2:
                    signup();
                    break;

                case 3:
                    System.out.println("Ok, thank you");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice");
                    counter++;
            }

            if (exit) break;

            if (counter == 4) {
                System.out.println("Too many invalid attempts. Contact admin.");
                break;
            }
        }
    }

    // ================== SIGNUP ==================
    private void signup() {

        String username;
        String password;
        int age = 0;
        String phoneNumber;

        // -------- Username validation --------
        while (true) {
            System.out.println("Enter Username:");
            username = scanner.next();

            if (username.length() >= 3) break;

            System.out.println("Username must be at least 3 characters");
        }

        // -------- Password validation --------
        while (true) {
            System.out.println("Enter Password:");
            password = scanner.next();

            if (password.length() >= 6) break;

            System.out.println("Password must be at least 6 characters");
        }

        // -------- Age validation --------
        while (true) {
            try {
                System.out.println("Enter Age:");
                age = scanner.nextInt();

                if (age >= 18) break;

                System.out.println("Age must be 18 or older");

            } catch (Exception e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine(); // VERY IMPORTANT (clear buffer)
            }
        }

        // -------- Phone validation --------
        while (true) {
            System.out.println("Enter Phone Number:");
            phoneNumber = scanner.next();

            // check if only digits and length = 11
            if (phoneNumber.matches("\\d{11}")) break;

            System.out.println("Phone must be 11 digits");
        }

        // Create account object with user data
        Account account = new Account(username, password, phoneNumber, age);

        // Call service to create account
        boolean isCreated = accountService.creatAccount(account);

        if (isCreated) {
            System.out.println("✅ Signup successful!");
            currentAccount = account;
            mainProfile(); // go to next screen
        } else {
            System.out.println("❌ Username already exists!");
        }
    }

    // ================== LOGIN ==================
    private void login() {

        String username;
        String password;

        while (true) {
            System.out.println("Enter Username:");
            username = scanner.next();
            if (username.length() >= 3) break;
            System.out.println("Username must be at least 3 characters");
        }

        while (true) {
            System.out.println("Enter Password:");
            password = scanner.next();
            if (password.length() >= 6) break;
            System.out.println("Password must be at least 6 characters");
        }

        Account account = accountService.login(username, password);

        if (account != null) {
            System.out.println("✅ Login successful!");
            currentAccount = account;
            mainProfile();
        } else {
            System.out.println("❌ Invalid username or password");
        }
    }
    // ================== mainProfile ==================
    public void mainProfile() {
        while (true) {
            System.out.println("1.Deposit    2.Withdraw    3.Logout");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    // ================== deposit ==================
    private void deposit() {

        System.out.println("Enter amount:");

        double amount = 0;

        try {
            amount = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
            return;
        }
////////here is current account
        accountService.deposite(currentAccount, amount);

        System.out.println("✅ Balance updated!");
        System.out.println("Your balance: " + currentAccount.getBalance());
    }
    // ================== withdraw ==================
    private void withdraw(){
        System.out.println("Enter amount:");

        double amount = 0;
        try {
            amount = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
            return;
        }
        accountService.withdraw(currentAccount, amount);

        System.out.println("✅ Balance updated!");
        System.out.println("Your balance: " + currentAccount.getBalance());
    }
}