package DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.SQLExecuteController;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionDaoImplTest {
    private String pathToDB = "jdbc:sqlite:src/main/resources/TestBank";
    private Connection connection;
    private TransactionDaoImpl transactionDao;
    private SQLExecuteController sqlExecuteController = new SQLExecuteController();

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        transactionDao = new TransactionDaoImpl(connection);
        String[] args = {"--init-test-db"};
        sqlExecuteController.executeQuery(args, connection);
    }

    @Test
    void TestIsFindGetsCorrectTransaction() {
        assertEquals(BigInteger.valueOf(100), transactionDao.find(1).getValue());
    }


}