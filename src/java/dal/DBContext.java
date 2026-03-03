package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    protected Connection connection;

    public DBContext() {
        try {
            String user = "sa";
            String pass = "sa";
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=ClothingShop;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);

            Logger.getLogger(DBContext.class.getName()).log(Level.INFO, "Database connected successfully!");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "JDBC Driver not found!", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Cannot connect to database! Check URL/username/password.", ex);
        }
    }
}