package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import DAO.AccountDaoImpl;
import DAO.CustomerDaoImpl;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Account;
import model.AccountStatus;
import model.AccountType;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        customerDaoImpl = new CustomerDaoImpl(connection);
    }


    @Test
    void testCreateNewCustomer() throws NoSuchAlgorithmException {
        menageCustomersController.createNewCustomer("Jan", "Sobieski", "jassobieski", "1234", 1);
        assertEquals("Jan", customerDaoImpl.findByLogin("jassobieski").getName());
    }

    @Test
    void testDeActivateCustomer() {
        Customer customer = customerDaoImpl.findByLogin("adam_malysz");
        menageCustomersController.deActivateCustomer(customer.getID());
        customer.setIsActive(0);
        assertEquals(customer.getIsActive(), customerDaoImpl.find(1).getIsActive());
    }

    @Test
    void TestAddANewAccount() {
        int listSize = accountDaoImpl.getAll().size();
        Customer customer = customerDaoImpl.find(1);
        AccountType accountType = new AccountType(1, "Settlement account", "Normal basic account");
        AccountStatus accountStatus = new AccountStatus(1, "active", "Normal status");
        menageCustomersController.addANewAccount(customer, accountType, accountStatus, BigInteger.valueOf(1000), BigInteger.valueOf(100), 1);
        assertEquals(accountDaoImpl.getAll().size(), listSize + 1);

    }
    @Test
    void TestBlockAnAccount() {
        Account account = accountDaoImpl.find(1);
        menageCustomersController.blockAnAccount(account.getAccountID());
        AccountStatus accountStatus = new AccountStatus(3, "", "");
        account.setAccountStatus(accountStatus);
        assertEquals(account.getAccountStatus().getId(),
            accountDaoImpl.find(1).getAccountStatus().getId());
    }


    @Test
    void TestUnblockAnAccount() {
        Account account = accountDaoImpl.find(1);
        menageCustomersController.blockAnAccount(account.getAccountID());
        menageCustomersController.unblockAnAccount(account.getAccountID());
        AccountStatus accountStatus = new AccountStatus(1, "", "");
        account.setAccountStatus(accountStatus);
        assertEquals(account.getAccountStatus().getId(), accountDaoImpl.find(1).getAccountID());
    }

}