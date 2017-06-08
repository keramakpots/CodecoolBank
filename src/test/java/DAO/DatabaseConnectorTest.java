package DAO;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DatabaseConnectorTest {

    private Connection connection;
    private DatabaseConnector connector;
    private final String PATH = "jdbc:sqlite:src/main/resources/TestBank";


    @Test
    void testConnect() throws SQLException {
        connector = new DatabaseConnector();
        connection = connector.connect(PATH);
        assertTrue(connection.isValid(5000));
    }

    @Test
    void testDisconnect() throws SQLException {
        connector = new DatabaseConnector();
        connection = connector.connect(PATH);
        DatabaseConnector.disconnect(connection);
        assertTrue(connection.isClosed());
    }

}