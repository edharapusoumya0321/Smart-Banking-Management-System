
package com.bankingapplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private String description;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    public void displayTransaction() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Type: " + type);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Description: " + description);
        System.out.println("Date: " + dateTime.format(formatter));
        System.out.println("----------------------------");
    }
}
