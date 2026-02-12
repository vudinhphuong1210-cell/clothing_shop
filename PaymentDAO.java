/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.PaymentMethod;
import model.PaymentStatus;

public class PaymentDAO extends DBContext {

    public boolean insert(Payment payment) {
        String sql = "INSERT INTO Payment (OrderId, PaymentMethod, PaymentStatus, PaymentDate, UpdatedAt) VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getPaymentMethod().name());
            ps.setString(3, payment.getPaymentStatus().name());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi insert: " + e.getMessage(), e);
        }
    }

    // admin
    public List<Payment> getAll() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM Payment ORDER BY UpdatedAt DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getAll: " + e.getMessage(), e);
        }
        return list;
    }

    public Payment getById(int paymentId) {
        String sql = "SELECT * FROM Payment WHERE PaymentId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, paymentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi getById: " + e.getMessage(), e);
        }
        return null;
    }

    public boolean updateStatus(int paymentId, PaymentStatus status) {
        String sql = "UPDATE Payment SET PaymentStatus = ?, UpdatedAt = CURRENT_TIMESTAMP WHERE PaymentId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status.name());
            ps.setInt(2, paymentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi updateStatus: " + e.getMessage(), e);
        }
    }

    public boolean delete(int paymentId) {
        String sql = "DELETE FROM Payment WHERE PaymentId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, paymentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi delete: " + e.getMessage(), e);
        }
    }

    private Payment mapResultSet(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("PaymentId"));
        payment.setOrderId(rs.getInt("OrderId"));

        payment.setPaymentMethod(PaymentMethod.valueOf(rs.getString("PaymentMethod")));
        payment.setPaymentStatus(PaymentStatus.valueOf(rs.getString("PaymentStatus")));

        Timestamp date = rs.getTimestamp("PaymentDate");
        if (date != null) {
            payment.setPaymentDate(date.toLocalDateTime());
        }

        Timestamp update = rs.getTimestamp("UpdatedAt");
        if (update != null) {
            payment.setUpdatedAt(update.toLocalDateTime());
        }

        return payment;
    }
}
