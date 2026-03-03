package dal;

import constant.ProductStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

  
public class ProductDAL extends DBContext {

    public List<Product> searchProductsByName(String keyword) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE ProductName LIKE ? ";
        
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, "%" + keyword + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getInt("ProductId"));
                    p.setCategoryId(rs.getInt("CategoryId"));
                    p.setProductName(rs.getString("ProductName"));
                    p.setPrice(rs.getBigDecimal("Price"));
                    p.setImage(rs.getString("Image"));
                    p.setStatus(ProductStatus.valueOf(rs.getString("Status").toUpperCase()));
                    
                    p.setDescription(rs.getString("Description"));
                    products.add(p);
                }
            }
        }
        return products;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE Status = 'Active'";
        
        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("ProductId"));
                p.setCategoryId(rs.getInt("CategoryId"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getBigDecimal("Price"));
                p.setImage(rs.getString("Image"));
                p.setStatus(ProductStatus.valueOf(rs.getString("Status").toUpperCase()));
                p.setDescription(rs.getString("Description"));
                products.add(p);
            }
        }
        return products;
    }
    public List<Product> getProductsByCategoryId(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE CategoryId = ? AND Status = 'Active'";
        
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, categoryId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getInt("ProductId"));
                    p.setCategoryId(rs.getInt("CategoryId"));
                    p.setProductName(rs.getString("ProductName"));
                    p.setPrice(rs.getBigDecimal("Price"));
                    p.setImage(rs.getString("Image"));
                    p.setStatus(ProductStatus.valueOf(rs.getString("Status").toUpperCase()));
                    p.setDescription(rs.getString("Description"));
                    products.add(p);
                }
            }
        }
        return products;
    }
}
