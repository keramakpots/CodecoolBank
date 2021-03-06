package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Date createDate;
    private Integer isActive;
    private Date lastLogin;
    private List<Account> accountList;


    public Customer(Integer id, String firstName, String lastName, String login,
                    String password, Date createDate, Integer isActive, Date lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isActive = isActive;
        this.lastLogin = lastLogin;
        this.accountList = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String login,
        String password, Date createDate, Integer isActive, Date lastLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.createDate = createDate;
        this.isActive = isActive;
        this.lastLogin = lastLogin;
        this.accountList = new ArrayList<Account>();
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return firstName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getLogin() {
        return login;
    }

    public Integer getID() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }
}
