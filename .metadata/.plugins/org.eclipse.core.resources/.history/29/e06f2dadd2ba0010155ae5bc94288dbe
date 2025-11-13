package com.app.dto;

import java.util.Objects;

public class Beneficary {
    private String name;
    private String accountNo;

    public Beneficary(String name, String accountNo) {
        this.name = name;
        this.accountNo = accountNo;
    }

    public boolean validateBene(String name) {
        if (name != null && name.equals("james"))
            return false;
        else
            return true;
    }

    public String getName() {
        return name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beneficary that = (Beneficary) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(accountNo, that.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accountNo);
    }

    @Override
    public String toString() {
        return "Beneficary{name='" + name + "', accountNo='" + accountNo + "'}";
    }
}
