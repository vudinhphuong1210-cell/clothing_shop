package model;

import java.math.BigDecimal;

public class OrderDetail {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productStatsId;
    private Integer quantity;
    private BigDecimal price;

    // Constructor mặc định
    public OrderDetail() {
    }

    // Constructor đầy đủ
    public OrderDetail(Integer orderDetailId, Integer orderId, Integer productStatsId, 
                      Integer quantity, BigDecimal price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productStatsId = productStatsId;
        this.quantity = quantity;
        this.price = price;
    }

    // Constructor không có ID (để insert)
    public OrderDetail(Integer orderId, Integer productStatsId, Integer quantity, BigDecimal price) {
        this.orderId = orderId;
        this.productStatsId = productStatsId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters và Setters
    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductStatsId() {
        return productStatsId;
    }

    public void setProductStatsId(Integer productStatsId) {
        this.productStatsId = productStatsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Method tính subtotal
    public BigDecimal getSubTotal() {
        if (price != null && quantity != null) {
            return price.multiply(BigDecimal.valueOf(quantity));
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", productStatsId=" + productStatsId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", subTotal=" + getSubTotal() +
                '}';
    }
}
