package model;

import constant.OrderStatus;
import java.time.LocalDateTime;

public class Order {

    private int orderId;
    private int accountId;
    private double totalAmount;
    private OrderStatus orderStatus;     // ENUM
    private LocalDateTime createdAt;

    // getters & setters

    public Order() {
    }

    public Order(int orderId, int accountId, double totalAmount, OrderStatus orderStatus, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}