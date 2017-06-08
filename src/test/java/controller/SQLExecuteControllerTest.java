package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class SQLExecuteControllerTest {

    private String pathToDB = "jdbc:sqlite:src/main/resources/TestBank";
    private Connection connection;
    private SQLExecuteController sqlExecuteController = new SQLExecuteController();


    @Test
    void testWhereIsNoArgsInSQLExecuter() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        String[] args = {};
        sqlExecuteController.executeQuery(args, connection);
    }

    @Test
    void testWithMigrateArgument() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        String[] args = {"--migrate-db"};
        sqlExecuteController.executeQuery(args, connection);
    }

    @Test
    void testWithInitArgument() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        String[] args = {"--init-db"};
        sqlExecuteController.executeQuery(args, connection);
    }
}