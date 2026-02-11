package model;

import constant.UserRole;

public class Account {

    private int accountId;
    private String username;
    private String password;
    private UserRole role;    // ENUM
    private String status;    // Active / Inactive (KHÔNG enum)

    // getters & setters

    public Account() {
    }

    public Account(int accountId, String username, String password, UserRole role, String status) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}