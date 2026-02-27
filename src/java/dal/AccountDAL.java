package dal;

import constant.AccountStatus;
import constant.OrderStatus;
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
        // Tắt auto-commit để bắt đầu transaction
        connection.setAutoCommit(false);

        // 1. Chèn vào bảng Account
        try (PreparedStatement stAcc = connection.prepareStatement(sqlAccount, Statement.RETURN_GENERATED_KEYS)) {
            stAcc.setString(1, account.getUserName());
            stAcc.setString(2, account.getPassword());
            
            // Chuẩn hóa Role (Ví dụ: "customer")
            String roleName = account.getRole().name(); 
            stAcc.setString(3, roleName.substring(0, 1).toUpperCase() + roleName.substring(1).toLowerCase());
            
            // Lấy status từ object account
            stAcc.setString(4, account.getStatus().name()); 
            stAcc.setTimestamp(5, Timestamp.valueOf(account.getCreatedAt()));

            int affectedRows = stAcc.executeUpdate();
            if (affectedRows == 0) {
                connection.rollback();
                return false;
            }

            // Lấy ID tự động tăng (AccountId)
            int accountId = 0;
            try (ResultSet generatedKeys = stAcc.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    accountId = generatedKeys.getInt(1);
                } else {
                    connection.rollback();
                    return false;
                }
            }

            // 2. Chèn vào bảng Customer
            try (PreparedStatement stCust = connection.prepareStatement(sqlCustomer)) {
                stCust.setInt(1, accountId);
                stCust.setString(2, customer.getFullName());
                stCust.setString(3, customer.getEmail());
                stCust.setInt(4, customer.getPoint());
                stCust.setTimestamp(5, Timestamp.valueOf(customer.getCreatedAt()));

                stCust.executeUpdate();
            }

            // Hoàn tất transaction
            connection.commit();
            return true;
        }

    } catch (SQLException ex) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, "Rollback failed", e);
        }
        Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, "Transaction failed", ex);
    } finally {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    return false;
}

  

    

    public AccountDAL() {
        // QUAN TRỌNG: Đảm bảo biến connection của lớp cha (DBContext) được khởi tạo
        if (this.connection == null) {
            // Tùy vào cách bạn viết DBContext, có thể là:
            // this.connection = new DBContext().getConnection();
        }
    }

    public Account checkLogin(String username, String password) {
        // Thêm kiểm tra này để debug nếu connection vẫn null
        if (connection == null) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, "DATABASE CONNECTION IS NULL!");
            return null;
        }

        String sql = "SELECT * FROM Account WHERE UserName = ? AND Password = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, username);
            st.setString(2, password);
            
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setAccountId(rs.getInt("AccountId"));
                    account.setUserName(rs.getString("UserName"));
                    account.setPassword(rs.getString("Password"));
                    
                    // Xử lý Role an toàn
                    String roleStr = rs.getString("Role");
                    account.setRole(roleStr != null ? UserRole.valueOf(roleStr.toUpperCase()) : UserRole.CUSTOMER);
                    
                    // Xử lý Status an toàn (Sửa lỗi bạn gặp trước đó)
                    String statusStr = rs.getString("Status");
                    account.setStatus(statusStr != null ? AccountStatus.valueOf(statusStr.toUpperCase()) : AccountStatus.INACTIVE);
                    
                    Timestamp ct = rs.getTimestamp("CreatedAt");
                    if (ct != null) account.setCreatedAt(ct.toLocalDateTime());
                    
                    return account;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}


