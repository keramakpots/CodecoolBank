package controller;

import DAO.AccountDaoImpl;
import DAO.CustomerDaoImpl;
import exceptions.AlreadyDisactivatedException;
import model.Account;
import model.AccountStatus;
import model.AccountType;
import model.Customer;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


/**
 * Created by oskar on 07.06.17.
 */
public class MenageCustomersController {
    private Connection connection;
    private CustomerDaoImpl customerDaoImpl;
    private AccountDaoImpl accountDaoImpl;

    public MenageCustomersController(Connection connection) {
        this.connection = connection;
        this.customerDaoImpl = new CustomerDaoImpl(connection);
        this.accountDaoImpl = new AccountDaoImpl(connection);
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

    public void addANewAccount(Customer customer,
                               AccountType accountType,
                               AccountStatus accountStatus, Date openDate, BigInteger balance, BigInteger debitLine,
                               Integer interest) {
        Random random = new Random();
        String number = String.format("%09d", random.nextInt(1000000000));
        Date date = getDate();
        Account account = new Account(customer, number, accountType, accountStatus, date, balance, debitLine, interest);
        accountDaoImpl.addAccount(account);

    }

    public void blockAnAcount(Integer AccountID) {
        Account account = accountDaoImpl.find(AccountID);
        if (account.getAccountStatus().equals()
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
