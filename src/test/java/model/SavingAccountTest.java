package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class SavingAccountTest {
    @Test
    void isSavingAccountInheritFromAbstractAccount() {
        assertTrue(SavingAccount.class.getSuperclass().equals(AbstractAccount.class));
    }
}