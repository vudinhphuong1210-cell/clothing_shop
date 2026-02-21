package dal;

import constant.UserRole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

import java.sql.Statement;
import model.Customer;

public class AccountDAL extends DBContext {

    public boolean checkUsernameDuplicate(String username) {
        String sql = "SELECT * FROM Account WHERE UserName = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean registerCustomer(Account account, Customer customer) {
        String sqlAccount = "INSERT INTO Account (UserName, Password, Role, Status, CreatedAt) VALUES (?, ?, ?, ?, ?)";
        String sqlCustomer = "INSERT INTO Customer (AccountId, FullName, Email, Point, CreatedAt) VALUES (?, ?, ?, ?, ?)";
        
        try {
            connection.setAutoCommit(false);
            
            // 1. Insert Account
            PreparedStatement stAcc = connection.prepareStatement(sqlAccount, Statement.RETURN_GENERATED_KEYS);
            stAcc.setString(1, account.getUserName());
            stAcc.setString(2, account.getPassword());
            stAcc.setString(3, account.getRole().name());
            stAcc.setString(4, account.getStatus());
            stAcc.setTimestamp(5, Timestamp.valueOf(account.getCreatedAt()));
            
            int affectedRows = stAcc.executeUpdate();
            if (affectedRows == 0) {
                connection.rollback();
                return false;
            }
            
            int accountId = 0;
            try (ResultSet generatedKeys = stAcc.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    accountId = generatedKeys.getInt(1);
                } else {
                    connection.rollback();
                    return false;
                }
            }
            
            // 2. Insert Customer
            PreparedStatement stCust = connection.prepareStatement(sqlCustomer);
            stCust.setInt(1, accountId);
            stCust.setString(2, customer.getFullName());
            stCust.setString(3, customer.getEmail());
            stCust.setInt(4, customer.getPoint());
            stCust.setTimestamp(5, Timestamp.valueOf(customer.getCreatedAt()));
            
            stCust.executeUpdate();
            
            connection.commit();
            return true;
            
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, e);
            }
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return false;
    }

    public Account checkLogin(String username, String password) {
        String sql = "SELECT * FROM Account WHERE UserName = ? AND Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("AccountId"));
                account.setUserName(rs.getString("UserName"));
                account.setPassword(rs.getString("Password"));
                
                String roleStr = rs.getString("Role");
                if (roleStr != null) {
                    try {
                        account.setRole(UserRole.valueOf(roleStr.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        // Default to CUSTOMER if role is invalid
                        account.setRole(UserRole.CUSTOMER);
                    }
                }
                
                account.setStatus(rs.getString("Status"));
                Timestamp ct = rs.getTimestamp("CreatedAt");
                if (ct != null) {
                    account.setCreatedAt(ct.toLocalDateTime());
                }
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
