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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

/**
 *
 * @author Admin
 */
@WebServlet(name="AdminDashboard", urlPatterns={"/AdminDashboard"})
public class AdminDashboard extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       try {
           
    clothingShopDal cs = new clothingShopDal();
    int total = cs.getTotalOrders();
    List<Order> eachOrder=cs.getAllOrders();
    request.setAttribute("totalOrders", total);   
    request.setAttribute("eachOrder", eachOrder);
    request.getRequestDispatcher("AdminDashboard.jsp")
           .forward(request, response);
} catch (Exception ex) {
    Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
}
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
