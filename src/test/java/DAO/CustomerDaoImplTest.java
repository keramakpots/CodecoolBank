package DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.SQLExecuteController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerDaoImplTest {

    private String pathToDB = "jdbc:sqlite:src/main/resources/TestBank";
    private Connection connection;
    private CustomerDaoImpl customerDao;
    private SQLExecuteController sqlExecuteController = new SQLExecuteController();
    private Customer customer;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        customerDao = new CustomerDaoImpl(connection);
        String[] args = {"--init-test-db"};
        sqlExecuteController.executeQuery(args, connection);
        List<Account> accounts = new ArrayList<>();
        customer = new Customer(6, "Nowy", "Polski", "Wiesio", "81dc9bdb52d04dc20036dbd8313ed055",
            Date.valueOf("2015-12-15"), 1, Date.valueOf("2016-12-12"));
    }


    @Test
    void isCorrectUpdateCustomerWorks() {
        customerDao.addCustomer(customer);
        assertEquals("Nowy", customerDao.find(6).getName());
    }

    @Test
    void isAddCustomerWorks() {
        customerDao.addCustomer(customer);
        customer.setIsActive(0);
        customerDao.update(customer);
        assertEquals(customer.getIsActive(), customerDao.find(6).getIsActive());
    }

}