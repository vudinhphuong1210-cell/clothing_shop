package filter;

import constant.UserRole;
import constant.AccountStatus;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/Admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        // Chưa login
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Account account = (Account) session.getAttribute("account");

        // Không có account
        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Không phải ADMIN
        if (account.getRole() != UserRole.ADMIN) {
            resp.sendRedirect(req.getContextPath() + "/access-denied.jsp");
            return;
        }

        // Account bị khóa
        if (account.getStatus() != AccountStatus.ACTIVE) {
            resp.sendRedirect(req.getContextPath() + "/account-blocked.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}