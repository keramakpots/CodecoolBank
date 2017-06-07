package DAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.AccountStatus;
import model.AccountType;
import model.Customer;
import model.SavingAccount;

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

    public List<SavingAccount> getAll() {
        Statement stmt;
        ArrayList<SavingAccount> savingAccountsList = new ArrayList();
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
                SavingAccount savingAccount = new SavingAccount(accountID, customer, number, accountType, accountStatus, openDate, balance, debitLine, interest);
                savingAccountsList.add(savingAccount);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return savingAccountsList;
    }

    public SavingAccount find(Integer id) {
        Statement stmt;
        SavingAccount savingAccount = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts WHERE AccountID = '"+id+"';");
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
            savingAccount = new SavingAccount(accountID, customer, number, accountType, accountStatus, openDate, balance, debitLine, interest);
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":AccountDaoImpl " + e.getMessage());
            e.printStackTrace();
        }
        return savingAccount;
    }
}
