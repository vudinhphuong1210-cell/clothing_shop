package dal;

import constant.OrderStatus;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Order;

public class clothingShopDal extends DBContext {

    // ===============================
    // LẤY TỔNG SỐ ĐƠN HÀNG
    // ===============================
    public int getTotalOrders() throws SQLException {
        String sql = "SELECT COUNT(*) FROM [Order]";

        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // ===============================
    // LẤY DANH SÁCH ĐƠN HÀNG
    // ===============================
    public List<Order> getAllOrders() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order]";

        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Order o = new Order();

                // INT
                o.setOrderId(rs.getInt("OrderId"));
                o.setCustomerId(rs.getInt("CustomerId"));

                // DATETIME2 -> LocalDateTime (CHECK NULL)
                Timestamp orderDateTs = rs.getTimestamp("OrderDate");
                if (orderDateTs != null) {
                    o.setOrderDate(orderDateTs.toLocalDateTime());
                }

                // DECIMAL -> BigDecimal
                BigDecimal totalAmount = rs.getBigDecimal("TotalAmount");
                o.setTotalAmount(totalAmount);

                // ENUM OrderStatus (AN TOÀN)
                String statusStr = rs.getString("OrderStatus");
                if (statusStr != null && !statusStr.trim().isEmpty()) {
                    try {
                        o.setOrderStatus(OrderStatus.valueOf(statusStr.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        o.setOrderStatus(OrderStatus.PENDING);
                    }
                } else {
                    o.setOrderStatus(OrderStatus.PENDING);
                }

                // NVARCHAR
                o.setAddress(rs.getString("Address"));

                // UpdatedAt (CÓ THỂ NULL)
                Timestamp updatedTs = rs.getTimestamp("UpdatedAt");
                if (updatedTs != null) {
                    o.setUpdatedAt(updatedTs.toLocalDateTime());
                }

                list.add(o);
            }
        }
        return list;
    }
}