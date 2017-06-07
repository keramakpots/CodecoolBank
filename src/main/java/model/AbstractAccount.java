package model;


import java.math.BigInteger;

public abstract class AbstractAccount implements Account {

    public void deposit(double amount) {

    }

    public void withdraw(double amount) {

    }
    public BigInteger getBalance() {
        return BigInteger.valueOf(0);
    }

    public int getAccountId() {
        return 0;
    }
}
