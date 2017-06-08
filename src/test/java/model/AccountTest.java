package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import DAO.AccountDaoImpl;
import controller.SQLExecuteController;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AccountTest {

    private String pathToDB = "jdbc:sqlite:src/main/resources/TestBank";
    private Connection connection;
    private AccountDaoImpl accountDao;
    private SQLExecuteController sqlExecuteController = new SQLExecuteController();

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        accountDao = new AccountDaoImpl(connection);
        String[] args = {"--init-test-db"};
        sqlExecuteController.executeQuery(args, connection);
    }

    @Test
    void isGetAllTakesListOfSavingAccounts() {
        List<Account> account = new ArrayList<Account>();
        assertTrue(account.getClass().equals(accountDao.getAll().getClass()));
    }

    @Test
    void isFindSavingAccountIsGivingCorrectObject(){
        AccountType accountType = new AccountType(1, "Settlement account", "Normal basic account");
        AccountStatus accountStatus = new AccountStatus(1, "active", "Normal status");
        Customer customer = new Customer(3, "Jadwiga", "Milecka", "jadzia", "81dc9bdb52d04dc20036dbd8313ed055", Date.valueOf("2017-05-02"), 1, Date.valueOf("2017-05-02"));
        Account accountTest = new Account(1, customer, "68 9348 1023 8136 4745 2775 5194", accountType, accountStatus, Date.valueOf("2017-05-02"),
            BigInteger.valueOf(2000), BigInteger.valueOf(500), 3);
        assertTrue(accountTest.getBalance().equals(accountDao.find(1).getBalance()));
    }
}