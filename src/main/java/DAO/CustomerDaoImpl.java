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
            System.err.println(e.getClass().getName() + ":CustomerDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return customer;
    }

    public void update(Customer customer) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeQuery(
                    "UPDATE Customers SET FirstName = '"
                        + customer.getName() + "', LastName = '" + customer.getLastName()
                        + "', Login = '" + customer.getLogin() + "', Password = '" + customer
                        .getPassword()
                        + "', CreateDate = '" + customer.getCreateDate()
                        + "', IsActive = '" + customer.getIsActive() + "', LastLogin = '" + customer
                        .getLastLogin() + "' WHERE CustomerID = '" + customer.getID() + "'");
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":CustomerDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer customer) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            stmt.executeQuery(
                    "INSERT INTO Customers (FirstName, LastName, Login, Password, CreateDate, IsActive, LastLogin) VALUES ('"
                        + customer.getName() + "','" + customer.getLastName() + "','" + customer
                        .getLogin()
                        + "','" + customer.getPassword() + "','" + customer.getCreateDate() + "','"
                        + customer.getIsActive() + "','" + customer.getLastLogin() + "')");
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":CustomerDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
    }
}
