/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Order {

    private int orderId;
    private int customerId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private OrderStatus orderStatus;
    private String address;
    private LocalDateTime updatedAt;
    private String paymentStatus;

    public Order() {
    }

    public Order(int orderId, int customerId, LocalDateTime orderDate, double totalAmount, OrderStatus orderStatus, String address, LocalDateTime updatedAt, String paymentStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.address = address;
        this.updatedAt = updatedAt;
        this.paymentStatus = paymentStatus;
    }

    

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
