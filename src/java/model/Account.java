package model;

import constant.AccountStatus;
import constant.UserRole;
import java.time.LocalDateTime;

public class Account {
    private Integer accountId;
    private String userName;
    private String password;
    private UserRole role;
    private AccountStatus status;
    private LocalDateTime createdAt;

    // Constructor mặc định
    public Account() {
        this.status = AccountStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor đầy đủ
    public Account(Integer accountId, String userName, String password,
                   UserRole role, AccountStatus status, LocalDateTime createdAt) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Constructor không có ID (để INSERT)
    public Account(String userName, String password, UserRole role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = AccountStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public AccountStatus getStatus() { return status; }
    public void setStatus(AccountStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    
}
