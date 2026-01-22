package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Category;
import model.Product;

public class clothingShopDal extends DBContext {

    public Account getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Account WHERE UserName = ? AND PassWord = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Account ac = new Account();
            ac.setAccountID(rs.getInt("AccountID"));
            ac.setUserName(rs.getString("UserName"));
            ac.setPassWord(rs.getString("PassWord"));
            ac.setRole(rs.getString("Role"));
            return ac;
        }
        return null;
    }

    public List<Product> getAllProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product pro = new Product();
            pro.setProductID(rs.getInt("ProductID"));
            pro.setProductName(rs.getString("ProductName"));
            pro.setPrice(rs.getDouble("Price"));
            pro.setImage(rs.getString("Image"));
            pro.setDescription(rs.getString("Description"));
            pro.setCategoryID(rs.getInt("CategoryID"));
            pro.setStatus(rs.getString("Status"));
            list.add(pro);
        }
        rs.close();
        pstmt.close();
        return list;
    }

    public int getSold(int ProductId) throws SQLException {
        int sold = 0;
        String sql = "SELECT ISNULL(SUM(TotalSold), 0) FROM ProductStats WHERE ProductID=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, ProductId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            sold = rs.getInt(1);
        }
        return sold;
    }
    
    public List<Category> getAllCategory() throws SQLException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Category c = new Category();
            c.setCategoryID(rs.getInt("categoryId"));
            c.setCategoryName(rs.getString("categoryName"));
            list.add(c);
        }

        rs.close();
        ps.close();
        return list;
    }
}
