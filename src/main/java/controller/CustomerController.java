package controller;

import exceptions.WrongPasswordException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CustomerController {

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
        Customer customer = new CustomerDaoImpl.findByLogin(login);
        String hashedPass = HashPassword(password);
        if (hashedPass == customer.password.toString()) {
            return customer;
        } else {
            throw new WrongPasswordException();
        }


    }
}
