
package com.bankingapplication;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SMART BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Check Balance");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Transfer Money");
            System.out.println("7. View Transaction History");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number from 1 to 8.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: {
                    System.out.print("Enter account number: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid account number.");
                        sc.nextLine();
                        break;
                    }

                    int accountNumber = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Enter opening balance: ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid balance.");
                        sc.nextLine();
                        break;
                    }

                    double balance = sc.nextDouble();
                    sc.nextLine();

                    if (accountNumber <= 0 || name.isEmpty()
                            || !Double.isFinite(balance) || balance < 0) {
                        System.out.println("Please enter valid details.");
                        break;
                    }

                    BankAccount account =
                            new BankAccount(accountNumber, name, balance);

                    bank.createAccount(account);
                    break;
                }

                case 2:
                    bank.viewAllAccounts();
                    break;

                case 3: {
                    System.out.print("Enter account number: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid account number.");
                        sc.nextLine();
                        break;
                    }

                    int accountNumber = sc.nextInt();
                    sc.nextLine();

                    BankAccount account = bank.findAccount(accountNumber);

                    if (account == null) {
                        System.out.println("Account not found.");
                    } else {
                        System.out.println("Customer: "
                                + account.getCustomerName());
                        System.out.printf("Balance: Rs. %.2f%n",
                                account.getBalance());
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter account number: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid account number.");
                        sc.nextLine();
                        break;
                    }

                    int accountNumber = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter deposit amount: ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        sc.nextLine();
                        break;
                    }

                    double amount = sc.nextDouble();
                    sc.nextLine();

                    bank.depositMoney(accountNumber, amount);
                    break;
                }

                case 5: {
                    System.out.print("Enter account number: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid account number.");
                        sc.nextLine();
                        break;
                    }

                    int accountNumber = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter withdrawal amount: ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        sc.nextLine();
                        break;
                    }

                    double amount = sc.nextDouble();
                    sc.nextLine();

                    bank.withdrawMoney(accountNumber, amount);
                    break;
                }

                case 6: {
                    System.out.print("Enter sender account number: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid account number.");
                        sc.nextLine();
                        break;
                    }

                    int sender = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter receiver account number: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid account number.");
                        sc.nextLine();
                        break;
                    }

                    int receiver = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter transfer amount: ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid amount.");
                        sc.nextLine();
                        break;
                    }

                    double amount = sc.nextDouble();
                    sc.nextLine();

                    bank.transferMoney(sender, receiver, amount);
                    break;
                }

                case 7:
                    bank.viewTransactions();
                    break;

                case 8:
                    System.out.println("Thank you for using Smart Banking!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Select 1 to 8.");
            }
        }
    }
}
