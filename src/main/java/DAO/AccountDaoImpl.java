package DAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.AccountStatus;
import model.AccountType;
import model.Customer;

public class AccountDaoImpl implements AccountDao {

    private Connection connection;
    private AccountStatusDaoImpl accountStatusDao;
    private AccountTypeDaoImpl accountTypeDao;
    private CustomerDaoImpl customerDao;


    public AccountDaoImpl(Connection connection) {
        this.connection = connection;
        this.accountStatusDao = new AccountStatusDaoImpl(connection);
        this.accountTypeDao = new AccountTypeDaoImpl(connection);
        this.customerDao = new CustomerDaoImpl(connection);
    }

    public List<Account> getAll() {
        Statement stmt;
        ArrayList<Account> accountsList = new ArrayList();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts;");
            while (rs.next()) {
                Integer accountID = rs.getInt("AccountID");
                Integer customerID = rs.getInt("CustomerID");
                Customer customer = customerDao.find(customerID);
                String number = rs.getString("Number");
                Integer accountTypeID = rs.getInt("AccountTypeID");
                AccountType accountType = accountTypeDao.find(accountTypeID);
                Integer accountStatusID = rs.getInt("AccountStatusID");
                AccountStatus accountStatus = accountStatusDao.find(accountStatusID);
                Date openDate = Date.valueOf(rs.getString("OpenDate"));
                BigInteger balance = BigInteger.valueOf(rs.getInt("Balance"));
                BigInteger debitLine = BigInteger.valueOf(rs.getInt("DebitLine"));
                Integer interest = rs.getInt("Interest");
                Account account = new Account(accountID, customer, number,
                    accountType, accountStatus, openDate, balance, debitLine, interest);
                accountsList.add(account);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return accountsList;
    }

    public Account find(Integer id) {
        Statement stmt;
        Account account = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt
                .executeQuery("SELECT * FROM Accounts WHERE AccountID = '" + id + "';");
            Integer accountID = rs.getInt("AccountID");
            Integer customerID = rs.getInt("CustomerID");
            Customer customer = customerDao.find(customerID);
            String number = rs.getString("Number");
            Integer accountTypeID = rs.getInt("AccountTypeID");
            AccountType accountType = accountTypeDao.find(accountTypeID);
            Integer accountStatusID = rs.getInt("AccountStatusID");
            AccountStatus accountStatus = accountStatusDao.find(accountStatusID);
            Date openDate = Date.valueOf(rs.getString("OpenDate"));
            BigInteger balance = BigInteger.valueOf(rs.getInt("Balance"));
            BigInteger debitLine = BigInteger.valueOf(rs.getInt("DebitLine"));
            Integer interest = rs.getInt("Interest");
            account = new Account(accountID, customer, number, accountType, accountStatus, openDate,
                balance, debitLine, interest);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return account;
    }

    public void addAccount(Account account) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            Customer customer = account.getCustomer();
            String customerLogin = customer.getLogin();
            customer = customerDao.findByLogin(customerLogin);
            Integer accountTypeID = accountTypeDao.find(account.getAccountType().getName());
            Integer accountStatusID = accountStatusDao.find(account.getAccountStatus().getName());
            stmt.executeQuery(
                "INSERT INTO Accounts (CustomerID, Number, AccountTypeID, AccountStatusID, OpenDate, Balance, DebitLine, Interest) VALUES ('"
                    + customer.getID() + "','" + account.getNumber() + "','" + accountTypeID
                    + "','" + accountStatusID + "','" + account.getOpenDate() + "','" + account
                    .getBalance() + "','" + account.getDebitLine() + "','" + account
                    .getInterest() + "')");
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(Account account) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            Customer customer = account.getCustomer();
            Integer accountTypeID = accountTypeDao.find(account.getAccountType().getName());
            Integer accountStatusID = accountStatusDao.find(account.getAccountStatus().getName());
            stmt.executeQuery(
                "UPDATE Accounts SET CustomerID = '" + customer.getID() + "', Number = '"
                    + account.getNumber() + "', AccountTypeID = '" + accountTypeID
                    + "', AccountStatusID = '" + accountStatusID + "', OpenDate = '" + account
                    .getOpenDate() + "', Balance = '" + account.getBalance()
                    + "', DebitLine = '" + account.getDebitLine() + "', Interest = '" + account
                    .getInterest() + "' WHERE AccountID = '" + account.getAccountID() + "'");
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
    }
}
