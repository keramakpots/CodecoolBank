package model;


import java.math.BigInteger;
import java.sql.Date;

public class Account {

    private Integer accountID;
    private Customer customer;
    private String number;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Date openDate;
    private java.math.BigInteger balance;
    private BigInteger debitLine;
    private Integer interest;

    public Account(Integer accountID, Customer customer, String number,
        AccountType accountType,
        AccountStatus accountStatus, Date openDate, BigInteger balance, BigInteger debitLine,
        Integer interest) {
        super();
        this.accountID = accountID;
        this.customer = customer;
        this.number = number;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.openDate = openDate;
        this.balance = balance;
        this.debitLine = debitLine;
        this.interest = interest;
    }

    public Account(Customer customer, String number,
                   AccountType accountType,
                   AccountStatus accountStatus, Date openDate, BigInteger balance, BigInteger debitLine,
                   Integer interest) {
        super();
        this.customer = customer;
        this.number = number;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.openDate = openDate;
        this.balance = balance;
        this.debitLine = debitLine;
        this.interest = interest;
    }

    public BigInteger getBalance() {
        return this.balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getNumber() {
        return number;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public BigInteger getDebitLine() {
        return debitLine;
    }

    public Integer getInterest() {
        return interest;
    }
}

