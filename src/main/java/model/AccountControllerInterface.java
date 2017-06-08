package model;

import java.math.BigInteger;

public interface AccountControllerInterface {

    public void deposit(BigInteger amount, Account account);

    public void withdraw(BigInteger amount, Account account);

}
