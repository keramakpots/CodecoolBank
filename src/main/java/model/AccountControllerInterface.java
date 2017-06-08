package model;

import java.math.BigInteger;

public interface AccountControllerInterface {
    public void deposit(double amount);

    public void withdraw(double amount);

    public BigInteger getBalance();

    public int getAccountId();

}
