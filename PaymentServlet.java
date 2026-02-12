/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.PaymentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import model.Payment;
import model.PaymentMethod;
import model.PaymentStatus;
import vnpay.Config;

public class PaymentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String methodRaw = request.getParameter("paymentMethod");
        PaymentMethod method = PaymentMethod.valueOf(methodRaw);

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setPaymentMethod(method);
        payment.setPaymentStatus(PaymentStatus.Pending);

        PaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.insert(payment);

        if (method == PaymentMethod.COD) {
            handleCOD(request, response);
        } else if (method == PaymentMethod.VNPAY) {
            handleVNPAY(request, response, orderId);
        }
    }

    private void handleCOD(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("thankyou.jsp?msg=Order placed successfully with COD");
    }

    private void handleVNPAY(HttpServletRequest request, HttpServletResponse response, int orderId) throws IOException {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getOrderByID(orderId);

        if (order != null) {
            double totalBill = order.getTotalAmount();
            response.sendRedirect("ajaxServlet?totalBill=" + totalBill + "&orderId=" + orderId);
        } else {
            response.sendRedirect("cart?error=OrderNotFound");
        }
    }
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
