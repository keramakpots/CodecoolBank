package DAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Account;
import model.Card;
import model.Transaction;
import model.TransactionStatus;
import model.TransactionType;

public class TransactionDaoImpl {
    private Connection connection;
    private TransactionStatusesDaoImpl transactionStatusesDao;
    private TransactionTypesDaoImpl transactionTypesDao;
    private AccountDaoImpl accountDao;

    public TransactionDaoImpl(Connection connection) {
        this.connection = connection;
        this.transactionStatusesDao = new TransactionStatusesDaoImpl(connection);
        this.transactionTypesDao = new TransactionTypesDaoImpl(connection);
        this.accountDao = new AccountDaoImpl(connection);
    }

    public Transaction find(Integer id) {
        Statement stmt;
        Transaction transaction = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM Transactions WHERE TransactionID = '" + id + "';");
            Date date = Date.valueOf(rs.getString("DateOfTransaction"));
            String description = rs.getString("Description");
            Integer transactionTypeID = rs.getInt("TransactionTypeID");
            TransactionType transactionType = transactionTypesDao.find(transactionTypeID);
            Integer transactionStatusID = rs.getInt("TransactionStatusID");
            TransactionStatus transactionStatus = transactionStatusesDao.find(transactionStatusID);
            BigInteger value = BigInteger.valueOf(rs.getInt("Value"));
            Integer sourceAccountID = rs.getInt("SourceAcountID");
            Account sourceAccount = accountDao.find(sourceAccountID);
            Integer destinationAccountID = rs.getInt("DestinationAccountID");
            Account destinationAccount = accountDao.find(destinationAccountID);
//            Integer SourceCardID = rs.getInt("SourceCardID");
            Card card = new Card();
            transaction = new Transaction(id, date, transactionType, value, description, transactionStatus, sourceAccount, card, destinationAccount);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":TransactionDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return transaction;
    }
}
