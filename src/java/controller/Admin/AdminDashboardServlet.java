package controller.Admin;

import dal.clothingShopDal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AdminDashboard", urlPatterns = {"/AdminDashboard"})
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            clothingShopDal dashboardDAO = new clothingShopDal();
            
            request.setAttribute("totalOrders", dashboardDAO.getTotalOrders());
            request.setAttribute("pendingOrders", dashboardDAO.getPendingOrders());
            request.setAttribute("totalCustomers", dashboardDAO.getTotalCustomers());
            request.setAttribute("revenueThisMonth", dashboardDAO.getRevenueThisMonth());
            
            request.setAttribute("ordersByStatus", dashboardDAO.getOrdersByStatus());
            request.setAttribute("revenueLast7Days", dashboardDAO.getRevenueLast7Days());
            
            request.setAttribute("latestOrders", dashboardDAO.getLatestOrders(10));
            request.setAttribute("lowStockProducts", dashboardDAO.getLowStockProducts(5));
            
            // Top 10 bán chạy
            request.setAttribute("top10", dashboardDAO.getTop10BestSelling());
            
            request.getRequestDispatcher("/Admin/Dashboard/AdminDashboard.jsp")
                    .forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}