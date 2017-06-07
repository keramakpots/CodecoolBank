package model;

import java.sql.Date;

public class Customer {

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Date createDate;
    private Integer isActive;
    private Date lastLogin;

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
    }
}
