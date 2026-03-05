/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Order;
import java.sql.*;
import model.OrderDetail;
import constant.OrderStatus;
import constant.PaymentStatus;

public class OrderDAO extends DBContext {
    
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT o.OrderId, o.OrderDate, o.TotalAmount, o.OrderStatus, p.PaymentStatus FROM [Order] o LEFT JOIN Payment p ON o.OrderId = p.OrderId WHERE o.CustomerId = ? ORDER BY OrderDate DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                Timestamp ts = rs.getTimestamp("OrderDate");
                if (ts != null) {
                    order.setOrderDate(ts.toLocalDateTime());
                }
                
                order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString("OrderStatus").toUpperCase()));
                String psStr = rs.getString("PaymentStatus");
                if (psStr != null) {
                    order.setPaymentStatus(PaymentStatus.valueOf(psStr.toUpperCase()));
                }
                
                list.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error in getOrdersByCustomerId", e);
        }
        return list;
    }
    
    public Order getOrderByIdAndCustomer(int orderId, int customerId) {
        String sql = "SELECT o.*, p.PaymentStatus FROM [Order] o LEFT JOIN Payment p ON o.OrderId = p.OrderId WHERE o.OrderId = ? AND o.CustomerId = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, customerId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                Timestamp ts = rs.getTimestamp("OrderDate");
                if (ts != null) {
                    order.setOrderDate(ts.toLocalDateTime());
                }
                
                order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString("OrderStatus").toUpperCase()));
                String psStr = rs.getString("PaymentStatus");
                if (psStr != null) {
                    order.setPaymentStatus(PaymentStatus.valueOf(psStr.toUpperCase()));
                }
                return order;
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving order. orderId=" + orderId + ", customerId=" + customerId, e);
        }
        return null;
    }
    
    public List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        
        String sql = "SELECT od.*, p.ProductName FROM OrderDetail od "
                   + "JOIN ProductStats ps ON od.ProductStatsId = ps.ProductStatsId "
                   + "JOIN Product p ON ps.ProductId = p.ProductId "
                   + "WHERE od.OrderId = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                
                orderDetail.setOrderDetailId(rs.getInt("OrderDetailId"));
                orderDetail.setOrderId(rs.getInt("OrderId"));
                orderDetail.setProductStatsId(rs.getInt("ProductStatsId"));
                orderDetail.setQuantity(rs.getInt("Quantity"));
                orderDetail.setPrice(rs.getBigDecimal("Price"));
                orderDetail.setProductName(rs.getString("ProductName"));
                
                list.add(orderDetail);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving order details for orderId=" + orderId, e);
        }
        return list;
    }
    
    public int insertOrder(Order order) {
        
        String sql = "INSERT INTO [Order] (CustomerId, TotalAmount, OrderStatus, Address, UpdatedAt)VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, order.getCustomerId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, String.valueOf(order.getOrderStatus()));
            ps.setString(4, order.getAddress());
            ps.setTimestamp(5, (order.getUpdatedAt() != null) ? Timestamp.valueOf(order.getUpdatedAt()) : null);
            
            int rows = ps.executeUpdate();
            
            if (rows == 0) {
                return -1;
            }
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi insert order", e);
        }
        
        return -1;
    }
    
    public Order getOrderById(int orderId) {
        String sql = "SELECT o.*, p.PaymentStatus FROM [Order] o LEFT JOIN Payment p ON o.OrderId = p.OrderId WHERE o.OrderId = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("OrderId"));
                order.setCustomerId(rs.getInt("CustomerId"));
                Timestamp ts = rs.getTimestamp("OrderDate");
                if (ts != null) {
                    order.setOrderDate(ts.toLocalDateTime());
                }              
                order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString("OrderStatus").toUpperCase()));
                order.setAddress(rs.getString("Address"));
                Timestamp tsUpdate = rs.getTimestamp("UpdatedAt");
                if (tsUpdate != null) {
                    order.setUpdatedAt(tsUpdate.toLocalDateTime());
                }
                String psStr = rs.getString("PaymentStatus");
                if (psStr != null) {
                    order.setPaymentStatus(PaymentStatus.valueOf(psStr.toUpperCase()));
                }
                return order;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi get order by order id", e);
        }
        return null;
    }
    
    public OrderStatus getOrderStatus(int orderId) {
        String sql = "SELECT OrderStatus FROM [Order] WHERE OrderId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return OrderStatus.valueOf(rs.getString("OrderStatus").toUpperCase());
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi get order status by order id", e);
        }
        return null;
    }
    
    public boolean updateOrderStatus(int orderId, OrderStatus status) {
        String sql = "UPDATE [Order] SET OrderStatus = ?, UpdatedAt = CURRENT_TIMESTAMP  WHERE OrderId = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status.name());
            ps.setInt(2, orderId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi update order status", e);
        }
    }
}
