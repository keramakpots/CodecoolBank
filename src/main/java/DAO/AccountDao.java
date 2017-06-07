package DAO;

import java.sql.Connection;
import java.util.List;
import model.SavingAccount;

public interface AccountDao {

    List<SavingAccount> getAll();
    SavingAccount find();
}
