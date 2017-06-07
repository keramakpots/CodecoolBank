package model;


import java.math.BigInteger;
import java.sql.Date;

public class SavingAccount extends AbstractAccount {
    private Integer accountID;
    private Integer customerID;
    private String number;
    private Integer accountTypeID;
    private Integer accountStatusID;
    private Date openDate;
    private java.math.BigInteger balance;
    private BigInteger debitLine;
    private Integer interest;

    public SavingAccount(Integer accountID, Integer customerID, String number,
        Integer accountTypeID,
        Integer accountStatusID, Date openDate, BigInteger balance, BigInteger debitLine,
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
