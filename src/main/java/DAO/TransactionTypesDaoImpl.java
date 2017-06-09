package DAO;

import model.TransactionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionTypesDaoImpl {
    private Connection connection;

    public TransactionTypesDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public TransactionType find(Integer id) {
        Statement stmt;
        TransactionType transactionType = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt
                .executeQuery("SELECT * FROM TransactionTypes WHERE TransactionTypeID = '" + id + "';");
            String name = rs.getString("Name");
            String description = rs.getString("Description");
            transactionType = new TransactionType(id, name, description);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":TransactionTypeDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return transactionType;
    }
}
