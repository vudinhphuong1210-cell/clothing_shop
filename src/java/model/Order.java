package model;

import constant.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private String address;
    private LocalDateTime updatedAt;
    private constant.PaymentStatus paymentStatus;

    public constant.PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(constant.PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    // Constructor mặc định
    public Order() {
        this.orderDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.PENDING;
    }

    // Constructor đầy đủ
    public Order(Integer orderId, Integer customerId, LocalDateTime orderDate, BigDecimal totalAmount,
                OrderStatus orderStatus, String address, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.address = address;
        this.updatedAt = updatedAt;
    }

    // Constructor không có ID (để insert)
    public Order(Integer customerId, BigDecimal totalAmount, String address) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.address = address;
        this.orderDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.PENDING;
    }

    // Getters và Setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // --- CART LOGIC (Session) ---
    private java.util.List<OrderDetail> orderDetails = new java.util.ArrayList<>();

    public java.util.List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(java.util.List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public void addOrderDetail(OrderDetail detail) {
        // Nếu đã có ProductStats này trong giỏ, chỉ cần tăng quantity
        for (OrderDetail od : orderDetails) {
            if (od.getProductStatsId().equals(detail.getProductStatsId())) {
                od.setQuantity(od.getQuantity() + detail.getQuantity());
                calculateTotalAmount();
                return;
            }
        }
        orderDetails.add(detail);
        calculateTotalAmount();
    }

    public void removeOrderDetail(Integer productStatsId) {
        orderDetails.removeIf(od -> od.getProductStatsId().equals(productStatsId));
        calculateTotalAmount();
    }

    public void updateQuantity(Integer productStatsId, int newQuantity) {
        for (OrderDetail od : orderDetails) {
            if (od.getProductStatsId().equals(productStatsId)) {
                if(newQuantity <= 0) {
                     orderDetails.remove(od);
                } else {
                     od.setQuantity(newQuantity);
                }
                calculateTotalAmount();
                return;
            }
        }
    }

    public void calculateTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderDetail od : orderDetails) {
            total = total.add(od.getSubTotal());
        }
        this.totalAmount = total;
    }
}
