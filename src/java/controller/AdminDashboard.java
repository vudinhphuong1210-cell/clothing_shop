/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import constant.OrderStatus;
import dal.clothingShopDal;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    List<Order> eachOrder=cs.getAllOrders();
    List<Order> eachOrderDelivered= new ArrayList<>();
    List<Order> eachOrderPending= new ArrayList<>();
    List<Order> eachOrderCancelled= new ArrayList<>();
    List<Order> eachOrderShipped= new ArrayList<>();
    List<Order> eachOrderConfirmed= new ArrayList<>();
    request.setAttribute("totalOrders", eachOrder.size());   
    request.setAttribute("eachOrder", eachOrder);
    
    for (Order o : eachOrder) {
    switch (o.getOrderStatus()) {
        case DELIVERED -> eachOrderDelivered.add(o);
        case SHIPPED -> eachOrderShipped.add(o);
        case CANCELLED -> eachOrderCancelled.add(o);
        case CONFIRMED -> eachOrderConfirmed.add(o);
        case PENDING -> eachOrderPending.add(o);
    }
}
   String statusParam = request.getParameter("status");

if (statusParam != null) {
    OrderStatus status = OrderStatus.valueOf(statusParam.toUpperCase());

    switch (status) {
        case DELIVERED -> {
            request.setAttribute("totalOrdersDelivered", eachOrderDelivered.size());
            request.setAttribute("eachOrderDelivered", eachOrderDelivered);
            request.getRequestDispatcher("Admin/AdminDashboardDelivered.jsp").forward(request, response);
            return;
        }
        case SHIPPED -> {
            request.setAttribute("totalOrdersShipped", eachOrderShipped.size());
            request.setAttribute("eachOrderShipped", eachOrderShipped);
            request.getRequestDispatcher("Admin/AdminDashboardShipped.jsp").forward(request, response);
            return;
        }
        case CANCELLED -> {
            request.setAttribute("totalOrdersCancelled", eachOrderCancelled.size());
            request.setAttribute("eachOrderCancelled", eachOrderCancelled);
            request.getRequestDispatcher("Admin/AdminDashboardCancelled.jsp").forward(request, response);
            return;
        }
        case CONFIRMED -> {
            request.setAttribute("totalOrdersConfirm", eachOrderConfirmed.size());
            request.setAttribute("eachOrderConfirm", eachOrderConfirmed);
            request.getRequestDispatcher("Admin/AdminDashboardConfirmed.jsp").forward(request, response);
            return;
        }
        case PENDING -> {
            request.setAttribute("totalOrdersPending", eachOrderPending.size());
            request.setAttribute("eachOrderPending", eachOrderPending);
            request.getRequestDispatcher("Admin/AdminDashboardPending.jsp").forward(request, response);
            return;
        }
    }
}
    request.getRequestDispatcher("Admin/AdminDashboard.jsp")
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
