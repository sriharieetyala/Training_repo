package com.app.PaymentOps;

import com.app.dto.Account;

public class PaymentOps {

    public static void main(String args[]) {
        Account acct = new Account("342432424424", 1000.0);
        Account acct2 = new Account("443424324000", 250.0);

        System.out.println("account details - " + acct.getAccountNo() + " balance " + acct.getBalance());
        System.out.println("account details - " + acct2.getAccountNo() + " balance " + acct2.getBalance());

        PaymentOps obj = new PaymentOps();

        double amountToTransfer = 200.0;
        boolean success = obj.funstransfer(acct, acct2, amountToTransfer);

        System.out.println("transfer success: " + success);
        System.out.println("after transfer, from-account balance: " + acct.getBalance());
        System.out.println("after transfer, to-account balance:   " + acct2.getBalance());
    }

    public boolean funstransfer(Account acct, Account acct2, double amount) {
        if (acct == null || acct2 == null) {
            System.out.println("one of the accounts is null");
            return false;
        }

        if (amount <= 0) {
            System.out.println("invalid amount: " + amount);
            return false;
        }

        double fromBalance = acct.getBalance();
        String fromAccNo = acct.getAccountNo();
        String toAccNo = acct2.getAccountNo();

        System.out.println("Attempting transfer of " + amount + " from " + fromAccNo + " to " + toAccNo);
        System.out.println("From-account balance before transfer: " + fromBalance);
        System.out.println("To-account balance before transfer:   " + acct2.getBalance());

        if (fromBalance < amount) {
            System.out.println("Insufficient funds in account " + fromAccNo);
            return false;
        }

        acct.setBalance(fromBalance - amount);
        acct2.setBalance(acct2.getBalance() + amount);

        System.out.println("Transfer complete.");
        System.out.println("From-account balance after transfer: " + acct.getBalance());
        System.out.println("To-account balance after transfer:   " + acct2.getBalance());

        return true;
    }

    public boolean funstransfer(Account acct, Account acct2) {
        double defaultAmount = 100.0;
        return funstransfer(acct, acct2, defaultAmount);
    }
}
