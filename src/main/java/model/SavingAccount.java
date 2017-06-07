package model;


import java.math.BigInteger;
import java.sql.Date;

public class SavingAccount extends AbstractAccount {
    private Integer accountID;
    private Customer customerID;
    private String number;
    private AccountType accountTypeID;
    private AccountStatus accountStatusID;
    private Date openDate;
    private java.math.BigInteger balance;
    private BigInteger debitLine;
    private Integer interest;

    public SavingAccount(Integer accountID, Customer customer, String number,
        AccountType accountType,
        AccountStatus accountStatus, Date openDate, BigInteger balance, BigInteger debitLine,
        Integer interest) {
        super();
        this.accountID = accountID;
        this.customerID = customerID;
        this.number = number;
        this.accountTypeID = accountTypeID;
        this.accountStatusID = accountStatusID;
        this.openDate = openDate;
        this.balance = balance;
        this.debitLine = debitLine;
        this.interest = interest;
    }
}
