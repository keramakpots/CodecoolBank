package model;

import java.math.BigInteger;

public interface AccountInterface {

    void deposit(double amount);

    void withdraw(double amount);

    BigInteger getBalance();

    int getAccountId();

}
