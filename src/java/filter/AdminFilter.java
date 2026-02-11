package filter;

import constant.UserRole;
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

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        // 1. Chưa login
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        Account account = (Account) session.getAttribute("account");

        // 2. Không có account
        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 3. Không phải ADMIN
        if (account.getRole() != UserRole.ADMIN) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Admin only");
            return;
        }

        // 4. Account không active
        if (!"Active".equalsIgnoreCase(account.getStatus())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Account inactive");
            return;
        }

        chain.doFilter(request, response);
    }
}