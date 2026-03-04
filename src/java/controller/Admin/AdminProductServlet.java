package controller.Admin;

import constant.ProductStatus;
import dal.clothingShopDal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductStats;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
@WebServlet(name = "AdminProductServlet", urlPatterns = {"/AdminProduct"})
public class AdminProductServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AdminProductServlet.class.getName());

    // =====================================================
    // GET – danh sách, tồn kho, form
    // =====================================================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        clothingShopDal dao = new clothingShopDal();

        try {
            switch (action) {

                case "list" -> {
                    String statusParam = request.getParameter("status");
                    List<Product> products;

                    if (statusParam != null && !statusParam.isEmpty()) {
                        try {
                            ProductStatus status = ProductStatus.valueOf(statusParam.toUpperCase());
                            products = dao.getProductsByStatus(status);
                            request.setAttribute("selectedStatus", statusParam);
                        } catch (IllegalArgumentException e) {
                            products = dao.getAllProduct();
                        }
                    } else {
                        products = dao.getAllProduct();
                    }

                    request.setAttribute("products", products);
                    request.setAttribute("totalProducts", products.size());
                    request.setAttribute("categories", dao.getAllCategories());
                    request.getRequestDispatcher("/Admin/Product/ListProducts.jsp")
                            .forward(request, response);
                }

                case "inventory" -> {
                    List<ProductStats> inventory = dao.getProductInventory();
                    List<ProductStats> topSelling = dao.getTopSellingProductStats(5);

                    long lowStockCount = inventory.stream()
                            .filter(ps -> ps.getTotalInStock() != null && ps.getTotalInStock() < 5)
                            .count();

                    request.setAttribute("inventory", inventory);
                    request.setAttribute("topSelling", topSelling);
                    request.setAttribute("lowStockCount", lowStockCount);
                    request.getRequestDispatcher("/Admin/Product/ProductInventory.jsp")
                            .forward(request, response);
                }

                case "add" -> {
                    request.setAttribute("categories", dao.getAllCategories());
                    request.getRequestDispatcher("/Admin/Product/AddProduct.jsp")
                            .forward(request, response);
                }

                case "edit" -> {
                    String idRaw = request.getParameter("id");
                    if (idRaw == null || idRaw.isEmpty()) {
                        response.sendRedirect("AdminProduct");
                        return;
                    }

                    int productId = Integer.parseInt(idRaw);
                    Product product = dao.getProductById(productId);

                    if (product == null) {
                        response.sendRedirect("AdminProduct");
                        return;
                    }

                    List<ProductStats> stats = dao.getStatsByProductId(productId);

                    request.setAttribute("product", product);
                    request.setAttribute("stats", stats);
                    request.setAttribute("categories", dao.getAllCategories());
                    request.getRequestDispatcher("/Admin/Product/EditProduct.jsp")
                            .forward(request, response);
                }

                default -> response.sendRedirect("AdminProduct");
            }

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "AdminProduct doGet error", e);
            request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // =====================================================
    // POST – thêm, sửa, variant
    // =====================================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("AdminProduct");
            return;
        }

        clothingShopDal dao = new clothingShopDal();

        try {
            switch (action) {

                // ── 1. THÊM PRODUCT ──
                case "add" -> {
                    Product p = new Product();
                    p.setProductName(request.getParameter("productName"));
                    p.setPrice(new BigDecimal(request.getParameter("price")));
                    p.setDescription(request.getParameter("description"));

                    String catIdRaw = request.getParameter("categoryId");
                    if (catIdRaw != null && !catIdRaw.isEmpty()) {
                        p.setCategoryId(Integer.parseInt(catIdRaw));
                    }

                    Part imagePart = request.getPart("image");
                    String fileName = Paths.get(imagePart.getSubmittedFileName())
                            .getFileName().toString();
                    if (fileName != null && !fileName.isEmpty()) {
                        String uploadPath = getServletContext().getRealPath("/images/products");
                        new File(uploadPath).mkdirs();
                        imagePart.write(uploadPath + File.separator + fileName);
                        p.setImage("images/products/" + fileName);
                    }

                    int productId = dao.addProduct(p);

                    String size     = request.getParameter("size");
                    String color    = request.getParameter("color");
                    String stockRaw = request.getParameter("stock");

                    if ((size != null && !size.isBlank()) || (color != null && !color.isBlank())) {
                        int stock = stockRaw != null ? Integer.parseInt(stockRaw) : 0;
                        dao.addProductVariant(productId, size, color, stock);
                    }

                    response.sendRedirect("AdminProduct?action=list&msg=added");
                }

                // ── 2. SỬA THÔNG TIN PRODUCT ──
                case "edit" -> {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    Product p = dao.getProductById(productId);

                    if (p != null) {
                        p.setProductName(request.getParameter("productName"));
                        p.setPrice(new BigDecimal(request.getParameter("price")));
                        p.setDescription(request.getParameter("description"));
                        p.setImage(request.getParameter("image"));

                        String catIdRaw = request.getParameter("categoryId");
                        if (catIdRaw != null && !catIdRaw.isEmpty()) {
                            p.setCategoryId(Integer.parseInt(catIdRaw));
                        }

                        dao.updateProduct(p);
                    }

                    response.sendRedirect("AdminProduct?action=edit&id=" + productId + "&msg=updated");
                }

                // ── 3. SỬA 1 VARIANT (từ modal) ──
                case "editVariant" -> {
                    int productStatsId = Integer.parseInt(request.getParameter("productStatsId"));
                    int productId      = Integer.parseInt(request.getParameter("productId"));
                    String size        = request.getParameter("size");
                    String color       = request.getParameter("color");
                    int stock          = Integer.parseInt(request.getParameter("stock"));

                    ProductStats s = new ProductStats();
                    s.setProductStatsId(productStatsId);  // ✅ có ID để WHERE đúng row
                    s.setSize(size);
                    s.setColor(color);
                    s.setTotalInStock(stock);

                    dao.updateProductStats(s);

                    response.sendRedirect("AdminProduct?action=edit&id=" + productId + "&msg=variantUpdated");
                }

                // ── 4. THÊM VARIANT MỚI ──
                case "addVariant" -> {
                    int productId   = Integer.parseInt(request.getParameter("productId"));
                    String size     = request.getParameter("size");
                    String color    = request.getParameter("color");
                    String stockRaw = request.getParameter("stock");
                    int stock       = (stockRaw == null || stockRaw.isEmpty()) ? 0 : Integer.parseInt(stockRaw);

                    dao.addProductVariant(productId, size, color, stock);  // ✅ INSERT row mới

                    response.sendRedirect("AdminProduct?action=edit&id=" + productId + "&msg=variantAdded");
                }

                 // ── 5. NHẬP THÊM HÀNG (cộng dồn tồn kho) ──
                case "addStock" -> {
                    int productStatsId = Integer.parseInt(request.getParameter("productStatsId"));
                    int quantity       = Integer.parseInt(request.getParameter("quantity"));

                    dao.addStock(productStatsId, quantity);

                    // Lấy productId để redirect về đúng trang edit
                    int productId = dao.getProductIdByStatsId(productStatsId);
                    response.sendRedirect("AdminProduct");
                }
                
                // ── 5. TOGGLE STATUS ──
                case "toggleStatus" -> {
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    Product p = dao.getProductById(productId);

                    if (p != null) {
                        ProductStatus newStatus = p.getStatus().toggle();
                        dao.updateProductStatus(productId, newStatus);
                    }

                    response.sendRedirect("AdminProduct?action=list");
                }

                default -> response.sendRedirect("AdminProduct");
            }

        } catch (NumberFormatException e) {
            LOG.log(Level.WARNING, "Invalid number format", e);
            response.sendRedirect("AdminProduct?action=list&error=invalid");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "AdminProduct doPost error", e);
            request.setAttribute("error", "Có lỗi xảy ra khi xử lý");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
