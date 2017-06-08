package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import DAO.AccountDaoImpl;
import exceptions.NotEnoughMoneyException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountControllerTest {

    private AccountController accountController;
    private AccountDaoImpl accountDao;
    private String pathToDB = "jdbc:sqlite:src/main/resources/TestBank";
    private Connection connection;
    private SQLExecuteController sqlExecuteController = new SQLExecuteController();

    @BeforeEach
    void prepareDb() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        String[] args = {"--init-test-db"};
        sqlExecuteController.executeQuery(args, connection);
        accountDao = new AccountDaoImpl(connection);
        accountController = new AccountController(connection);

    }

    @Test
    void transferAccountToAccountWorksCorrectlyWithStandardValues() {
        accountController.transferAccountToAccount(1, "89 9523 1037 3412 5746 9331 3189",
            200, 2, "SomeTransfer");
        assertEquals(BigInteger.valueOf(8000),
            accountDao.findByNumber("89 9523 1037 3412 5746 9331 3189").getBalance());
    }

    @Test
    void IsTransferMethodThrowsNotEnoughMoneyException() {
        assertThrows(NotEnoughMoneyException.class, () -> {
            accountController.transferAccountToAccount(1, "89 9523 1037 3412 5746 9331 3189",
                2000000, 2, "SomeTransfer");
        });
    }

    @Test
    void testIsDepositAddMoneyToAnAccount() {
        Account testAccount = accountDao.find(1);
        accountController.deposit(BigInteger.valueOf(100), testAccount);
        assertEquals(BigInteger.valueOf(2100), accountDao.find(1).getBalance());
    }

    @Test
    void testOfWithdrawSubtractMoneyFromAnAccount() {
        Account testAccount = accountDao.find(1);
        accountController.withdraw(BigInteger.valueOf(100), testAccount);
        assertEquals(BigInteger.valueOf(1900), accountDao.find(1).getBalance());
    }

    @Test
    void testIfWithdrawSubtractMoneyFromAnAccountThrowException() {
        Account testAccount = accountDao.find(1);
        assertThrows(NotEnoughMoneyException.class, () -> {
            accountController.withdraw(BigInteger.valueOf(100000), testAccount);
        });
    }
}