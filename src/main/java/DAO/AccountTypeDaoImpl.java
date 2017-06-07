package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.AccountType;


public class AccountTypeDaoImpl {

    private Connection connection;

    public AccountTypeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public AccountType find(Integer id) {
        Statement stmt;
        AccountType accountType = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt
                .executeQuery("SELECT * FROM AccountTypes WHERE AccountTypeID = '" + id + "';");
            String name = rs.getString("Name");
            String description = rs.getString("Description");
            accountType = new AccountType(id, name, description);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountTypeDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return accountType;
    }
}
