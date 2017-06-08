package controller;

import DAO.AccountDaoImpl;
import DAO.CustomerDaoImpl;
import model.Account;
import model.AccountStatus;
import model.AccountType;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenageCustomersControllerTest {

    private MenageCustomersController menageCustomersController;
    private CustomerDaoImpl customerDaoImpl;
    private AccountDaoImpl accountDaoImpl;

    private String pathToDB = "jdbc:sqlite:src/main/resources/TestBank";
    private Connection connection;
    private SQLExecuteController sqlExecuteController = new SQLExecuteController();

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        accountDaoImpl = new AccountDaoImpl(connection);
        String[] args = {"--init-test-db"};
        sqlExecuteController.executeQuery(args, connection);
        menageCustomersController = new MenageCustomersController(connection);

    }


    @Test
    void testCreateNewCustomer() throws NoSuchAlgorithmException {
        menageCustomersController.createNewCustomer("Jan", "Sobieski", "jassobieski", "1234", 1);
        assertEquals("Jan", customerDaoImpl.findByLogin("jassobieski").getName());
    }

    @Test
    void testDeActivateCustomer() {
        Customer customer = customerDaoImpl.findByLogin("adam_malysz");
        menageCustomersController.blockAnAcount(customer.getID());
        assertEquals(customer.getIsActive().toString(), 0);

    }

    @Test
    void TestAddANewAccount() {
        AccountDaoImpl accountDaoImpl = new AccountDaoImpl(connection);
        int listSize = accountDaoImpl.getAll().size();
        Customer customer = customerDaoImpl.find(1);
        AccountType accountType = new AccountType(1, "Settlement account", "Normal basic account");
        AccountStatus accountStatus = new AccountStatus(1, "active", "Normal status");
        menageCustomersController.addANewAccount(customer, accountType, accountStatus, BigInteger.valueOf(1000), BigInteger.valueOf(100), 1);
        assertEquals(accountDaoImpl.getAll().size(), listSize + 1);

    }
    @Test
    void TestBlockAnAcount() {
        Account account = accountDaoImpl.find(1);

    }


    @Test
    void TestUnblockAnAcount() {
    }

}