package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.AccountStatus;

public class AccountStatusDaoImpl {
    private Connection connection;

    public AccountStatusDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public AccountStatus find(Integer id) {
        Statement stmt;
        AccountStatus accountStatus = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AccountStatuses WHERE AccountStatusID = '"+id+"';");
            String name = rs.getString("Name");
            String description = rs.getString("Description");
            accountStatus = new AccountStatus(id, name, description);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountStatusDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return accountStatus;
    }
}
