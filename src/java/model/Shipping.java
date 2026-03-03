/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import constant.ShippingStatus;
import java.time.LocalDateTime;

public class Shipping {

    private int shippingId;
    private int orderId;
    private ShippingStatus shippingStatus;
    private LocalDateTime shippingDate;
    private LocalDateTime deliveredDate;
    private LocalDateTime updatedAt;

    public Shipping() {
    }

    public Shipping(int shippingId, int orderId, ShippingStatus shippingStatus, LocalDateTime shippingDate, LocalDateTime deliveredDate, LocalDateTime updatedAt) {
        this.shippingId = shippingId;
        this.orderId = orderId;
        this.shippingStatus = shippingStatus;
        this.shippingDate = shippingDate;
        this.deliveredDate = deliveredDate;
        this.updatedAt = updatedAt;
    }

    public int getShippingId() {
        return shippingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public void setUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
