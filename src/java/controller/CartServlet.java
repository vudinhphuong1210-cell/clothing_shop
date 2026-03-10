package controller;

import dal.ProductDAL;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductStats;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            // View cart
            request.getRequestDispatcher("/view/cart/cart.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
        }

        try {
            ProductDAL dal = new ProductDAL();

            if ("add".equals(action) || "buyNow".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int quantity = 1;
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                String color = request.getParameter("color");
                if (color == null) color = "";
                String size = request.getParameter("size");
                if (size == null) size = "";

                Product p = dal.getProductById(id);
                ProductStats stats = null;
                if (!color.isEmpty() && !size.isEmpty()) {
                     stats = dal.getProductStats(id, color, size); 
                }
                
                // Nếu lấy theo color/size KHÔNG ra kết quả, HOẶC user không truyền (vd add từ trang home)
                // Lấy id của biến thể đầu tiên cho linh động
                if(stats == null && p != null && p.getVariants() != null && !p.getVariants().isEmpty()) {
                   stats = dal.getProductStatsById(p.getVariants().get(0).getProductStatsId());
                }

                if (p != null && stats != null) {
                    OrderDetail detail = new OrderDetail();
                    detail.setProductStatsId(stats.getProductStatsId());
                    detail.setProductId(p.getProductId()); // ID cho liên kết tới URL chi tiết
                    detail.setProductName(p.getProductName());
                    detail.setPrice(p.getPrice());
                    detail.setQuantity(quantity);
                    detail.setProductImage(p.getImage());
                    detail.setColor(stats.getColor());
                    detail.setSize(stats.getSize());

                    order.addOrderDetail(detail);
                    session.setAttribute("order", order);
                    session.setAttribute("cartSize", order.getOrderDetails().size());
                }

                if ("buyNow".equals(action)) {
                    response.sendRedirect(request.getContextPath() + "/cart");
                } else {
                    String referer = request.getHeader("referer");
                    if (referer != null) {
                        response.sendRedirect(referer);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/home");
                    }
                }
            } else if ("remove".equals(action)) {
                int productStatsId = Integer.parseInt(request.getParameter("statsId"));
                order.removeOrderDetail(productStatsId);
                
                session.setAttribute("order", order);
                session.setAttribute("cartSize", order.getOrderDetails().size());
                response.sendRedirect(request.getContextPath() + "/cart");

            } else if ("update".equals(action)) {
                int productStatsId = Integer.parseInt(request.getParameter("statsId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                
                order.updateQuantity(productStatsId, quantity);
                session.setAttribute("order", order);
                session.setAttribute("cartSize", order.getOrderDetails().size());
                response.sendRedirect(request.getContextPath() + "/cart");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("cartError", "Lỗi thêm giỏ hàng: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
