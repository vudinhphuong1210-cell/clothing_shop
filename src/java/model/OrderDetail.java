/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class OrderDetail {
    private int orderDetailID;
    private int orderID;
    private int productStatsID;
    private int quantity;
    private double price;

    public OrderDetail() {}

    public OrderDetail(int orderDetailID, int orderID, int productStatsID,
                       int quantity, double price) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productStatsID = productStatsID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductStatsID() {
        return productStatsID;
    }

    public void setProductStatsID(int productStatsID) {
        this.productStatsID = productStatsID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
