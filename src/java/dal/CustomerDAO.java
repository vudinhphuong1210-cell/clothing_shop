/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Customer;
import java.sql.*;
import model.Gender;

public class CustomerDAO extends DBContext {

    public Customer getCustomerByAccountId(int accountId) {
        String sql = "SELECT CustomerId, AccountId, FullName, Phone, Email, Gender, Address, Point, CreatedAt FROM Customer WHERE AccountId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerId"));
                customer.setAccountId(rs.getInt("AccountId"));
                customer.setFullName(rs.getString("FullName"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                String gender = rs.getString("Gender");
                if (gender != null) {
                    customer.setGender(Gender.valueOf(gender.trim()));
                }
                customer.setAddress(rs.getString("Address"));
                customer.setPoint(rs.getInt("Point"));
                Timestamp createdAt = rs.getTimestamp("CreatedAt");
                if (createdAt != null) {
                    customer.setCreatedAt(createdAt.toLocalDateTime());
                }
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting customer by accountId", e);
        }
        return null;
    }
}
