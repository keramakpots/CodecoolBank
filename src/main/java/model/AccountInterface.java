package model;

import java.math.BigInteger;

public interface AccountInterface {
    public void deposit(double amount);

    public void withdraw(double amount);

    public BigInteger getBalance();

    public int getAccountId();

}
