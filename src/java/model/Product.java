package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private Integer productId;
    private Integer categoryId;
    private String productName;
    private BigDecimal price;
    private String image;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor mặc định
    public Product() {
        this.status = "Active";
        this.createdAt = LocalDateTime.now();
    }

    // Constructor đầy đủ
    public Product(Integer productId, Integer categoryId, String productName, BigDecimal price,
                  String image, String status, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Constructor không có ID (để insert)
    public Product(Integer categoryId, String productName, BigDecimal price, String image, String description) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = "Active";
        this.createdAt = LocalDateTime.now();
    }

    // Getters và Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

   
}
