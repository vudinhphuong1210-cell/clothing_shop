package controller.Admin;

import constant.AccountStatus;
import dal.clothingShopDal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AdminEmployeeServlet", urlPatterns = {"/AdminEmployeeControl"})
public class AdminEmployeeControlServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AdminEmployeeControlServlet.class.getName());

    // =====================================================
    // GET: list / add / edit
    // =====================================================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        clothingShopDal del = new clothingShopDal();

        try {
            switch (action) {

                // ===== LIST EMPLOYEE =====
                case "list": {
                    List<Employee> employees = del.getAllEmployees();
                    request.setAttribute("employees", employees);
                    request.setAttribute("totalEmployees", employees.size());
                    request.getRequestDispatcher("/Admin/Employee/ListEmployees.jsp")
                           .forward(request, response);
                    break;
                }

                // ===== SHOW ADD FORM =====
                case "add": {
                    request.getRequestDispatcher("/Admin/Employee/AddEmployee.jsp")
                           .forward(request, response);
                    break;
                }

                // ===== SHOW EDIT FORM =====
                case "edit": {
                    String idRaw = request.getParameter("id");
                    if (idRaw == null || idRaw.isBlank()) {
                        response.sendRedirect("AdminEmployee");
                        return;
                    }

                    int employeeId = Integer.parseInt(idRaw);
                    Employee emp = del.getEmployeeById(employeeId);

                    if (emp == null) {
                        response.sendRedirect("AdminEmployee?error=notfound");
                        return;
                    }

                    request.setAttribute("employee", emp);
                    request.getRequestDispatcher("/Admin/Employee/EditEmployee.jsp")
                           .forward(request, response);
                    break;
                }

                default:
                    response.sendRedirect("AdminEmployeeControl");
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "SQL Error in AdminEmployeeServlet", ex);
            response.sendError(500);
        }
    }

    // =====================================================
    // POST: add / edit / toggleStatus
    // =====================================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("AdminEmployeeControl");
            return;
        }

        clothingShopDal del = new clothingShopDal();

        try {
            switch (action) {

                // ===== ADD EMPLOYEE =====
                case "add": {
                    String userName = request.getParameter("userName").trim();
                    String password = request.getParameter("password").trim();
                    String employeeName = request.getParameter("employeeName").trim();
                    String phone = request.getParameter("phone");
                    String position = request.getParameter("position");

                    // check username exists
                    if (del.isUserNameExists(userName)) {
                        request.setAttribute("error", "Tên đăng nhập đã tồn tại");
                        request.getRequestDispatcher("/Admin/Employee/AddEmployee.jsp")
                               .forward(request, response);
                        return;
                    }

                    // NOTE: nên hash password khi làm thật
                    del.createEmployee(userName, password, employeeName, phone, position);
                    response.sendRedirect("AdminEmployeeControl?msg=added");
                    break;
                }

                // ===== EDIT EMPLOYEE =====
                case "edit": {
                    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                    String employeeName = request.getParameter("employeeName").trim();
                    String phone = request.getParameter("phone");
                    String position = request.getParameter("position");

                    del.updateEmployee(employeeId, employeeName, phone, position);
                    response.sendRedirect("AdminEmployeeControl?msg=updated");
                    break;
                }

                // ===== TOGGLE ACCOUNT STATUS =====
                case "toggleStatus": {
                    int accountId = Integer.parseInt(request.getParameter("accountId"));
                    String currentStatus = request.getParameter("currentStatus");

                    AccountStatus newStatus =
                            "ACTIVE".equalsIgnoreCase(currentStatus)
                                    ? AccountStatus.INACTIVE
                                    : AccountStatus.ACTIVE;

                    del.updateAccountStatus(accountId, newStatus);
                    response.sendRedirect("AdminEmployeeControl");
                    break;
                }

                default:
                    response.sendRedirect("AdminEmployeeControl");
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "SQL Error in AdminEmployeeServlet", ex);
            response.sendError(500);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Unexpected Error", ex);
            response.sendError(500);
        }
    }
}