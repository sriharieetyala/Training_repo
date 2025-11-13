package com.app.dto;

public class Account {
    private String acctno;
    private double acctbalance;

    public Account(String acctno, double acctbalance) {
        this.acctno = acctno;
        this.acctbalance = acctbalance;
    }

    public String getAccountNo() {
        return acctno;
    }

    public double getBalance() {
        return acctbalance;
    }

    public void setBalance(double balance) {
        this.acctbalance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + acctno + ", balance=" + acctbalance + "}";
    }
}
