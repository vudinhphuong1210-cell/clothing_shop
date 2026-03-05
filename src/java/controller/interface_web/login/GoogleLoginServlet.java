package controller.interface_web.login;

import dal.AccountDAL;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Customer;
import util.GoogleUtils;
import constant.UserRole;
import constant.AccountStatus;
import java.time.LocalDateTime;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "GoogleLoginServlet", urlPatterns = {"/googleLogin"})
public class GoogleLoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GoogleLoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code  = request.getParameter("code");
        String state = request.getParameter("state");

        if (code == null || code.isEmpty()) {
            // Step 1: Redirect to Google OAuth, kèm state chống CSRF
            String csrfToken = UUID.randomUUID().toString();
            request.getSession().setAttribute("oauth_state", csrfToken);

            String authUrl = "https://accounts.google.com/o/oauth2/auth?"
                    + "client_id="     + URLEncoder.encode(GoogleUtils.getClientId(), StandardCharsets.UTF_8)
                    + "&redirect_uri=" + URLEncoder.encode(GoogleUtils.getRedirectUri(), StandardCharsets.UTF_8)
                    + "&response_type=code"
                    + "&scope="        + URLEncoder.encode("email profile openid", StandardCharsets.UTF_8)
                    + "&state="        + csrfToken;

            response.sendRedirect(authUrl);

        } else {
            // Step 2: Callback từ Google
            HttpSession session = request.getSession();

            // Kiểm tra CSRF state
            String savedState = (String) session.getAttribute("oauth_state");
            session.removeAttribute("oauth_state");
            if (savedState == null || !savedState.equals(state)) {
                request.setAttribute("error", "Yêu cầu không hợp lệ (CSRF detected).");
                request.getRequestDispatcher("/view/home/login.jsp").forward(request, response);
                return;
            }

            try {
                String accessToken = GoogleUtils.getToken(code);
                Map<String, String> userInfo = GoogleUtils.getUserInfo(accessToken);
                String email = userInfo.get("email");
                String name  = userInfo.get("name");

                AccountDAL accountDAL = new AccountDAL();
                Account account = accountDAL.getAccountByEmail(email);

                if (account == null) {
                    // Tự động đăng ký tài khoản mới
                    account = new Account();
                    account.setUserName(email);
                    account.setPassword("");
                    account.setRole(UserRole.CUSTOMER);
                    account.setStatus(AccountStatus.ACTIVE);
                    account.setCreatedAt(LocalDateTime.now());

                    Customer customer = new Customer();
                    customer.setFullName(name);
                    customer.setEmail(email);
                    customer.setPoint(0);
                    customer.setCreatedAt(LocalDateTime.now());

                    boolean registered = accountDAL.registerCustomer(account, customer);
                    if (!registered) {
                        throw new Exception("Không thể tạo tài khoản Google.");
                    }
                    account = accountDAL.getAccountByEmail(email);
                }

                if (account == null) {
                    throw new Exception("Không tìm thấy tài khoản sau khi đăng ký.");
                }

                // Kiểm tra trạng thái tài khoản
                if (account.getStatus() != AccountStatus.ACTIVE) {
                    request.setAttribute("error", "Tài khoản của bạn đã bị khóa. Vui lòng liên hệ hỗ trợ.");
                    request.getRequestDispatcher("/view/home/login.jsp").forward(request, response);
                    return;
                }

                // Lưu session và redirect theo role
                session.setAttribute("account", account);
                redirectByRole(account.getRole(), request, response);

            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Google Login Error", ex);
                request.setAttribute("error", "Đăng nhập Google thất bại. Vui lòng thử lại.");
                request.getRequestDispatcher("/view/home/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void redirectByRole(UserRole role, HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String ctx = req.getContextPath();
        switch (role) {
            case ADMIN    -> res.sendRedirect(ctx + "/AdminDashboard");
            case EMPLOYEE -> res.sendRedirect(ctx + "/EmployeeDashboard");
            default       -> res.sendRedirect(ctx + "/home");
        }
    }
}