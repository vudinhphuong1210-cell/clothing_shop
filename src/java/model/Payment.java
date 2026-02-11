package model;

import constant.PaymentStatus;
import java.time.LocalDateTime;

public class Payment {

    private int paymentId;
    private int orderId;
    private PaymentStatus paymentStatus;   // ENUM
    private String paymentMethod;          // String (KHÔNG enum)
    private LocalDateTime paidAt;

    // getters & setters

    public Payment() {
    }

    public Payment(int paymentId, int orderId, PaymentStatus paymentStatus, String paymentMethod, LocalDateTime paidAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.paidAt = paidAt;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
    
}