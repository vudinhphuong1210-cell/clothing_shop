package model;

import constant.EmployeeStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Employee {
    private Integer employeeId;
    private Integer accountId;
    private String employeeName;
    private String phone;
    private String position;
    private EmployeeStatus status;
    private LocalDateTime createdAt;

    // ✅ Thêm field account để JSP dùng ${e.account.userName}, ${e.account.status}
    private Account account;

    // Constructor mặc định
    public Employee() {
        this.status    = EmployeeStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor đầy đủ
    public Employee(Integer employeeId, Integer accountId, String employeeName,
                   String phone, String position, EmployeeStatus status, LocalDateTime createdAt) {
        this.employeeId   = employeeId;
        this.accountId    = accountId;
        this.employeeName = employeeName;
        this.phone        = phone;
        this.position     = position;
        this.status       = status;
        this.createdAt    = createdAt;
    }

    // Constructor không có ID (để INSERT)
    public Employee(Integer accountId, String employeeName, String phone, String position) {
        this.accountId    = accountId;
        this.employeeName = employeeName;
        this.phone        = phone;
        this.position     = position;
        this.status       = EmployeeStatus.ACTIVE;
        this.createdAt    = LocalDateTime.now();
    }

    // Getters & Setters
    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public EmployeeStatus getStatus() { return status; }
    public void setStatus(EmployeeStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // ✅ Account getter/setter
    public Account getAccount() { return account; }
    public void setAccount(Account account) {
        this.account   = account;
        this.accountId = (account != null) ? account.getAccountId() : null;
    }

    // Getter dùng cho JSP hiển thị ngày tạo (dd/MM/yyyy)
public String getCreatedAtFormatted() {
    if (createdAt == null) {
        return "";
    }
    return createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
}
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", status=" + status +
                '}';
    }
}