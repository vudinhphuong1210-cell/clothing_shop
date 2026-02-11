package model;

import java.time.LocalDateTime;

public class Employee {
    private Integer employeeId;
    private Integer accountId;
    private String employeeName;
    private String phone;
    private String position;
    private String status;
    private LocalDateTime createdAt;

    // Constructor mặc định
    public Employee() {
        this.status = "Active";
        this.createdAt = LocalDateTime.now();
    }

    // Constructor đầy đủ
    public Employee(Integer employeeId, Integer accountId, String employeeName, 
                   String phone, String position, String status, LocalDateTime createdAt) {
        this.employeeId = employeeId;
        this.accountId = accountId;
        this.employeeName = employeeName;
        this.phone = phone;
        this.position = position;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Constructor không có ID (để insert)
    public Employee(Integer accountId, String employeeName, String phone, String position) {
        this.accountId = accountId;
        this.employeeName = employeeName;
        this.phone = phone;
        this.position = position;
        this.status = "Active";
        this.createdAt = LocalDateTime.now();
    }

    // Getters và Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
