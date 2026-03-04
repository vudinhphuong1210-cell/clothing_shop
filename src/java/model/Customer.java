package model;

import constant.Gender;
import java.time.LocalDateTime;

public class Customer {
    private Integer customerId;
    private Integer accountId;
    private String fullName;
    private String phone;
    private Gender gender;
    private String address;
    private Integer point;
    private LocalDateTime createdAt;

    // ✅ Thêm field account để JSP dùng ${c.account.status},
    // ${c.account.userName}...
    private Account account;

    // Constructor mặc định
    public Customer() {
        this.point = 0;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor đầy đủ
    public Customer(Integer customerId, Integer accountId, String fullName, String phone,
            Gender gender, String address, Integer point, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.point = point;
        this.createdAt = createdAt;
    }

    // Constructor không có ID (để INSERT)
    public Customer(Integer accountId, String fullName, String phone,
            Gender gender, String address) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.point = 0;
        this.createdAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Delegate email to account to avoid massive JSP refactoring
    public String getEmail() {
        return account != null ? account.getEmail() : null;
    }

    public void setEmail(String email) {
        if (account != null)
            account.setEmail(email);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // ✅ Getter/Setter cho account
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
        this.accountId = (account != null) ? account.getAccountId() : null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", accountId=" + accountId +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", point=" + point +
                '}';
    }
}