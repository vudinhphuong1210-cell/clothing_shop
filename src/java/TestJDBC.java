import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestJDBC {
    public static void main(String[] args) {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ClothingShop;encrypt=true;trustServerCertificate=true;";
            Connection conn = DriverManager.getConnection(url, "sa", "sa");

            String sql = "SELECT FORMAT(OrderDate, 'yyyy-MM') AS month, SUM(TotalAmount) AS revenue " +
                    "FROM [Order] WHERE OrderStatus = 'Delivered' AND YEAR(OrderDate) = 2026 " +
                    "GROUP BY FORMAT(OrderDate, 'yyyy-MM') ORDER BY month";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getString("month") + ": " + rs.getDouble("revenue"));
                count++;
            }
            conn.close();
            System.out.println("Query executed successfully! Total rows: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
