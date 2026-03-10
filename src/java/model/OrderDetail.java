package model;

import java.math.BigDecimal;

public class OrderDetail {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productStatsId;
    private Integer quantity;
    private BigDecimal price;
    private String productName;

    // Constructor mặc định
    public OrderDetail() {
    }

    // Constructor đầy đủ
    public OrderDetail(Integer orderDetailId, Integer orderId, Integer productStatsId, 
                      Integer quantity, BigDecimal price, String productName) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productStatsId = productStatsId;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }

    // Constructor không có ID (để insert)
    public OrderDetail(Integer orderId, Integer productStatsId, Integer quantity, BigDecimal price, String productName) {
        this.orderId = orderId;
        this.productStatsId = productStatsId;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }

    // Getters và Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
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

    // Transient fields for UI Display
    private String productImage;
    private String color;
    private String size;
    private Integer productId;

    // Getters and Setters cho Transient Fields
    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    // Method tính subtotal
    public BigDecimal getSubTotal() {
        if (price != null && quantity != null) {
            return price.multiply(BigDecimal.valueOf(quantity));
        }
        return BigDecimal.ZERO;
    }
}
