package dal;

import constant.AccountStatus;
import constant.EmployeeStatus;
import constant.Gender;
import constant.OrderStatus;
import constant.ProductStatus;
import constant.UserRole;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Types;
import model.Account;
import model.Category;
import model.Customer;
import model.Order;
import model.Product;
import model.ProductStats;
import java.sql.Statement;
import model.Employee;
import model.TopProduct;

public class clothingShopDal extends DBContext {
    // ===============================
    // Function Admin
    // ===============================

    // LẤY DANH SÁCH ĐƠN HÀNG
    public List<Order> getAllOrders() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order] ORDER BY OrderDate DESC";

        try (PreparedStatement pstm = connection.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {

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

    // LẤY DANH SÁCH KHÁCH HÀNG
    // Đây là đoạn code mẫu cho phương thức getAllCustomers() trong DAL
    // Phải JOIN Account để lấy đủ thông tin cho JSP dùng ${c.account.status}
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new ArrayList<>();

        // ✅ JOIN Account để lấy đủ thông tin
        String sql = "SELECT c.CustomerId, c.AccountId, c.FullName, c.Phone, "
                + "       c.Gender, c.Address, c.Point, c.CreatedAt, "
                + "       a.AccountId, a.UserName, a.Email, a.Role, a.Status, a.CreatedAt AS AccountCreatedAt "
                + "FROM Customer c "
                + "JOIN Account a ON c.AccountId = a.AccountId "
                + "ORDER BY c.CustomerId";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Map Customer
                Customer c = new Customer();
                Account a = new Account(); // Initialize Account early
                c.setAccount(a);

                c.setCustomerId(rs.getInt("CustomerId"));
                c.setAccountId(rs.getInt("AccountId"));
                c.setFullName(rs.getString("FullName"));
                c.setPhone(rs.getString("Phone"));
                c.setEmail(rs.getString("Email")); // This now delegates to Account via c.setAccount()
                c.setPoint(rs.getInt("Point"));
                c.setAddress(rs.getString("Address"));

                String genderStr = rs.getString("Gender");
                if (genderStr != null) {
                    try {
                        c.setGender(Gender.valueOf(genderStr.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        c.setGender(null);
                    }
                }

                Timestamp createdTs = rs.getTimestamp("CreatedAt");
                if (createdTs != null) {
                    c.setCreatedAt(createdTs.toLocalDateTime());
                }

                // ✅ Continue Mapping Account
                a.setAccountId(rs.getInt("AccountId"));
                a.setUserName(rs.getString("UserName"));
                // Email is already set above via c.setEmail()

                String roleStr = rs.getString("Role");
                if (roleStr != null) {
                    try {
                        a.setRole(UserRole.valueOf(roleStr.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        a.setRole(UserRole.CUSTOMER);
                    }
                }

                String statusStr = rs.getString("Status");
                if (statusStr != null) {
                    try {
                        a.setStatus(AccountStatus.valueOf(statusStr.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        a.setStatus(AccountStatus.ACTIVE);
                    }
                }

                Timestamp accountCreatedTs = rs.getTimestamp("AccountCreatedAt");
                if (accountCreatedTs != null) {
                    a.setCreatedAt(accountCreatedTs.toLocalDateTime());
                }

                // ✅ Gắn account vào customer — JSP mới dùng được ${c.account.status}
                c.setAccount(a);

                list.add(c);
            }
        }
        return list;
    }

    public List<TopProduct> getTop10BestSelling() throws SQLException {
        List<TopProduct> list = new ArrayList<>();
        String sql = "SELECT TOP 10 "
                + "    p.ProductId, "
                + "    p.ProductName, "
                + "    p.Image, "
                + "    p.Price, "
                + "    c.CategoryName, "
                + "    SUM(od.Quantity)           AS TotalSold, "
                + "    SUM(od.Quantity * od.Price) AS TotalRevenue "
                + "FROM Product p "
                + "JOIN ProductStats  ps ON p.ProductId      = ps.ProductId "
                + "JOIN OrderDetail   od ON ps.ProductStatsId = od.ProductStatsId "
                + "JOIN [Order]       o  ON od.OrderId        = o.OrderId "
                + "LEFT JOIN Category c  ON p.CategoryId      = c.CategoryId "
                + "WHERE o.OrderStatus = 'Delivered' "
                + "GROUP BY p.ProductId, p.ProductName, p.Image, p.Price, c.CategoryName "
                + "ORDER BY TotalSold DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            int rank = 1;
            while (rs.next()) {
                TopProduct tp = new TopProduct();
                tp.setRank(rank++);
                tp.setProductId(rs.getInt("ProductId"));
                tp.setProductName(rs.getString("ProductName"));
                tp.setImage(rs.getString("Image"));
                tp.setPrice(rs.getBigDecimal("Price"));
                tp.setCategoryName(rs.getString("CategoryName"));
                tp.setTotalSold(rs.getInt("TotalSold"));
                tp.setTotalRevenue(rs.getBigDecimal("TotalRevenue"));
                list.add(tp);
            }
        }
        return list;
    }

    // ===== KPI =====
    public int getTotalOrders() {
        String sql = "SELECT COUNT(*) FROM [Order]";
        return getIntValue(sql);
    }

    public int getPendingOrders() {
        String sql = "SELECT COUNT(*) FROM [Order] WHERE OrderStatus = 'Pending'";
        return getIntValue(sql);
    }

    public int getTotalCustomers() {
        String sql = "SELECT COUNT(*) FROM Customer";
        return getIntValue(sql);
    }

    public double getRevenueThisMonth() {
        String sql = """
                    SELECT ISNULL(SUM(TotalAmount), 0)
                    FROM [Order]
                    WHERE OrderStatus = 'Delivered'
                      AND OrderDate >= ?
                      AND OrderDate <= ?
                """;

        LocalDateTime start = LocalDate.now()
                .withDayOfMonth(1)
                .atStartOfDay();
        LocalDateTime now = LocalDateTime.now();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(start));
            ps.setTimestamp(2, Timestamp.valueOf(now));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ===== Charts =====
    public Map<String, Integer> getOrdersByStatus() {
        Map<String, Integer> map = new LinkedHashMap<>();
        String sql = "SELECT OrderStatus, COUNT(*) cnt FROM [Order] GROUP BY OrderStatus";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("OrderStatus"), rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Double> getRevenueLast7Days() {
        Map<String, Double> map = new LinkedHashMap<>();
        String sql = """
                    SELECT CAST(OrderDate AS DATE) d, SUM(TotalAmount) revenue
                    FROM [Order]
                    WHERE OrderStatus = 'Delivered'
                      AND OrderDate >= DATEADD(DAY, -6, GETDATE())
                    GROUP BY CAST(OrderDate AS DATE)
                    ORDER BY d
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getDate("d").toString(), rs.getDouble("revenue"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // ===== Revenue by Year (monthly) =====
    public Map<String, Double> getRevenueByYear(int year) {
        Map<String, Double> map = new LinkedHashMap<>();

        // Check if there are any orders in this year
        String checkSql = "SELECT COUNT(*) FROM [Order] WHERE OrderStatus = 'Delivered' AND YEAR(OrderDate) = ?";
        boolean hasOrders = false;
        try (PreparedStatement ps = connection.prepareStatement(checkSql)) {
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    hasOrders = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!hasOrders) {
            return map; // empty map -> will not display this year's chart
        }

        // Initialize 12 months with 0
        for (int i = 1; i <= 12; i++) {
            map.put("Tháng " + i, 0.0);
        }

        String sql = """
                    SELECT MONTH(OrderDate) AS month,
                           SUM(TotalAmount) AS revenue
                    FROM [Order]
                    WHERE OrderStatus = 'Delivered'
                      AND YEAR(OrderDate) = ?
                    GROUP BY MONTH(OrderDate)
                    ORDER BY month
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int m = rs.getInt("month");
                    map.put("Tháng " + m, rs.getDouble("revenue"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // ===== Tables =====
    public List<Map<String, Object>> getLatestOrders(int limit) {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = """
                    SELECT TOP (?) OrderID, CustomerID, TotalAmount, OrderStatus, OrderDate
                    FROM [Order]
                    ORDER BY OrderDate DESC
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("OrderID"));
                row.put("customer", rs.getInt("CustomerID"));
                row.put("total", rs.getDouble("TotalAmount"));
                row.put("status", rs.getString("OrderStatus"));
                row.put("date", rs.getTimestamp("OrderDate"));
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Map<String, Object>> getLowStockProducts(int threshold) {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = """
                    SELECT ProductName, TotalInStock
                    FROM vw_ProductInventory
                    WHERE TotalInStock < ?
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("name", rs.getString("ProductName"));
                row.put("stock", rs.getInt("TotalInStock"));
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===== Helper =====
    private int getIntValue(String sql) {
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateAccountStatus(int accountId, AccountStatus status) throws Exception {
        String sql = "UPDATE Account SET Status = ? WHERE AccountId = ?";
        // Chuyển ACTIVE → "Active", BANNED → "Banned" để khớp với DB
        String statusStr = status.name().charAt(0) + status.name().substring(1).toLowerCase();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, statusStr);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }
    // =====================================================
    // 1. XEM DANH SÁCH PRODUCT (có JOIN Category)
    // =====================================================

    public List<Product> getAllProduct() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ProductId, p.CategoryId, p.ProductName, p.Price, "
                + "       p.Image, p.Status, p.Description, p.CreatedAt, p.UpdatedAt, "
                + "       c.CategoryName "
                + "FROM Product p "
                + "LEFT JOIN Category c ON p.CategoryId = c.CategoryId "
                + "ORDER BY p.CreatedAt DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapProduct(rs));
            }
        }
        return list;
    }

    // Lọc theo Status
    public List<Product> getProductsByStatus(ProductStatus status) throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ProductId, p.CategoryId, p.ProductName, p.Price, "
                + "       p.Image, p.Status, p.Description, p.CreatedAt, p.UpdatedAt, "
                + "       c.CategoryName "
                + "FROM Product p "
                + "LEFT JOIN Category c ON p.CategoryId = c.CategoryId "
                + "WHERE p.Status = ? "
                + "ORDER BY p.CreatedAt DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status.name().charAt(0)
                    + status.name().substring(1).toLowerCase()); // ACTIVE → Active
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapProduct(rs));
                }
            }
        }
        return list;
    }

    // Tìm kiếm theo tên sản phẩm
    public List<Product> getProductsByName(String search) throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ProductId, p.CategoryId, p.ProductName, p.Price, "
                + "       p.Image, p.Status, p.Description, p.CreatedAt, p.UpdatedAt, "
                + "       c.CategoryName "
                + "FROM Product p "
                + "LEFT JOIN Category c ON p.CategoryId = c.CategoryId "
                + "WHERE p.ProductName LIKE ? "
                + "ORDER BY p.CreatedAt DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + search + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapProduct(rs));
                }
            }
        }
        return list;
    }

    // Lấy 1 product theo ID
    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT p.ProductId, p.CategoryId, p.ProductName, p.Price, "
                + "       p.Image, p.Status, p.Description, p.CreatedAt, p.UpdatedAt, "
                + "       c.CategoryName "
                + "FROM Product p "
                + "LEFT JOIN Category c ON p.CategoryId = c.CategoryId "
                + "WHERE p.ProductId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapProduct(rs);
                }
            }
        }
        return null;
    }

    // =====================================================
    // 2. XEM TỒN KHO — dùng view vw_ProductInventory
    // (Admin cần xem cả Active lẫn Inactive nên query thẳng)
    // =====================================================
    public List<ProductStats> getProductInventory() throws SQLException {
        List<ProductStats> list = new ArrayList<>();

        String sql = """
                    SELECT ps.ProductStatsId,
                           ps.ProductId,
                           ps.Size,
                           ps.Color,
                           ps.TotalInStock,
                           ps.TotalSold,
                           ps.UpdatedAt
                    FROM ProductStats ps
                    ORDER BY ps.ProductId, ps.Size, ps.Color
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductStats psItem = new ProductStats();
                psItem.setProductStatsId(rs.getInt("ProductStatsId"));
                psItem.setProductId(rs.getInt("ProductId"));
                psItem.setSize(rs.getString("Size"));
                psItem.setColor(rs.getString("Color"));
                psItem.setTotalInStock(rs.getInt("TotalInStock"));
                psItem.setTotalSold(rs.getInt("TotalSold"));
                psItem.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());

                list.add(psItem);
            }
        }
        return list;
    }

    // Lấy tồn kho theo từng ProductStats của 1 product
    public List<ProductStats> getStatsByProductId(int productId) throws SQLException {
        List<ProductStats> list = new ArrayList<>();
        String sql = "SELECT ProductStatsId, ProductId, Size, Color, "
                + "       TotalInStock, TotalSold, UpdatedAt "
                + "FROM ProductStats "
                + "WHERE ProductId = ? "
                + "ORDER BY Size, Color";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductStats stat = new ProductStats();
                    stat.setProductStatsId(rs.getInt("ProductStatsId"));
                    stat.setProductId(rs.getInt("ProductId"));
                    stat.setSize(rs.getString("Size"));
                    stat.setColor(rs.getString("Color"));
                    stat.setTotalInStock(rs.getInt("TotalInStock"));
                    stat.setTotalSold(rs.getInt("TotalSold"));
                    Timestamp ts = rs.getTimestamp("UpdatedAt");
                    if (ts != null) {
                        stat.setUpdatedAt(ts.toLocalDateTime());
                    }
                    list.add(stat);
                }
            }
        }
        return list;
    }

    // =====================================================
    // 3. BẬT / TẮT BÁN SẢN PHẨM (đổi Status)
    // =====================================================
    public boolean updateProductStatus(int productId, ProductStatus status) throws SQLException {
        // Chuyển enum ACTIVE → "Active", INACTIVE → "Inactive" cho đúng DB
        String statusStr = status.name().charAt(0)
                + status.name().substring(1).toLowerCase();
        String sql = "UPDATE Product SET Status = ?, UpdatedAt = GETDATE() "
                + "WHERE ProductId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, statusStr);
            ps.setInt(2, productId);
            return ps.executeUpdate() > 0;
        }
    }

    // =====================================================
    // 4. THÊM PRODUCT MỚI
    // =====================================================
    public int addProduct(Product product) throws SQLException {

        // 1. Câu SQL: thêm 1 product mới
        String sql = """
                    INSERT INTO Product
                        (CategoryId, ProductName, Price, Image, Status, Description)
                    VALUES
                        (?, ?, ?, ?, ?, ?)
                """;

        // 2. Chuẩn bị statement + lấy ID tự tăng
        try (PreparedStatement ps = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            // CategoryId (có thể null)
            if (product.getCategoryId() != null) {
                ps.setInt(1, product.getCategoryId());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            // Thông tin sản phẩm
            ps.setString(2, product.getProductName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setString(5, "Active"); // mặc định khi tạo mới
            ps.setString(6, product.getDescription());

            // 3. Thực thi insert
            ps.executeUpdate();

            // 4. Lấy ProductId vừa tạo
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        // Insert thất bại
        return -1;
    }

    // =====================================================
    // 5. SỬA PRODUCT (giá, mô tả, tên, category)
    // =====================================================
    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product "
                + "SET CategoryId = ?, ProductName = ?, Price = ?, "
                + "    Image = ?, Description = ?, UpdatedAt = GETDATE() "
                + "WHERE ProductId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (product.getCategoryId() != null) {
                ps.setInt(1, product.getCategoryId());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setString(2, product.getProductName());
            ps.setBigDecimal(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setString(5, product.getDescription());
            ps.setInt(6, product.getProductId());

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Sửa 1 variant theo ProductStatsId. Dùng cho action "editVariant" (modal
     * sửa từ trang edit product). CẦN set ProductStatsId trước khi gọi!
     */
    public void updateProductStats(ProductStats s) throws SQLException {
        String sql = """
                    UPDATE ProductStats
                    SET Size = ?, Color = ?, TotalInStock = ?, UpdatedAt = GETDATE()
                    WHERE ProductStatsId = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getSize());
            ps.setString(2, s.getColor());
            ps.setInt(3, s.getTotalInStock());
            ps.setInt(4, s.getProductStatsId()); // ← bắt buộc phải có
            ps.executeUpdate();
        }
    }

    // Thêm variant mới (Size + Color + Stock)
    public boolean addProductVariant(int productId, String size, String color,
            int stock) throws SQLException {
        String sql = "INSERT INTO ProductStats (ProductId, Size, Color, TotalInStock) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setString(2, size);
            ps.setString(3, color);
            ps.setInt(4, stock);
            return ps.executeUpdate() > 0;
        }
    }

    // =====================================================
    // THÊM MỚI: addStock – cộng dồn tồn kho cho variant đã có
    // Dùng cho action "addStock" (form nhập thêm hàng)
    // =====================================================
    public boolean addStock(int productStatsId, int quantity) throws SQLException {
        String sql = """
                    UPDATE ProductStats
                    SET TotalInStock = TotalInStock + ?, UpdatedAt = GETDATE()
                    WHERE ProductStatsId = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productStatsId);
            return ps.executeUpdate() > 0;
        }
    }

    // =====================================================
    // THÊM MỚI: getProductIdByStatsId – lấy ProductId từ ProductStatsId
    // Dùng để redirect về đúng trang edit sau khi addStock
    // =====================================================
    public int getProductIdByStatsId(int productStatsId) throws SQLException {
        String sql = "SELECT ProductId FROM ProductStats WHERE ProductStatsId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productStatsId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ProductId");
                }
            }
        }
        return -1;
    }
    // // =====================================================
    // // 7. TOP SẢN PHẨM BÁN CHẠY
    // // =====================================================

    public List<ProductStats> getTopSellingProductStats(int top) throws SQLException {
        List<ProductStats> list = new ArrayList<>();

        String sql = """
                    SELECT TOP (?)
                           ps.ProductStatsId,
                           ps.ProductId,
                           ps.Size,
                           ps.Color,
                           ps.TotalInStock,
                           ps.TotalSold,
                           ps.UpdatedAt
                    FROM ProductStats ps
                    ORDER BY ps.TotalSold DESC
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, top);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductStats psItem = new ProductStats();
                    psItem.setProductStatsId(rs.getInt("ProductStatsId"));
                    psItem.setProductId(rs.getInt("ProductId"));
                    psItem.setSize(rs.getString("Size"));
                    psItem.setColor(rs.getString("Color"));
                    psItem.setTotalInStock(rs.getInt("TotalInStock"));
                    psItem.setTotalSold(rs.getInt("TotalSold"));
                    psItem.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());

                    list.add(psItem);
                }
            }
        }
        return list;
    }

    // =====================================================
    // 8. DANH SÁCH CATEGORY (để populate dropdown)
    // =====================================================
    public List<Category> getAllCategories() throws SQLException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT CategoryId, CategoryName, Description FROM Category ORDER BY CategoryName";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("CategoryId"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setDescription(rs.getString("Description"));
                list.add(c);
            }
        }
        return list;
    }

    // =====================================================
    // HELPER: map ResultSet → Product
    // =====================================================
    private Product mapProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("ProductId"));
        p.setCategoryId(rs.getInt("CategoryId"));
        p.setProductName(rs.getString("ProductName"));
        p.setPrice(rs.getBigDecimal("Price"));
        p.setImage(rs.getString("Image"));
        p.setDescription(rs.getString("Description"));

        // Status enum
        String statusStr = rs.getString("Status");
        if (statusStr != null) {
            try {
                p.setStatus(ProductStatus.valueOf(statusStr.toUpperCase()));
            } catch (IllegalArgumentException e) {
                p.setStatus(ProductStatus.ACTIVE);
            }
        }

        // Timestamps
        Timestamp createdTs = rs.getTimestamp("CreatedAt");
        if (createdTs != null) {
            p.setCreatedAt(createdTs.toLocalDateTime());
        }

        Timestamp updatedTs = rs.getTimestamp("UpdatedAt");
        if (updatedTs != null) {
            p.setUpdatedAt(updatedTs.toLocalDateTime());
        }

        // CategoryName từ JOIN (nếu có)
        try {
            String catName = rs.getString("CategoryName");
            if (catName != null) {
                Category cat = new Category();
                cat.setCategoryId(p.getCategoryId());
                cat.setCategoryName(catName);
                p.setCategory(cat);

            }
        } catch (SQLException ignored) {
        }

        return p;
    }

    // =====================================================
    // LẤY DANH SÁCH EMPLOYEE (JOIN Account)
    // =====================================================
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.EmployeeId, e.AccountId, e.EmployeeName, e.Phone, "
                + "       e.Position, e.Status, e.CreatedAt, "
                + "       a.UserName, a.Role, a.Status AS AccountStatus "
                + "FROM Employee e "
                + "JOIN Account a ON e.AccountId = a.AccountId "
                + "ORDER BY e.CreatedAt DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapEmployee(rs));
            }
        }
        return list;
    }

    // Lấy 1 employee theo ID
    public Employee getEmployeeById(int employeeId) throws SQLException {
        String sql = "SELECT e.EmployeeId, e.AccountId, e.EmployeeName, e.Phone, "
                + "       e.Position, e.Status, e.CreatedAt, "
                + "       a.UserName, a.Role, a.Status AS AccountStatus "
                + "FROM Employee e "
                + "JOIN Account a ON e.AccountId = a.AccountId "
                + "WHERE e.EmployeeId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEmployee(rs);
                }
            }
        }
        return null;
    }

    // Kiểm tra username đã tồn tại chưa
    public boolean isUserNameExists(String userName) throws SQLException {
        String sql = "SELECT 1 FROM Account WHERE UserName = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // =====================================================
    // TẠO TÀI KHOẢN + EMPLOYEE (transaction)
    // =====================================================
    public boolean createEmployee(String userName, String password,
            String employeeName, String phone,
            String position) throws SQLException {
        connection.setAutoCommit(false);
        try {
            // Bước 1: INSERT Account với Role = 'Employee'
            String sqlAccount = "INSERT INTO Account (UserName, Password, Role, Status) "
                    + "VALUES (?, ?, 'Employee', 'Active')";
            int accountId;
            try (PreparedStatement ps = connection.prepareStatement(
                    sqlAccount, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, userName);
                ps.setString(2, password); // nên hash trước khi truyền vào
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("Không lấy được AccountId");
                    }
                    accountId = keys.getInt(1);
                }
            }

            // Bước 2: INSERT Employee với AccountId vừa tạo
            String sqlEmployee = "INSERT INTO Employee (AccountId, EmployeeName, Phone, Position, Status) "
                    + "VALUES (?, ?, ?, ?, 'Active')";
            try (PreparedStatement ps = connection.prepareStatement(sqlEmployee)) {
                ps.setInt(1, accountId);
                ps.setString(2, employeeName);
                ps.setString(3, phone);
                ps.setString(4, position);
                ps.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // =====================================================
    // SỬA THÔNG TIN EMPLOYEE
    // =====================================================
    public boolean updateEmployee(int employeeId, String employeeName,
            String phone, String position) throws SQLException {
        String sql = "UPDATE Employee "
                + "SET EmployeeName = ?, Phone = ?, Position = ? "
                + "WHERE EmployeeId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, employeeName);
            ps.setString(2, phone);
            ps.setString(3, position);
            ps.setInt(4, employeeId);
            return ps.executeUpdate() > 0;
        }
    }

    // =====================================================
    // ĐỔI TRẠNG THÁI ACCOUNT (Active ↔ Inactive)
    // =====================================================
    // public boolean updateAccountStatus(int accountId, AccountStatus status)
    // throws SQLException {
    // // Chuyển enum → string đúng format DB: ACTIVE → "Active"
    // String statusStr = status.name().charAt(0)
    // + status.name().substring(1).toLowerCase();
    // String sql = "UPDATE Account SET Status = ? WHERE AccountId = ?";
    // try (PreparedStatement ps = connection.prepareStatement(sql)) {
    // ps.setString(1, statusStr);
    // ps.setInt(2, accountId);
    // return ps.executeUpdate() > 0;
    // }
    // }

    // =====================================================
    // HELPER: map ResultSet → Employee (có Account)
    // =====================================================
    private Employee mapEmployee(ResultSet rs) throws SQLException {
        Employee e = new Employee();
        e.setEmployeeId(rs.getInt("EmployeeId"));
        e.setAccountId(rs.getInt("AccountId"));
        e.setEmployeeName(rs.getString("EmployeeName"));
        e.setPhone(rs.getString("Phone"));
        e.setPosition(rs.getString("Position"));

        // Employee.Status
        String empStatus = rs.getString("Status");
        if (empStatus != null) {
            try {
                e.setStatus(EmployeeStatus.valueOf(empStatus.toUpperCase()));
            } catch (IllegalArgumentException ex) {
                e.setStatus(EmployeeStatus.ACTIVE);
            }
        }

        Timestamp ts = rs.getTimestamp("CreatedAt");
        if (ts != null) {
            e.setCreatedAt(ts.toLocalDateTime());
        }

        // Account
        Account a = new Account();
        a.setAccountId(rs.getInt("AccountId"));
        a.setUserName(rs.getString("UserName"));
        a.setRole(UserRole.EMPLOYEE);

        String accStatus = rs.getString("AccountStatus");
        if (accStatus != null) {
            try {
                a.setStatus(AccountStatus.valueOf(accStatus.toUpperCase()));
            } catch (IllegalArgumentException ex) {
                a.setStatus(AccountStatus.ACTIVE);
            }
        }
        e.setAccount(a);

        return e;
    }

    // ===============================
    // Function Admin
    // ===============================
}
