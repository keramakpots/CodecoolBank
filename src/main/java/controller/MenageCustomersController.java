package controller;

import DAO.AccountDaoImpl;
import DAO.AccountStatusDaoImpl;
import DAO.CustomerDaoImpl;
import exceptions.AlreadyDisactivatedException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import model.Account;
import model.AccountStatus;
import model.AccountType;
import model.Customer;


public class MenageCustomersController {
    private Connection connection;
    private CustomerDaoImpl customerDaoImpl;
    private AccountDaoImpl accountDaoImpl;
    private AccountStatusDaoImpl accountStatusDaoImpl;

    public MenageCustomersController(Connection connection) {
        this.connection = connection;
        this.customerDaoImpl = new CustomerDaoImpl(connection);
        this.accountDaoImpl = new AccountDaoImpl(connection);
        this.accountStatusDaoImpl = new AccountStatusDaoImpl(connection);
    }

    private Date getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        String formatted = dateFormat.format(cal.getTime());
        return Date.valueOf(formatted);
    }

    public void createNewCustomer(String firstName, String lastName, String login, String password, Integer isActive) throws NoSuchAlgorithmException {
        Date createDate = getDate();
        Date lastLogin = getDate();
        String hashedPass = CustomerController.HashPassword(password);
        Customer customer = new Customer(firstName, lastName, login, hashedPass, createDate, isActive, lastLogin);
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
                               AccountStatus accountStatus, BigInteger balance, BigInteger debitLine,
                               Integer interest) {
        Random random = new Random();
        String number = String.format("%09d", random.nextInt(1000000000));
        Date date = getDate();
        Account account = new Account(customer, number, accountType, accountStatus, date, balance, debitLine, interest);
        accountDaoImpl.addAccount(account);

    }

    public void blockAnAcount(Integer AccountID) {
        Account account = accountDaoImpl.find(AccountID);
        if (account.getAccountStatus().equals(accountStatusDaoImpl.find(1))) {
            account.setAccountStatus(accountStatusDaoImpl.find(3));
            accountDaoImpl.update(account);
        } else {
            throw new AlreadyDisactivatedException();
        }
    }

    public void unblockAnAcount(Integer AccountID) {
        Account account = accountDaoImpl.find(AccountID);
        if (account.getAccountStatus().equals(accountStatusDaoImpl.find(3))) {
            account.setAccountStatus(accountStatusDaoImpl.find(1));
            accountDaoImpl.update(account);
        } else {
            throw new AlreadyDisactivatedException();
        }
    }

//    public void AddANewCard() {
//
//    }
//
//    public void BlockACard() {
//    }
//
//    public void UnblockACard() {
//    }
//

}
