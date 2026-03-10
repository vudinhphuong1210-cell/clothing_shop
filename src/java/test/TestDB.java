package test;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDB extends DBContext {
    public static void main(String[] args) {
        TestDB test = new TestDB();
        try {
            Connection conn = test.connection;
            if (conn != null) {
                System.out.println("Connection works!");
                PreparedStatement st = conn.prepareStatement("SELECT 1 AS Result");
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    System.out.println("Query execution successful: " + rs.getInt("Result"));
                }
            } else {
                System.out.println("Connection is NULL. DBContext initialization failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
