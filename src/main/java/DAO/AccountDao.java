package DAO;

import java.util.List;
import model.Account;

public interface AccountDao {

    List<Account> getAll();

    Account find(Integer id);
}
