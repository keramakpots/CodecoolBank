package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.TransactionStatus;

public class TransactionStatusesDaoImpl{
    private Connection connection;

    public TransactionStatusesDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public TransactionStatus find(Integer id) {
        Statement stmt;
        TransactionStatus transactionStatus = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt
                .executeQuery("SELECT * FROM TransactionStatuses WHERE TransactionStatusID = '" + id + "';");
            String name = rs.getString("Name");
            String description = rs.getString("Description");
            transactionStatus = new TransactionStatus(id, name, description);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":TransactionStatusDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return transactionStatus;
    }
}
