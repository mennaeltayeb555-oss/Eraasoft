package org.example.walletsystem.service.imp;

import org.example.walletsystem.model.Account;
import org.example.walletsystem.model.EWalletSystem;
import org.example.walletsystem.service.AccountService;

import java.util.Scanner;

public class AccountServiceImp implements AccountService {

    // This object holds all accounts in the system (like a fake database)
    private static EWalletSystem eWalletSystem = new EWalletSystem();

    // ================== SIGNUP ==================
    @Override
    public boolean creatAccount(Account account) {

        // Check if username already exists
        boolean isAccountExist = eWalletSystem.getAccounts()
                .stream()
                .anyMatch(acc -> acc.getUsername().equals(account.getUsername()));

        // If username exists → do not create account
        if (isAccountExist) {
            return false;
        }

        // Add new account to the list
        eWalletSystem.getAccounts().add(account);

        // Account created successfully
        return true;
    }

    // ================== LOGIN ==================
    @Override
    public  Account login(String username, String password) {

        // Check if there is an account with SAME username AND password
        return eWalletSystem.getAccounts()
                .stream()
                .filter(acc ->
                        acc.getUsername().equals(username) &&
                                acc.getPassword().equals(password)
                )
                .findFirst()
                .orElse(null);
    }
    // ================== deposite ==================
    @Override
    public void deposite(Account account, double amount) {
        // check if amount is valid
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }
        //هنا عايز اتاكد الاول الاكونت ده موجود عندي فعلا ولا لا//
        // check if amount is multiple of 100
        if (amount % 100 != 0) {
            System.out.println("Amount must be 100, 200, 300, ...");
            return;
        }
        // add amount to balance
        account.setBalance(account.getBalance() + amount);
    }
    // ================== withdraw ==================
    @Override
    public void withdraw(Account account, double amount) {
        if(amount<=0){
            System.out.println("Invalid amount");
            return;
        }
        if (account.getBalance() < amount){
            System.out.println("your balance not enough");
            return;
        }
        // minus amount of balance
        account.setBalance(account.getBalance() - amount);

    }
}