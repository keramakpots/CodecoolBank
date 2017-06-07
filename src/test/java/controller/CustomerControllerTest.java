package controller;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Created by oskar on 07.06.17.
 */
class CustomerControllerTest {

    @Test
    void testHashPassword() throws NoSuchAlgorithmException {
        String password = "1234";
        assertEquals("81dc9bdb52d04dc20036dbd8313ed055", customerController.HashPassword(password));
    }

    @Test
    void testValidatePassword() throws NoSuchAlgorithmException {
        CustomerController customerController = new CustomerController();
        assertEquals(customerController.validatePassword("adam_malysz", "1234").name, "Adam");
    }

}