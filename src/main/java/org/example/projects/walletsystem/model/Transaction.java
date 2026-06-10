package org.example.walletsystem.model;

public class Transaction {
    private String type; // DEPOSIT, WITHDRAW, TRANSFER, LOGIN, SIGNUP
    private double amount;
    private String from;
    private String to;
    private String date;

    public Transaction(String type, double amount, String from, String to) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.date = java.time.LocalDateTime.now().toString();
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getDate() { return date; }
}

