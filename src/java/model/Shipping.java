package model;

import constant.ShippingStatus;
import java.time.LocalDateTime;

public class Shipping {
    private Integer shippingId;
    private Integer orderId;
    private ShippingStatus shippingStatus;
    private LocalDateTime shippingDate;
    private LocalDateTime deliveryDate;
    private LocalDateTime updatedAt;

    // Constructor mặc định
    public Shipping() {
        this.shippingStatus = ShippingStatus.PENDING;
    }

    // Constructor đầy đủ
    public Shipping(Integer shippingId, Integer orderId, ShippingStatus shippingStatus,
                   LocalDateTime shippingDate, LocalDateTime deliveryDate, LocalDateTime updatedAt) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.shippingStatus = shippingStatus;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.updatedAt = updatedAt;
    }

    // Constructor không có ID (để insert)
    public Shipping(Integer orderId) {
        this.orderId = orderId;
        this.shippingStatus = ShippingStatus.PENDING;
    }

    // Getters và Setters
    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

   
}
