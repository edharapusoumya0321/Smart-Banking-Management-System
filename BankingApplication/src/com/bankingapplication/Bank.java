
package com.bankingapplication;

import java.util.ArrayList;

public class Bank {

    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // Create a new bank account
    public boolean createAccount(BankAccount account) {

        if (account.getAccountNumber() <= 0) {
            System.out.println("Account number must be positive.");
            return false;
        }

        if (account.getCustomerName() == null
                || account.getCustomerName().trim().isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return false;
        }

        if (account.getBalance() < 0
                || !Double.isFinite(account.getBalance())) {
            System.out.println("Invalid opening balance.");
            return false;
        }

        if (findAccount(account.getAccountNumber()) != null) {
            System.out.println("Account number already exists.");
            return false;
        }

        accounts.add(account);
        System.out.println("Account created successfully!");
        return true;
    }

    // Find an account using its account number
    public BankAccount findAccount(int accountNumber) {

        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

    // Display all bank accounts
    public void viewAllAccounts() {

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (BankAccount account : accounts) {
            System.out.println("----------------------------");
            System.out.println("Account Number: "
                    + account.getAccountNumber());
            System.out.println("Customer Name: "
                    + account.getCustomerName());
            System.out.printf("Balance: Rs. %.2f%n",
                    account.getBalance());
        }
    }

    // Deposit money
    public boolean depositMoney(int accountNumber, double amount) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        if (account.deposit(amount)) {
            transactions.add(new Transaction(
                    "Deposit",
                    amount,
                    "Deposit into account " + accountNumber
            ));

            System.out.println("Deposit successful!");
            System.out.printf("New balance: Rs. %.2f%n",
                    account.getBalance());
            return true;
        }

        System.out.println("Invalid deposit amount.");
        return false;
    }

    // Withdraw money
    public boolean withdrawMoney(int accountNumber, double amount) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        if (account.withdraw(amount)) {
            transactions.add(new Transaction(
                    "Withdrawal",
                    amount,
                    "Withdrawal from account " + accountNumber
            ));

            System.out.println("Withdrawal successful!");
            System.out.printf("Remaining balance: Rs. %.2f%n",
                    account.getBalance());
            return true;
        }

        System.out.println("Invalid amount or insufficient balance.");
        return false;
    }

    // Transfer money between two accounts
    public boolean transferMoney(int senderNumber,
                                 int receiverNumber,
                                 double amount) {

        BankAccount sender = findAccount(senderNumber);
        BankAccount receiver = findAccount(receiverNumber);

        if (sender == null || receiver == null) {
            System.out.println("Sender or receiver account not found.");
            return false;
        }

        if (senderNumber == receiverNumber) {
            System.out.println("Cannot transfer to the same account.");
            return false;
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            System.out.println("Transfer amount must be positive.");
            return false;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return false;
        }

        if (!sender.withdraw(amount)) {
            System.out.println("Transfer failed.");
            return false;
        }

        if (!receiver.deposit(amount)) {
            sender.deposit(amount);
            System.out.println("Transfer failed.");
            return false;
        }

        transactions.add(new Transaction(
                "Transfer",
                amount,
                "From account " + senderNumber
                        + " to account " + receiverNumber
        ));

        System.out.println("Transfer successful!");
        System.out.printf("Sender balance: Rs. %.2f%n",
                sender.getBalance());
        System.out.printf("Receiver balance: Rs. %.2f%n",
                receiver.getBalance());

        return true;
    }

    // Display transaction history
    public void viewTransactions() {

        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }
}
