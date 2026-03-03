/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import constant.OrderStatus;

public class ShippingDAO extends DBContext {

    public List<Order> getOrdersForShipper() {
        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM [Order] WHERE OrderStatus = 'Shipped'";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setCustomerId(rs.getInt("CustomerId"));
                Timestamp orderDate = rs.getTimestamp("OrderDate");
                if (orderDate != null) {
                    order.setOrderDate(orderDate.toLocalDateTime());
                }
                order.setTotalAmount(rs.getDouble("TotalAmount"));
                String orderStatus = rs.getString("OrderStatus");
                if (orderStatus != null) {
                    order.setOrderStatus(OrderStatus.valueOf(orderStatus));
                }
                order.setAddress(rs.getString("Address"));
                Timestamp updatedAt = rs.getTimestamp("UpdatedAt");
                if (updatedAt != null) {
                    order.setUpdatedAt(updatedAt.toLocalDateTime());
                }
                list.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getOrderForShipper: " + e.getMessage(), e);
        }
        return list;
    }

    public boolean confirmPayment(int orderId) throws SQLException {

        connection.setAutoCommit(false);
        String updateOrder = "UPDATE Order SET OrderStatus = ? WHERE OrderId = ?";
        PreparedStatement orderSt = connection.prepareStatement(updateOrder);
        orderSt.setString(1, "Delivered");
        orderSt.setInt(2, orderId);

        int orderRows = orderSt.executeUpdate();

        String updatePayment = "UPDATE Payment SET OrderStatus = ? WHERE OrderId = ?";
        PreparedStatement paymentSt = connection.prepareStatement(updatePayment);
        paymentSt.setString(1, "PAID");
        paymentSt.setInt(2, orderId);

        int paymentRows = paymentSt.executeUpdate();

        if (orderRows > 0 && paymentRows > 0) {
            connection.commit();
            return true;
        } else {
            connection.rollback();
            return false;
        }
    }
}

