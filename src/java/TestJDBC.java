import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {
    public static void main(String[] args) {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ClothingShop;encrypt=true;trustServerCertificate=true;";
            Connection conn = DriverManager.getConnection(url, "sa", "sa");

            String sql = "SELECT c.CustomerId, c.AccountId, c.FullName, c.Phone, "
                    + "       c.Gender, c.Address, c.Point, c.CreatedAt, "
                    + "       a.AccountId, a.UserName, a.Email, a.Role, a.Status, a.CreatedAt AS AccountCreatedAt "
                    + "FROM Customer c "
                    + "JOIN Account a ON c.AccountId = a.AccountId "
                    + "ORDER BY c.CustomerId";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(sql);
            System.out.println("Query executed successfully!");

            conn.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
