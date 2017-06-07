package model;

public interface Account {

    public void deposit(double amount);

    public void withdraw(double amount);

    public double getBalance();

    public int getAccountId();

}
