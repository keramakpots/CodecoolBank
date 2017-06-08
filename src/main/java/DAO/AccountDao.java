package DAO;

import model.Account;

import java.util.List;

public interface AccountDao {

    List<Account> getAll();

    Account find(Integer id);
}
