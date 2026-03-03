/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Customer {

    private int customerId;
    private int accountId;
    private String fullName;
    private String phone;
    private String email;
    private Gender gender;
    private String address;
    private int point;
    private LocalDateTime createdAt;

    public Customer() {
    }

    public Customer(int customerId, int accountId, String fullName, String phone, String email, Gender gender, String address, int point, LocalDateTime createdAt) {
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

    public int getCustomerId() {
        return customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public int getPoint() {
        return point;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
