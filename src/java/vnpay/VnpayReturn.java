/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package vnpay;

import dal.OrderDAO;
import dal.PaymentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import model.OrderStatus;
import model.PaymentStatus;

@WebServlet("/vnpayReturn")
public class VnpayReturn extends HttpServlet {

    OrderDAO orderDAO = new OrderDAO();

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
        Map<String, String> fields = new HashMap<>();
        PaymentDAO paymentDAO = new PaymentDAO();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);

            if (fieldValue != null && fieldValue.length() > 0) {
                fields.put(fieldName,
                        URLEncoder.encode(fieldValue, StandardCharsets.UTF_8));
            }
        }

        String vnpSecureHash = request.getParameter("vnp_SecureHash");

        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        String signValue = Config.hashAllFields(fields);

        if (!signValue.equals(vnpSecureHash)) {
            request.setAttribute("transResult", false);
            request.setAttribute("message", "Chữ ký không hợp lệ!");
            request.getRequestDispatcher("paymentResult.jsp").forward(request, response);
            return;
        }

        String orderIdStr = request.getParameter("vnp_TxnRef");
        if (orderIdStr == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        String responseCode = request.getParameter("vnp_ResponseCode");
        String transactionStatus = request.getParameter("vnp_TransactionStatus");
        String transactionNo = request.getParameter("vnp_TransactionNo");

        int orderId = Integer.parseInt(orderIdStr);

        OrderStatus currentStatus = orderDAO.getOrderStatus(orderId);

        if (currentStatus != OrderStatus.Pending) {
            request.setAttribute("transResult", true);
            request.setAttribute("message", "Đơn hàng đã được xử lý trước đó.");
            request.getRequestDispatcher("paymentResult.jsp").forward(request, response);
            return;
        }

        boolean transSuccess = false;

        if ("00".equals(responseCode) && "00".equals(transactionStatus)) {

            orderDAO.updateOrderStatus(orderId, OrderStatus.Confirmed);

            paymentDAO.updatePaymentVNPStatus(orderId, PaymentStatus.Paid, transactionNo);

            transSuccess = true;

        } else {

            orderDAO.updateOrderStatus(orderId, OrderStatus.Cancelled);

            paymentDAO.updatePaymentVNPStatus(orderId, PaymentStatus.Failed, transactionNo);
        }

        request.setAttribute("transResult", transSuccess);
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("thankyou.jsp").forward(request, response);
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
        processRequest(request, response);
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
