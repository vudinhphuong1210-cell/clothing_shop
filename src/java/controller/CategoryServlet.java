package controller;

import dal.ProductDAL;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = 1; // Default to Woman
        try {
            if (idStr != null) {
                id = Integer.parseInt(idStr);
            }
        } catch (NumberFormatException e) {
            id = 1;
        }

        ProductDAL dal = new ProductDAL();
        try {
            List<Product> list = dal.getProductsByCategoryId(id);
            request.setAttribute("products", list);
            
            String categoryName = "";
            String forwardPath = "";
            switch (id) {
                case 1:
                    categoryName = "NỮ";
                    forwardPath = "view/product/woman/woman.jsp";
                    break;
                case 2:
                    categoryName = "NAM";
                    forwardPath = "view/product/men/men.jsp";
                    break;
                case 3:
                    categoryName = "TRẺ EM";
                    forwardPath = "view/product/children/children.jsp";
                    break;
                case 4:
                    categoryName = "TRẺ SƠ SINH";
                    forwardPath = "view/product/baby/baby.jsp";
                    break;
                default:
                    categoryName = "SẢN PHẨM";
                    forwardPath = "view/product/woman/woman.jsp"; // Fallback
                    break;
            }
            
            request.setAttribute("categoryName", categoryName);
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
