package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exceptions.WrongPasswordException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void testValidatePasswordThrowsWrongPasswordException() throws NoSuchAlgorithmException {
        assertThrows(WrongPasswordException.class, () -> {
            customerController.validatePassword("adam_malysz", "12345").getName();
        });
    }

    @Test
    void testOfGetAccountsList() {
        assertEquals(1, customerController.getAccountsList(1).size());
    }
}