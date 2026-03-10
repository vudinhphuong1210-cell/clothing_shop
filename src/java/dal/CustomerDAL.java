package dal;

import constant.Gender;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

public class CustomerDAL extends DBContext {

    public Customer getCustomerByAccountId(int accountId) {
        String sql = "SELECT c.*, a.Email FROM Customer c JOIN Account a ON c.AccountId = a.AccountId WHERE c.AccountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setAccount(new model.Account());
                c.setCustomerId(rs.getInt("CustomerId"));
                c.setAccountId(rs.getInt("AccountId"));
                c.setFullName(rs.getString("FullName"));
                c.setPhone(rs.getString("Phone"));
                c.setEmail(rs.getString("Email"));

                String genderStr = rs.getString("Gender");
                if (genderStr != null) {
                    try {
                        c.setGender(Gender.valueOf(genderStr.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        // ignore or set default
                    }
                }

                c.setAddress(rs.getString("Address"));
                c.setPoint(rs.getInt("Point"));

                Timestamp ct = rs.getTimestamp("CreatedAt");
                if (ct != null) {
                    c.setCreatedAt(ct.toLocalDateTime());
                }
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (AccountId, FullName, Phone, Gender, Address, Point, CreatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customer.getAccountId());
            st.setString(2, customer.getFullName());
            st.setString(3, customer.getPhone());
            st.setString(4, customer.getGender() != null ? customer.getGender().name() : null);
            st.setString(5, customer.getAddress());
            st.setInt(6, 0); // Default points
            st.setTimestamp(7, Timestamp.valueOf(java.time.LocalDateTime.now()));

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error inserting customer: " + ex.getMessage());
            Logger.getLogger(CustomerDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET FullName = ?, Phone = ?, Gender = ?, Address = ? WHERE AccountId = ?";
        String sqlAcc = "UPDATE Account SET Email = ? WHERE AccountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, customer.getFullName());
            st.setString(2, customer.getPhone());
            st.setString(3, customer.getGender() != null ? customer.getGender().name() : null);
            st.setString(4, customer.getAddress());
            st.setInt(5, customer.getAccountId());

            int rowsAffected = st.executeUpdate();

            PreparedStatement stAcc = connection.prepareStatement(sqlAcc);
            stAcc.setString(1, customer.getEmail());
            stAcc.setInt(2, customer.getAccountId());
            stAcc.executeUpdate();
            System.out.println(
                    "Update Customer for AccountId " + customer.getAccountId() + ": Rows affected = " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating customer: " + ex.getMessage());
            Logger.getLogger(CustomerDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
