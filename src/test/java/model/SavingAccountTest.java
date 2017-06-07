package model;

import static org.junit.jupiter.api.Assertions.*;

import DAO.AccountDaoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


class SavingAccountTest {
    private String pathToDB = "jdbc:sqlite:src/main/resources/Bank";
    private Connection connection;
    @Test
    void isSavingAccountInheritFromAbstractAccount() {
        assertTrue(SavingAccount.class.getSuperclass().equals(AbstractAccount.class));
    }

    @Test
    void isGetAllTakesListOfSavingAccounts() throws SQLException {
        connection = DriverManager.getConnection(pathToDB);
        List<SavingAccount> account = new ArrayList<SavingAccount>();
        assertTrue(account.getClass().equals(new AccountDaoImpl(connection).getAll().getClass()));
    }


}