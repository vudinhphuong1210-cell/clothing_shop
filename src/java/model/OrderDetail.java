/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class OrderDetail {

    private int orderDetailId;
    private int orderId;
    private int productStatsId;
    private int quantity;
    private double price;
    private String productName;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, int orderId, int productStatsId, int quantity, double price, String productName) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productStatsId = productStatsId;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }

    

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductStatsId() {
        return productStatsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductStatsId(int productStatsId) {
        this.productStatsId = productStatsId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
