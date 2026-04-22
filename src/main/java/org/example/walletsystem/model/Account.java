package org.example.walletsystem.model;

public class Account {
    private String username;
    private String Password;
    private double balance;
    private String   phonenumber;
    private int age;

    public Account() {
    }

    public Account(String username, String password, String  phoneNumber, int age) {
        this.username = username;
        this.Password = password;
        this.balance = 0.0;
        this.phonenumber = phonenumber;
        this.age = age;
    }

    public Account(String username, String password) {
        this.username = username;
        Password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
