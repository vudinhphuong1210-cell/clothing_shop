/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.clothingShopDal;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "productServlet", urlPatterns = {"/product"})
public class productServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            clothingShopDal csd = new clothingShopDal();
            List<Product> list = csd.getAllProduct();
            List<Category> listCate=csd.getAllCategory();
            request.setAttribute("listCate", listCate);
            for (Product p : list) {
                int sold = csd.getSold(p.getProductID());
                p.setTotalSold(sold);
            }
            request.setAttribute("listProduct", list);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (SQLException ex) {
    ex.printStackTrace();   // BẮT BUỘC
    response.getWriter().print("ERROR IN PRODUCT SERVLET");
}

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
