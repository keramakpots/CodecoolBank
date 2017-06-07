package controller;

import DAO.CustomerDaoImpl;
import exceptions.AlreadyDisactivatedException;
import model.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by oskar on 07.06.17.
 */
public class MenageCustomersController {
    private Connection connection;
    private CustomerDaoImpl customerDaoImpl;

    public MenageCustomersController(Connection connection) {
        this.connection = connection;
        this.customerDaoImpl = new CustomerDaoImpl(connection);
    }

    private Date getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal));
    }

    public void createNewCustomer(String firstName, String lastName, String login, String password, Integer isActive) {
        Date createDate = getDate();
        Date lastLogin = null;
        Customer customer = new Customer(firstName, lastName, login, password, createDate, isActive, lastLogin);
        customerDaoImpl.addCustomer(customer);
    }

    public void deActivateCustomer(Integer customerID) {
        Customer customer = customerDaoImpl.find(customerID);
        if (customer.getIsActive().equals(1)) {
            customer.setIsActive(0);
            customerDaoImpl.update(customer);
        } else {
            throw new AlreadyDisactivatedException();
        }

    }

    public void addANewAccount() {
    }

    public void blockAnAcount() {
    }

    public void unblockAnAcount() {
    }

    public void AddANewCard() {
    }

    public void BlockACard() {
    }

    public void UnblockACard() {
    }


}
