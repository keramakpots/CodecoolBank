package model;

import java.math.BigInteger;
import java.util.Date;

public class Transaction {

    private Integer id;
    private Date dateOfTransaction;
    private TransactionType transactionType;
    private BigInteger value;
    private String description;
    private TransactionStatus transactionStatus;
    private Account baseAccount;
    private Card card = null;
    private Account destinationAccount;

    public Transaction(Integer id, Date dateOfTransaction, TransactionType transactionType,
        BigInteger value, String description, TransactionStatus transactionStatus,
        Account baseAccount, Card card,
        Account destinationAccount) {
        this.id = id;
        this.dateOfTransaction = dateOfTransaction;
        this.transactionType = transactionType;
        this.value = value;
        this.description = description;
        this.transactionStatus = transactionStatus;
        this.baseAccount = baseAccount;
        this.card = card;
        this.destinationAccount = destinationAccount;
    }

    public Transaction(Date dateOfTransaction, TransactionType transactionType,
        BigInteger value, String description, TransactionStatus transactionStatus,
        Account baseAccount, Card card,
        Account destinationAccount) {
        this.dateOfTransaction = dateOfTransaction;
        this.transactionType = transactionType;
        this.value = value;
        this.description = description;
        this.transactionStatus = transactionStatus;
        this.baseAccount = baseAccount;
        this.card = card;
        this.destinationAccount = destinationAccount;
    }

    public BigInteger getValue() {
        return value;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public Account getBaseAccount() {
        return baseAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public String getDescription() {
        return description;
    }

    public Card getCard() {
        return card;
    }
}
