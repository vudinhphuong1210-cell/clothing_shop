package model;

import constant.ShippingStatus;
import java.time.LocalDateTime;

public class Shipping {

    private int shippingId;
    private int orderId;
    private ShippingStatus shippingStatus;  // ENUM
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;

    // getters & setters

    public Shipping() {
    }

    public Shipping(int shippingId, int orderId, ShippingStatus shippingStatus, LocalDateTime shippedAt, LocalDateTime deliveredAt) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.shippingStatus = shippingStatus;
        this.shippedAt = shippedAt;
        this.deliveredAt = deliveredAt;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
    
}