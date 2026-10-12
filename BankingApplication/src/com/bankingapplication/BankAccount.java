
package com.bankingapplication;

public class BankAccount {

    private int accountNumber;
    private String customerName;
    private double balance;

    public BankAccount(int accountNumber, String customerName,
                       double openingBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = openingBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance = balance + amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance = balance - amount;
        return true;
    }
}
