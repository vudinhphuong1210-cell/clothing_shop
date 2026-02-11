package model;

import java.time.LocalDateTime;

public class ProductStats {
    private Integer productStatsId;
    private Integer productId;
    private String size;
    private String color;
    private Integer totalInStock;
    private Integer totalSold;
    private LocalDateTime updatedAt;

    // Constructor mặc định
    public ProductStats() {
        this.totalInStock = 0;
        this.totalSold = 0;
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor đầy đủ
    public ProductStats(Integer productStatsId, Integer productId, String size, String color,
                       Integer totalInStock, Integer totalSold, LocalDateTime updatedAt) {
        this.productStatsId = productStatsId;
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.totalInStock = totalInStock;
        this.totalSold = totalSold;
        this.updatedAt = updatedAt;
    }

    // Constructor không có ID (để insert)
    public ProductStats(Integer productId, String size, String color, Integer totalInStock) {
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.totalInStock = totalInStock;
        this.totalSold = 0;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters và Setters
    public Integer getProductStatsId() {
        return productStatsId;
    }

    public void setProductStatsId(Integer productStatsId) {
        this.productStatsId = productStatsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getTotalInStock() {
        return totalInStock;
    }

    public void setTotalInStock(Integer totalInStock) {
        this.totalInStock = totalInStock;
    }

    public Integer getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(Integer totalSold) {
        this.totalSold = totalSold;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ProductStats{" +
                "productStatsId=" + productStatsId +
                ", productId=" + productId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", totalInStock=" + totalInStock +
                ", totalSold=" + totalSold +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
