package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MenageCustomersControllerTest {

    private MenageCustomersController menageCustomersController;

    private String pathToDB = "jdbc:sqlite:src/main/resources/Bank";
    private Connection connection;


    @BeforeEach
    private void init() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        menageCustomersController = new MenageCustomersController(connection);
    }

    @Test
    void testCreateNewCustomer() {
        menageCustomersController.createNewCustomer("Jan", "Sobieski", "jassobieski", "1234", 1);
    }

    @Test
    void testDeActivateCustomer() {
    }

    @Test
    void TestAddANewAccount() {
    }

    @Test
    void TestBlockAnAcount() {
    }

    @Test
    void TestUnblockAnAcount() {
    }

}