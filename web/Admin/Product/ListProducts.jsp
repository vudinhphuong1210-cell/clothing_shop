<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Quản lý Sản phẩm</title>
                <style>
                    * {
                        box-sizing: border-box;
                        margin: 0;
                        padding: 0;
                    }

                    body {
                        font-family: 'Segoe UI', sans-serif;
                        background: #f4f6f9;
                        color: #333;
                    }

                    .container {
                        padding: 24px;
                    }

                    h2 {
                        font-size: 22px;
                        font-weight: 700;
                        margin-bottom: 20px;
                        color: #1a1a2e;
                    }

                    .toolbar {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        background: #fff;
                        padding: 14px 20px;
                        border-radius: 10px;
                        box-shadow: 0 1px 4px rgba(0, 0, 0, .08);
                        margin-bottom: 20px;
                        flex-wrap: wrap;
                        gap: 10px;
                    }

                    .toolbar form {
                        display: flex;
                        gap: 10px;
                        align-items: center;
                    }

                    .toolbar select,
                    .toolbar button,
                    .btn {
                        padding: 8px 16px;
                        border-radius: 6px;
                        border: 1px solid #ddd;
                        font-size: 14px;
                        cursor: pointer;
                    }

                    .btn-primary {
                        background: #4f46e5;
                        color: #fff;
                        border-color: #4f46e5;
                        text-decoration: none;
                        display: inline-block;
                    }

                    .btn-primary:hover {
                        background: #4338ca;
                    }

                    .btn-sm {
                        padding: 5px 12px;
                        font-size: 13px;
                    }

                    .btn-warning {
                        background: #f59e0b;
                        color: #fff;
                        border: none;
                    }

                    .btn-danger {
                        background: #ef4444;
                        color: #fff;
                        border: none;
                    }

                    .btn-success {
                        background: #10b981;
                        color: #fff;
                        border: none;
                    }

                    .stats-bar {
                        display: flex;
                        gap: 16px;
                        margin-bottom: 20px;
                        flex-wrap: wrap;
                    }

                    .stat-card {
                        background: #fff;
                        padding: 16px 24px;
                        border-radius: 10px;
                        box-shadow: 0 1px 4px rgba(0, 0, 0, .08);
                        flex: 1;
                        min-width: 140px;
                    }

                    .stat-card .num {
                        font-size: 28px;
                        font-weight: 700;
                    }

                    .stat-card .lbl {
                        font-size: 13px;
                        color: #888;
                        margin-top: 4px;
                    }

                    .stat-card.active .num {
                        color: #10b981;
                    }

                    .stat-card.inactive .num {
                        color: #f59e0b;
                    }

                    .table-wrap {
                        background: #fff;
                        border-radius: 10px;
                        box-shadow: 0 1px 4px rgba(0, 0, 0, .08);
                        overflow: hidden;
                    }

                    table {
                        width: 100%;
                        border-collapse: collapse;
                    }

                    thead {
                        background: #1a1a2e;
                        color: #fff;
                    }

                    thead th {
                        padding: 13px 16px;
                        text-align: left;
                        font-size: 13px;
                        font-weight: 600;
                    }

                    tbody tr {
                        border-bottom: 1px solid #f0f0f0;
                    }

                    tbody tr:hover {
                        background: #fafafa;
                    }

                    tbody td {
                        padding: 12px 16px;
                        font-size: 14px;
                        vertical-align: middle;
                    }

                    .badge {
                        padding: 4px 10px;
                        border-radius: 20px;
                        font-size: 12px;
                        font-weight: 600;
                    }

                    .badge-active {
                        background: #d1fae5;
                        color: #065f46;
                    }

                    .badge-inactive {
                        background: #fee2e2;
                        color: #991b1b;
                    }

                    .prod-img {
                        width: 48px;
                        height: 48px;
                        object-fit: cover;
                        border-radius: 8px;
                        border: 1px solid #eee;
                    }

                    .no-img {
                        width: 48px;
                        height: 48px;
                        background: #f3f4f6;
                        border-radius: 8px;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        font-size: 20px;
                    }

                    .alert {
                        padding: 12px 16px;
                        border-radius: 8px;
                        margin-bottom: 16px;
                        font-size: 14px;
                    }

                    .alert-success {
                        background: #d1fae5;
                        color: #065f46;
                    }
                </style>
            </head>

            <body>

                <jsp:include page="/Admin/AdminHome.jsp" />
                <div class="main-content">
                    <div class="container">
                        <h2>📦 Quản lý Sản phẩm</h2>

                        <c:if test="${param.msg eq 'added'}">
                            <div class="alert alert-success">✅ Thêm sản phẩm thành công!</div>
                        </c:if>
                        <c:if test="${param.msg eq 'updated'}">
                            <div class="alert alert-success">✅ Cập nhật sản phẩm thành công!</div>
                        </c:if>

                        <div class="stats-bar">
                            <div class="stat-card">
                                <div class="num">${totalProducts}</div>
                                <div class="lbl">Tổng sản phẩm</div>
                            </div>
                            <div class="stat-card active">
                                <div class="num">
                                    <c:set var="activeCount" value="0" />
                                    <c:forEach items="${products}" var="p">
                                        <c:if test="${not empty p.status and p.status.name() eq 'ACTIVE'}">
                                            <c:set var="activeCount" value="${activeCount + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    ${activeCount}
                                </div>
                                <div class="lbl">Đang bán</div>
                            </div>
                            <div class="stat-card inactive">
                                <div class="num">${totalProducts - activeCount}</div>
                                <div class="lbl">Ngừng bán</div>
                            </div>
                        </div>

                        <div class="toolbar">
                            <form action="AdminProduct" method="get">
                                <input type="hidden" name="action" value="list">
                                <input type="text" name="search" value="${searchQuery}"
                                    placeholder="Tìm theo tên sản phẩm..." class="form-control" style="width:200px;">
                                <select name="status">
                                    <option value="">-- Tất cả --</option>
                                    <option value="active" ${selectedStatus eq 'active' ? 'selected' : '' }>Active
                                    </option>
                                    <option value="inactive" ${selectedStatus eq 'inactive' ? 'selected' : '' }>Inactive
                                    </option>
                                </select>
                                <button type="submit">Tìm & Lọc</button>
                            </form>
                            <div style="display:flex; gap:10px;">
                                <a href="AdminProduct?action=inventory" class="btn btn-primary btn-sm">📊 Xem tồn
                                    kho</a>
                                <a href="AdminProduct?action=add" class="btn btn-primary btn-sm">➕ Thêm sản phẩm</a>
                            </div>
                        </div>

                        <div class="table-wrap">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Ảnh</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Category</th>
                                        <th>Giá</th>
                                        <th>Trạng thái</th>
                                        <th>Ngày tạo</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${products}" var="p">
                                        <tr>
                                            <td>#${p.productId}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty p.image}">
                                                        <img src="${p.image}" alt="${p.productName}" class="prod-img" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="no-img">🖼️</div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <strong>${p.productName}</strong><br />
                                                <small style="color:#888">${p.description}</small>
                                            </td>
                                            <td>
                                                <c:if test="${not empty p.category}">
                                                    ${p.category.categoryName}
                                                </c:if>
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${p.price}" type="number"
                                                    maxFractionDigits="0" />đ
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when
                                                        test="${not empty p.status and p.status.name() eq 'ACTIVE'}">
                                                        <span class="badge badge-active">Active</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge badge-inactive">Inactive</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                ${p.createdAtFormatted}
                                            </td>
                                            <td>
                                                <a href="AdminProduct?action=edit&id=${p.productId}"
                                                    class="btn btn-warning btn-sm">✏️ Sửa</a>

                                                <form action="AdminProduct" method="post" style="display:inline">
                                                    <input type="hidden" name="action" value="toggleStatus">
                                                    <input type="hidden" name="productId" value="${p.productId}">
                                                    <c:choose>
                                                        <c:when
                                                            test="${not empty p.status and p.status.name() eq 'ACTIVE'}">
                                                            <button type="submit" class="btn btn-danger btn-sm">🔴
                                                                Tắt</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button type="submit" class="btn btn-success btn-sm">🟢
                                                                Bật</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    <c:if test="${empty products}">
                                        <tr>
                                            <td colspan="8" style="text-align:center;padding:30px;color:#aaa">
                                                Không có sản phẩm nào
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </body>

            </html>