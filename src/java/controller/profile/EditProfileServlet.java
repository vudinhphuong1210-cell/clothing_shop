/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.profile;

import java.io.IOException;
import java.io.PrintWriter;
import dal.CustomerDAL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Customer;

/**
 *
 * @author Admin
 */
@WebServlet(name="EditProfileServlet", urlPatterns={"/editprofile"})
public class EditProfileServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet EditProfileServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        CustomerDAL customerDAL = new CustomerDAL();
        Customer customer = customerDAL.getCustomerByAccountId(account.getAccountId());
        
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("view/home/editProfile.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Set character encoding for Vietnamese text
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String genderStr = request.getParameter("gender");
        String address = request.getParameter("address");
        
        CustomerDAL customerDAL = new CustomerDAL();
        Customer customer = customerDAL.getCustomerByAccountId(account.getAccountId());
        
        boolean isNew = false;
        if (customer == null) {
            customer = new Customer();
            customer.setAccountId(account.getAccountId());
            isNew = true;
        }
        
        customer.setFullName(fullName);
        customer.setPhone(phone);
        customer.setEmail(email);
        if (genderStr != null && !genderStr.isEmpty()) {
            try {
                customer.setGender(constant.Gender.valueOf(genderStr.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // ignore invalid gender
            }
        }
        customer.setAddress(address);
        
        boolean success;
        if (isNew) {
            success = customerDAL.insertCustomer(customer);
        } else {
            success = customerDAL.updateCustomer(customer);
        }
        
        if (success) {
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            request.setAttribute("error", "Failed to " + (isNew ? "create" : "update") + " profile in database.");
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("view/home/editProfile.jsp").forward(request, response);
        }
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
