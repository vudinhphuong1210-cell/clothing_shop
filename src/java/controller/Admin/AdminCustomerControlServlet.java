package controller.Admin;

import constant.AccountStatus;
import static constant.AccountStatus.INACTIVE;
import dal.clothingShopDal;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

@WebServlet(name = "AdminCustomerControl", urlPatterns = {"/AdminCustomerControl"})
public class AdminCustomerControlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            clothingShopDal cs = new clothingShopDal();
            List<Customer> customers = cs.getAllCustomers();

            List<Customer> customersActive   = new ArrayList<>();
            List<Customer> customersInactive = new ArrayList<>();
            List<Customer> customersBanned   = new ArrayList<>();

            request.setAttribute("totalCustomers", customers.size());
            request.setAttribute("customers", customers);

            for (Customer c : customers) {
                switch (c.getAccount().getStatus()) {
                    case ACTIVE   -> customersActive.add(c);
                    case INACTIVE -> customersInactive.add(c);
                    case BANNED   -> customersBanned.add(c);
                }
            }

            String statusParam = request.getParameter("status");

            // ✅ SỬA: kiểm tra null VÀ rỗng trước khi parse enum
            // Trường hợp chọn "-- All --" sẽ gửi status="" → vào else → hiện tất cả
            if (statusParam != null && !statusParam.trim().isEmpty()) {
                try {
                    AccountStatus status = AccountStatus.valueOf(statusParam.toUpperCase());

                    switch (status) {
                        case ACTIVE -> {
                            request.setAttribute("totalCustomerActive", customersActive.size());
                            request.setAttribute("customerActive", customersActive);
                            request.getRequestDispatcher("Admin/Customer/CustomersActive.jsp")
                                    .forward(request, response);
                            return;
                        }
                        case INACTIVE -> {
                            request.setAttribute("totalCustomerInactive", customersInactive.size());
                            request.setAttribute("customerInactive", customersInactive);
                            request.getRequestDispatcher("Admin/Customer/CustomersInactive.jsp")
                                    .forward(request, response);
                            return;
                        }
                        case BANNED -> {
                            request.setAttribute("totalCustomerBanned", customersBanned.size());
                            request.setAttribute("customerBanned", customersBanned);
                            request.getRequestDispatcher("Admin/Customer/CustomersBanned.jsp")
                                    .forward(request, response);
                            return;
                        }
                    }

                } catch (IllegalArgumentException e) {
                    // ✅ SỬA: bắt lỗi nếu status không hợp lệ → fallthrough hiện tất cả
                    Logger.getLogger(AdminCustomerControlServlet.class.getName())
                            .log(Level.WARNING, "Invalid status param: {0}", statusParam);
                }
            }

            // Mặc định: hiện toàn bộ danh sách
            request.getRequestDispatcher("/Admin/Customer/ListOfCustomers.jsp")
                    .forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(AdminCustomerControlServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Cannot load customer list");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountIdRaw = request.getParameter("accountId");
        String action       = request.getParameter("action");

        // ✅ Kiểm tra đầu vào không trống
        if (accountIdRaw != null && !accountIdRaw.trim().isEmpty()
                && action != null && !action.trim().isEmpty()) {
            try {
                int accountId    = Integer.parseInt(accountIdRaw);
                clothingShopDal cs = new clothingShopDal();

                if ("ban".equals(action)) {
                    cs.updateAccountStatus(accountId, AccountStatus.BANNED);
                } else if ("unban".equals(action)) {
                    cs.updateAccountStatus(accountId, AccountStatus.ACTIVE);
                }

            } catch (NumberFormatException e) {
                Logger.getLogger(AdminCustomerControlServlet.class.getName())
                        .log(Level.WARNING, "Invalid accountId format: {0}", accountIdRaw);
            } catch (Exception ex) {
                Logger.getLogger(AdminCustomerControlServlet.class.getName())
                        .log(Level.SEVERE, "Error executing Ban/Unban", ex);
            }
        }

        // Luôn redirect về danh sách sau khi xử lý
        response.sendRedirect("AdminCustomerControl");
    }
}