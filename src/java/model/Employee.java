/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Employee {

    private int employeeId;
    private int accountId;
    private String employeeName;
    private String phone;
    private String position;
    private String status;
    private LocalDateTime createdAt;

    public Employee() {
    }

    public Employee(int employeeId, int accountId, String employeeName, String phone, String position, String status, LocalDateTime createdAt) {
        this.employeeId = employeeId;
        this.accountId = accountId;
        this.employeeName = employeeName;
        this.phone = phone;
        this.position = position;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
