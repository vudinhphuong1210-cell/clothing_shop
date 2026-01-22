/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ProductStats {
    private int productStatsID;
    private int productID;
    private int totalSold;
    private int totalStock;
    private Date lastUpdated;
    private String size;
    private String color;

    public ProductStats() {}

    public ProductStats(int productStatsID, int productID, int totalSold,
                        int totalStock, Date lastUpdated, String size, String color) {
        this.productStatsID = productStatsID;
        this.productID = productID;
        this.totalSold = totalSold;
        this.totalStock = totalStock;
        this.lastUpdated = lastUpdated;
        this.size = size;
        this.color = color;
    }

    public int getProductStatsID() {
        return productStatsID;
    }

    public void setProductStatsID(int productStatsID) {
        this.productStatsID = productStatsID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
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
    
}
