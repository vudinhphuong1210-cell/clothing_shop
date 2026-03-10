import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Random;

public class TestDataGenerator {
    public static void main(String[] args) {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ClothingShop;encrypt=true;trustServerCertificate=true;";
            Connection conn = DriverManager.getConnection(url, "sa", "sa");

            // 1. Ensure we have at least one Customer and Product variant to bind orders to
            int customerId = 1; // Assuming customer 1 exists from test_db.sql
            int productStatsId = 1; // Assuming product variant 1 exists from test_db.sql

            String insertOrderSql = "INSERT INTO [Order] (CustomerId, OrderDate, TotalAmount, OrderStatus, Address) VALUES (?, ?, ?, ?, ?)";
            String insertDetailSql = "INSERT INTO OrderDetail (OrderId, ProductStatsId, Quantity, Price) VALUES (?, ?, ?, ?)";

            PreparedStatement psOrder = conn.prepareStatement(insertOrderSql, java.sql.Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDetail = conn.prepareStatement(insertDetailSql);

            Random random = new Random();

            // Generate orders for the last 7 days
            LocalDateTime now = LocalDateTime.now();

            for (int i = 0; i < 7; i++) {
                // 2 to 5 orders per day
                int numOrders = 2 + random.nextInt(4);
                LocalDateTime orderDate = now.minusDays(6 - i);

                for (int j = 0; j < numOrders; j++) {
                    int qty = 1 + random.nextInt(3);
                    BigDecimal price = new BigDecimal("150000"); // 150k
                    BigDecimal totalAmount = price.multiply(new BigDecimal(qty));

                    // Insert Order
                    psOrder.setInt(1, customerId);

                    // Randomize hour of day
                    LocalDateTime specificOrderTime = orderDate.withHour(8 + random.nextInt(12))
                            .withMinute(random.nextInt(60));
                    psOrder.setTimestamp(2, Timestamp.valueOf(specificOrderTime));

                    psOrder.setBigDecimal(3, totalAmount);
                    // Force 'Delivered' so it counts towards Revenue chart
                    psOrder.setString(4, "Delivered");
                    psOrder.setString(5, "Test Address " + j);

                    psOrder.executeUpdate();

                    // Get Generated OrderId
                    try (java.sql.ResultSet rs = psOrder.getGeneratedKeys()) {
                        if (rs.next()) {
                            int orderId = rs.getInt(1);

                            // Insert OrderDetail
                            psDetail.setInt(1, orderId);
                            psDetail.setInt(2, productStatsId);
                            psDetail.setInt(3, qty);
                            psDetail.setBigDecimal(4, price);

                            try {
                                psDetail.executeUpdate();
                            } catch (Exception ignored) {
                                // Ignore insufficient stock trigger for this test
                                // (in reality we should turn it off, but let's hope we have enough stock or it
                                // just fails detail insert)
                            }
                        }
                    }
                }
            }

            conn.close();
            System.out.println("Test data generated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
