<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tồn kho sản phẩm</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background:#f4f6f9; }
        .container { padding:24px; }
        h2 { margin-bottom: 6px; }
        .subtitle { color:#777; margin-bottom:20px; }

        table { width:100%; border-collapse:collapse; background:#fff; }
        th, td { padding:12px 14px; border-bottom:1px solid #eee; }
        th { background:#1a1a2e; color:#fff; font-size:13px; text-align:left; }
        tr:hover { background:#fafafa; }

        .stock-ok  { color:#059669; font-weight:600; }
        .stock-low { color:#f59e0b; font-weight:600; }
        .stock-out { color:#ef4444; font-weight:600; }

        .badge-low { background:#fef3c7; color:#92400e; padding:2px 8px;
                     border-radius:12px; font-size:11px; margin-left:6px; }
        .badge-out { background:#fee2e2; color:#991b1b; padding:2px 8px;
                     border-radius:12px; font-size:11px; margin-left:6px; }

        .stock-form { display:flex; gap:6px; }
        .stock-form input { width:60px; padding:4px 6px; }
        .btn-add { background:#4f46e5; color:#fff; border:none;
                   padding:5px 10px; border-radius:6px; cursor:pointer; }
    </style>
</head>
<body>

<jsp:include page="/Admin/AdminHome.jsp"/>
<div class="main-content">
<div class="container">
    <a href="AdminProduct?action=list">← Quay về danh sách sản phẩm</a>

    <h2>📦 Quản lý tồn kho</h2>
    <p class="subtitle">
        Tồn kho theo từng biến thể (size / color)
    </p>

    <table>
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Size</th>
                <th>Color</th>
                <th>Tồn kho</th>
                <th>Đã bán</th>
                <th>Cập nhật</th>
                <th>Nhập thêm</th>
            </tr>
        </thead>
        <tbody>

        <c:forEach items="${inventory}" var="ps">
            <tr>
                <td>#${ps.productId}</td>

                <td>${empty ps.size ? '—' : ps.size}</td>
                <td>${empty ps.color ? '—' : ps.color}</td>

                <td>
                    <c:choose>
                        <c:when test="${ps.totalInStock == 0}">
                            <span class="stock-out">
                                0 <span class="badge-out">Hết</span>
                            </span>
                        </c:when>
                        <c:when test="${ps.totalInStock < 5}">
                            <span class="stock-low">
                                ${ps.totalInStock}
                                <span class="badge-low">Thấp</span>
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span class="stock-ok">${ps.totalInStock}</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>${ps.totalSold}</td>

                <td>
                    <fmt:formatDate value="${ps.updatedAtAsDate}"
                pattern="dd/MM/yyyy HH:mm"/>
                </td>

                <td>
                    <form action="AdminProduct" method="post" class="stock-form">
                        <input type="hidden" name="action" value="addStock">
                        <input type="hidden" name="productStatsId"
                               value="${ps.productStatsId}">
                        <input type="number" name="quantity"
                               min="1" value="1">
                        <button type="submit" class="btn-add">+ Nhập</button>
                    </form>
                </td>
            </tr>
        </c:forEach>

        <c:if test="${empty inventory}">
            <tr>
                <td colspan="7" style="text-align:center;color:#999;padding:30px">
                    Chưa có dữ liệu tồn kho
                </td>
            </tr>
        </c:if>

        </tbody>
    </table>
</div>
</div>
</body>
</html>