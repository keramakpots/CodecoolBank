package DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Customer;

public class CustomerDaoImpl {

    private Connection connection;

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Customer find(Integer id) {
        Statement stmt;
        Customer customer = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt
                .executeQuery("SELECT * FROM Customers WHERE CustomerID = '" + id + "';");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String login = rs.getString("Login");
            String password = rs.getString("Password");
            Date createDate = Date.valueOf(rs.getString("CreateDate"));
            Integer isActive = rs.getInt("IsActive");
            Date lastLogin = Date.valueOf(rs.getString("LastLogin"));
            customer = new Customer(id, firstName, lastName, login, password, createDate, isActive,
                lastLogin);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return customer;
    }

    public Customer findByLogin(String login) {
        Statement stmt;
        Customer customer = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt
                .executeQuery("SELECT * FROM Customers WHERE Login = '" + login + "';");
            Integer id = rs.getInt("CustomerID");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String password = rs.getString("Password");
            Date createDate = Date.valueOf(rs.getString("CreateDate"));
            Integer isActive = rs.getInt("IsActive");
            Date lastLogin = Date.valueOf(rs.getString("LastLogin"));
            customer = new Customer(id, firstName, lastName, login, password, createDate, isActive,
                lastLogin);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return customer;
    }
}
