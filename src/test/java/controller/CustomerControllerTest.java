package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by oskar on 07.06.17.
 */
class CustomerControllerTest {

    private CustomerController customerController;

    private String pathToDB = "jdbc:sqlite:src/main/resources/Bank";
    private Connection connection;


    @BeforeEach
    private void init() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        customerController = new CustomerController(connection);
    }

    @Test
    void testHashPassword() throws NoSuchAlgorithmException {
        String password = "1234";
        assertEquals("81dc9bdb52d04dc20036dbd8313ed055", customerController.HashPassword(password));
    }

    @Test
    void testValidatePassword() throws NoSuchAlgorithmException {
        assertEquals(customerController.validatePassword("adam_malysz", "1234").getName(), "Adam");
    }

}