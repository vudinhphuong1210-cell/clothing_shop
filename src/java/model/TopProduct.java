package model;

import java.math.BigDecimal;

/**
 * DTO dùng cho Top 10 sản phẩm bán chạy trên Dashboard
 */
public class TopProduct {
    private int rank;
    private int productId;
    private String productName;
    private String image;
    private BigDecimal price;
    private String categoryName;
    private int totalSold;
    private BigDecimal totalRevenue;

    public TopProduct() {}

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public int getTotalSold() { return totalSold; }
    public void setTotalSold(int totalSold) { this.totalSold = totalSold; }

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
}