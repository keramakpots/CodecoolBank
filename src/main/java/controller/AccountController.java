package controller;

import DAO.AccountDaoImpl;
import DAO.TransactionDaoImpl;
import DAO.TransactionStatusesDaoImpl;
import DAO.TransactionTypesDaoImpl;
import exceptions.NotEnoughMoneyException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import model.Account;
import model.AccountControllerInterface;
import model.Transaction;
import model.TransactionStatus;
import model.TransactionType;

public class AccountController implements AccountControllerInterface {

    private AccountDaoImpl accountDaoImpl;
    private TransactionTypesDaoImpl transactionTypesDaoImpl;
    private TransactionStatusesDaoImpl transactionStatusesDaoImpl;
    private TransactionDaoImpl transactionDaoImpl;

    public AccountController(Connection connection) {
        this.accountDaoImpl = new AccountDaoImpl(connection);
        this.transactionTypesDaoImpl = new TransactionTypesDaoImpl(connection);
        this.transactionStatusesDaoImpl = new TransactionStatusesDaoImpl(connection);
        this.transactionDaoImpl = new TransactionDaoImpl(connection);

    }

    public void deposit(double amount) {

    }

    public void withdraw(double amount) {

    }

    public void transferAccountToAccount(Integer accountID, String accountNumber, Integer amount,
        Integer transactionTypeID, String description) {
        Account account = accountDaoImpl.find(accountID);
        if (account.getBalance().compareTo(BigInteger.valueOf(amount)) == 1) {
            account.setBalance(account.getBalance().subtract(BigInteger.valueOf(amount)));
            accountDaoImpl.update(account);
            Account destinationAccount = accountDaoImpl.findByNumber(accountNumber);
            destinationAccount.setBalance(destinationAccount.getBalance().add(BigInteger.valueOf(amount)));
            accountDaoImpl.update(destinationAccount);
            Date date = Util.Date.getDate();
            TransactionType transactionType = transactionTypesDaoImpl.find(transactionTypeID);
            TransactionStatus transactionStatus = transactionStatusesDaoImpl.find(1);
            Transaction transaction = new Transaction(date, transactionType, BigInteger.valueOf(amount), description,
                transactionStatus, account, null, destinationAccount);
            transactionDaoImpl.add(transaction);
        } else {
            throw new NotEnoughMoneyException();
        }


    }
}