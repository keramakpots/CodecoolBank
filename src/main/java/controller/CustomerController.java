package controller;

import DAO.CustomerDaoImpl;
import exceptions.WrongPasswordException;
import model.Customer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;


public class CustomerController {

    private Connection connection;
    private CustomerDaoImpl customerDaoImpl;

    public CustomerController(Connection connection) {
        this.connection = connection;
        this.customerDaoImpl = new CustomerDaoImpl(connection);
    }


    public String HashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public Customer validatePassword(String login, String password) throws NoSuchAlgorithmException {
        Customer customer = customerDaoImpl.findByLogin(login);
        String hashedPass = HashPassword(password);
        if (hashedPass.equals(customer.getPassword())) {
            return customer;
        } else {
            throw new WrongPasswordException();
        }


    }
}
