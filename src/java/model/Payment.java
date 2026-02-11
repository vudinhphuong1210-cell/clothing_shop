package model;

import constant.PaymentStatus;
import java.time.LocalDateTime;

public class Payment {
    private Integer paymentId;
    private Integer orderId;
    private String paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private LocalDateTime updatedAt;

    // Constructor mặc định
    public Payment() {
        this.paymentMethod = "COD";
        this.paymentStatus = PaymentStatus.PENDING;
    }

    // Constructor đầy đủ
    public Payment(Integer paymentId, Integer orderId, String paymentMethod, 
                  PaymentStatus paymentStatus, LocalDateTime paymentDate, LocalDateTime updatedAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.updatedAt = updatedAt;
    }

    // Constructor không có ID (để insert)
    public Payment(Integer orderId) {
        this.orderId = orderId;
        this.paymentMethod = "COD";
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public Payment(Integer orderId, String paymentMethod) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    // Getters và Setters
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", paymentDate=" + paymentDate +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
