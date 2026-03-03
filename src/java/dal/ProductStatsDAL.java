package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.ProductStats;

public class ProductStatsDAL extends DBContext {

    public List<ProductStats> getProductStatsByProductId(int productId) throws SQLException {
        List<ProductStats> statsList = new ArrayList<>();
        String sql = "SELECT * FROM ProductStats WHERE ProductId = ?";
        
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, productId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    ProductStats ps = new ProductStats();
                    ps.setProductStatsId(rs.getInt("ProductStatsId"));
                    ps.setProductId(rs.getInt("ProductId"));
                    ps.setSize(rs.getString("Size"));
                    ps.setColor(rs.getString("Color"));
                    ps.setTotalInStock(rs.getInt("TotalInStock"));
                    ps.setTotalSold(rs.getInt("TotalSold"));
                    Timestamp updatedTs = rs.getTimestamp("UpdatedAt");
                    if (updatedTs != null) {
                        ps.setUpdatedAt(updatedTs.toLocalDateTime());
                    }
                    statsList.add(ps);
                }
            }
        }
        return statsList;
    }
}
