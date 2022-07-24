
package com.mp3.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {

    int accountNumber;
    String name;
    double balance;
    public static double maintainingBalance = 1000;
    public static double penalty = 1.0;
    int penaltyCounter;

    public Account(String uname) {
        // Hard-coded data for user "mp3username"
        if (uname.equals("mp3username")) {
            accountNumber = 13244510;
            name = "Juan Dela Cruz";
            balance = 55320.52;
            penaltyCounter = 0;
        }
    }

    public Account() {
        // creates an account for "mp3username"
        this("mp3username");
    }

    public int deposit(double amt) {
        double amount = Math.abs(amt);
        balance = round(balance + amount);
        checkForPenalty();
        return 1;
    }

    public int withdraw(double amt) {
        double amount = Math.abs(amt);
        if ((balance - amount) >= maintainingBalance) { //balance is above maintaining
            balance = round(balance - amount);
            return 1;
        } else { //balance is below maintaining
            if ((balance - amount) < 0) { //Insufficient balance
                return -1;
            } else { //Due for penalty
                penaltyCounter++;
                balance = round((balance - amount) - penalty);
                return 0;
            }
        }
    }

    public double getBalance() {
        checkForPenalty();
        return balance;
    }

    public int checkForPenalty() {
        if (balance >= maintainingBalance) {
            penaltyCounter = 0; //Reset penalty counter when balance is greater than maintaining
        }
        return penaltyCounter;
    }

    public static void updateMaintainingBalance(double newMaintaing) {
        maintainingBalance = newMaintaing;
    }

    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
