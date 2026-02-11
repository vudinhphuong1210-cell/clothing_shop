package model;

import constant.Gender;
import java.time.LocalDateTime;

public class Customer {
    private Integer customerId;
    private Integer accountId;
    private String fullName;
    private String phone;
    private String email;
    private Gender gender;
    private String address;
    private Integer point;
    private LocalDateTime createdAt;

    // Constructor mặc định
    public Customer() {
        this.point = 0;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor đầy đủ
    public Customer(Integer customerId, Integer accountId, String fullName, String phone, 
                   String email, Gender gender, String address, Integer point, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.point = point;
        this.createdAt = createdAt;
    }

    // Constructor không có ID (để insert)
    public Customer(Integer accountId, String fullName, String phone, String email, Gender gender, String address) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.point = 0;
        this.createdAt = LocalDateTime.now();
    }

    // Getters và Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
