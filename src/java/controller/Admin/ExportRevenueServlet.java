package controller.Admin;

import dal.clothingShopDal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ExportRevenueServlet", urlPatterns = { "/ExportRevenue" })
public class ExportRevenueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int year = java.time.Year.now().getValue();
        String yearParam = request.getParameter("year");
        if (yearParam != null && !yearParam.isEmpty()) {
            try {
                year = Integer.parseInt(yearParam);
            } catch (NumberFormatException e) {
                // Ignore, keep default year
            }
        }

        clothingShopDal dao = new clothingShopDal();
        Map<String, Double> revenueData = dao.getRevenueByYear(year);

        // Set response headers for CSV download
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        String fileName = "revenue_" + year + ".csv";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // Write CSV content
        try (PrintWriter writer = response.getWriter()) {
            // write BOM for Excel UTF-8
            writer.write('\ufeff');
            writer.println("Tháng,Doanh Thu");

            if (revenueData.isEmpty()) {
                writer.println("Không có dữ liệu,0");
            } else {
                for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
                    writer.println(entry.getKey() + "," + entry.getValue());
                }
            }
        }
    }
}
