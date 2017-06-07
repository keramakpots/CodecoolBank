package DAO;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.SavingAccount;

public class AccountDaoImpl implements AccountDao {
    private Connection connection;

    public AccountDaoImpl(Connection connection) {
        this.connection = connection;
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
                String number = rs.getString("Number");
                Integer accountTypeID = rs.getInt("AccountTypeID");
                Integer accountStatusID = rs.getInt("AccountStatusID");
                Date openDate = Date.valueOf(rs.getString("OpenDate"));

                BigInteger balance = BigInteger.valueOf(rs.getInt("Balance"));
                BigInteger debitLine = BigInteger.valueOf(rs.getInt("DebitLine"));
                Integer interest = rs.getInt("Interest");
                SavingAccount savingAccount = new SavingAccount(accountID, customerID, number, accountTypeID, accountStatusID, openDate, balance, debitLine, interest);
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

    public SavingAccount find() {
        return null;
    }
}
