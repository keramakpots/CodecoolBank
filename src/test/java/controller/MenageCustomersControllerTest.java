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

    private String pathToDB = "jdbc:sqlite:src/main/resources/Bank";
    private Connection connection;
    p


    @BeforeEach
    private void init() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        menageCustomersController = new MenageCustomersController(connection);
        accountDaoImpl = new AccountDaoImpl(connection);

    }

    @Test
    void testCreateNewCustomer() throws NoSuchAlgorithmException {
        menageCustomersController.createNewCustomer("Jan", "Sobieski", "jassobieski", "1234", 1);
        assertEquals(customerDaoImpl.findByLogin("jassobieski").getName(), "Jan");
    }

    @Test
    void testDeActivateCustomer() {
        Customer customer = customerDaoImpl.findByLogin("adam_malysz");
        menageCustomersController.blockAnAcount(customer.getId());
        assertEquals(customer.getIsActive().toString(), 0);

    }

    @Test
    void TestAddANewAccount() {
        AccountDaoImpl accountDaoImpl = new AccountDaoImpl(connection);
        int listsize = accountDaoImpl.getAll().size();

        Customer customer = customerDaoImpl.find(1);
        AccountType accountType = new AccountType(1, "Settlement account", "Normal basic account");
        AccountStatus accountStatus = new AccountStatus(1, "active", "Normal status");
        menageCustomersController.addANewAccount(customer, accountType, accountStatus, BigInteger.valueOf(1000), BigInteger.valueOf(100), 1);
        assertEquals(accountDaoImpl.getAll().size(), listsize + 1);

    }
    @Test
    void TestBlockAnAcount() {
        Account account = accountDaoImpl.
    }


    @Test
    void TestUnblockAnAcount() {
    }

}