package dal;

import constant.ProductStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.ProductStats;


  
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

    public List<Product> getProductsWithVariants(String sortBy) throws SQLException {
        List<Product> products = new ArrayList<>();
        String orderByClause = "ProductId DESC"; // Default sorting

        if (sortBy != null) {
            switch (sortBy) {
                case "name_asc":
                    orderByClause = "ProductName ASC";
                    break;
                case "name_desc":
                    orderByClause = "ProductName DESC";
                    break;
                case "price_asc":
                    orderByClause = "Price ASC";
                    break;
                case "price_desc":
                    orderByClause = "Price DESC";
                    break;
            }
        }

        String sql = "SELECT * FROM Product WHERE Status = 'Active' ORDER BY " + orderByClause;

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

        for (Product p : products) {
            p.setVariants(getVariantsByProductId(p.getProductId()));
        }
        return products;
    }

    public List<Product> getProductsWithVariants() throws SQLException {
        return getProductsWithVariants(null);
    }

    public List<ProductStats> getVariantsByProductId(int productId) throws SQLException {
        List<ProductStats> variants = new ArrayList<>();
        String sql = "SELECT * FROM ProductStats WHERE ProductId = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, productId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    ProductStats s = new ProductStats();
                    s.setProductStatsId(rs.getInt("ProductStatsId"));
                    s.setProductId(rs.getInt("ProductId"));
                    s.setSize(rs.getString("Size"));
                    s.setColor(rs.getString("Color"));
                    s.setTotalInStock(rs.getInt("TotalInStock"));
                    s.setTotalSold(rs.getInt("TotalSold"));
                    variants.add(s);
                }
            }
        }
        return variants;
    }

    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Product WHERE ProductId = ? AND Status = 'Active'";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, productId);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getInt("ProductId"));
                    p.setCategoryId(rs.getInt("CategoryId"));
                    p.setProductName(rs.getString("ProductName"));
                    p.setPrice(rs.getBigDecimal("Price"));
                    p.setImage(rs.getString("Image"));
                    p.setStatus(ProductStatus.valueOf(rs.getString("Status").toUpperCase()));
                    p.setDescription(rs.getString("Description"));
                    p.setVariants(getVariantsByProductId(p.getProductId()));
                    return p;
                }
            }
        }
        return null; // or throw Exception
    }

    public ProductStats getProductStats(int productId, String color, String size) throws SQLException {
        String sql = "SELECT TOP 1 * FROM ProductStats WHERE ProductId = ? AND Color = ? AND Size = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, productId);
            pstm.setString(2, color);
            pstm.setString(3, size);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    ProductStats s = new ProductStats();
                    s.setProductStatsId(rs.getInt("ProductStatsId"));
                    s.setProductId(rs.getInt("ProductId"));
                    s.setSize(rs.getString("Size"));
                    s.setColor(rs.getString("Color"));
                    s.setTotalInStock(rs.getInt("TotalInStock"));
                    s.setTotalSold(rs.getInt("TotalSold"));
                    return s;
                }
            }
        }
        return null;
    }

    public ProductStats getProductStatsById(int productStatsId) throws SQLException {
        String sql = "SELECT * FROM ProductStats WHERE ProductStatsId = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, productStatsId);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    ProductStats s = new ProductStats();
                    s.setProductStatsId(rs.getInt("ProductStatsId"));
                    s.setProductId(rs.getInt("ProductId"));
                    s.setSize(rs.getString("Size"));
                    s.setColor(rs.getString("Color"));
                    s.setTotalInStock(rs.getInt("TotalInStock"));
                    s.setTotalSold(rs.getInt("TotalSold"));
                    return s;
                }
            }
        }
        return null;
    }
}
