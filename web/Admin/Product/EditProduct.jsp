<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Sản phẩm</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', sans-serif; background: #f4f6f9; color: #333; }
        .container { padding: 24px; max-width: 900px; }
        h2 { font-size: 22px; font-weight: 700; margin-bottom: 20px; color: #1a1a2e; }
        .back-link { display: inline-flex; gap: 6px; color: #4f46e5; font-size: 14px;
                     text-decoration: none; margin-bottom: 20px; }

        .card { background: #fff; border-radius: 12px;
                box-shadow: 0 1px 4px rgba(0,0,0,.08); padding: 28px; margin-bottom: 20px; }
        .card-title { font-size: 15px; font-weight: 700; margin-bottom: 20px;
                      padding-bottom: 10px; border-bottom: 2px solid #f0f0f0; color: #1a1a2e; }

        .form-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }
        .form-group { display: flex; flex-direction: column; gap: 6px; }
        .form-group.full { grid-column: 1 / -1; }
        label { font-size: 13px; font-weight: 600; color: #555; }
        input, select, textarea {
            padding: 10px 12px; border: 1px solid #ddd; border-radius: 8px;
            font-size: 14px; font-family: inherit; }
        input:focus, select:focus, textarea:focus {
            outline: none; border-color: #4f46e5;
            box-shadow: 0 0 0 3px rgba(79,70,229,.1); }
        textarea { resize: vertical; min-height: 90px; }

        .btn-row { display: flex; gap: 12px; margin-top: 8px; }
        .btn { padding: 10px 24px; border-radius: 8px; border: none;
               font-size: 14px; font-weight: 600; cursor: pointer; text-decoration: none;
               display: inline-block; transition: opacity .15s; }
        .btn:hover { opacity: .85; }
        .btn-primary   { background: #4f46e5; color: #fff; }
        .btn-secondary { background: #f3f4f6; color: #333; }
        .btn-success   { background: #10b981; color: #fff; }
        .btn-danger    { background: #ef4444; color: #fff; }
        .btn-sm { padding: 6px 14px; font-size: 13px; }

        /* Variants table */
        table { width: 100%; border-collapse: collapse; }
        thead { background: #f8f9fa; }
        thead th { padding: 10px 14px; text-align: left; font-size: 13px;
                   font-weight: 600; color: #555; border-bottom: 2px solid #eee; }
        tbody td { padding: 10px 14px; font-size: 14px;
                   border-bottom: 1px solid #f0f0f0; vertical-align: middle; }
        tbody tr:hover { background: #fafafa; }

        .stock-ok  { color: #059669; font-weight: 700; }
        .stock-low { color: #f59e0b; font-weight: 700; }
        .stock-out { color: #ef4444; font-weight: 700; }
        .badge-low { background: #fef3c7; color: #92400e; padding: 2px 7px;
                     border-radius: 10px; font-size: 11px; font-weight: 600; }
        .badge-out { background: #fee2e2; color: #991b1b; padding: 2px 7px;
                     border-radius: 10px; font-size: 11px; font-weight: 600; }

        .alert-success { background: #d1fae5; color: #065f46; padding: 10px 14px;
                         border-radius: 8px; margin-bottom: 16px; font-size: 14px; }
        .alert-error   { background: #fee2e2; color: #991b1b; padding: 10px 14px;
                         border-radius: 8px; margin-bottom: 16px; font-size: 14px; }

        /* Modal overlay */
        .modal-overlay {
            display: none; position: fixed; inset: 0;
            background: rgba(0,0,0,.45); z-index: 1000;
            align-items: center; justify-content: center;
        }
        .modal-overlay.active { display: flex; }
        .modal {
            background: #fff; border-radius: 14px; padding: 28px;
            width: 420px; max-width: 95vw;
            box-shadow: 0 8px 32px rgba(0,0,0,.18);
            animation: slideIn .2s ease;
        }
        @keyframes slideIn {
            from { transform: translateY(-20px); opacity: 0; }
            to   { transform: translateY(0);     opacity: 1; }
        }
        .modal-title {
            font-size: 16px; font-weight: 700; margin-bottom: 18px;
            padding-bottom: 10px; border-bottom: 2px solid #f0f0f0; color: #1a1a2e;
        }
        .modal .form-group { margin-bottom: 14px; }
        .modal .btn-row { margin-top: 18px; }
    </style>
</head>
<body>
<jsp:include page="/Admin/AdminHome.jsp"/>
<div class="main-content">
<div class="container">
    <a href="AdminProduct?action=list" class="back-link">← Quay về danh sách</a>
    <h2>✏️ Sửa Sản phẩm #${product.productId}</h2>

    <c:if test="${param.msg eq 'updated'}">
        <div class="alert-success">✅ Đã cập nhật sản phẩm!</div>
    </c:if>
    <c:if test="${param.msg eq 'variantAdded'}">
        <div class="alert-success">✅ Đã thêm biến thể mới!</div>
    </c:if>
    <c:if test="${param.msg eq 'variantUpdated'}">
        <div class="alert-success">✅ Đã cập nhật biến thể!</div>
    </c:if>
    <c:if test="${param.error eq 'invalid'}">
        <div class="alert-error">❌ Dữ liệu không hợp lệ, vui lòng kiểm tra lại.</div>
    </c:if>

    <%-- ===== FORM SỬA THÔNG TIN CƠ BẢN ===== --%>
    <form action="AdminProduct" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="productId" value="${product.productId}">

        <div class="card">
            <div class="card-title">📝 Thông tin sản phẩm</div>
            <div class="form-grid">
                <div class="form-group full">
                    <label>Tên sản phẩm <span style="color:red">*</span></label>
                    <input type="text" name="productName" required
                           value="${product.productName}" maxlength="255"/>
                </div>

                <div class="form-group">
                    <label>Danh mục</label>
                    <select name="categoryId">
                        <option value="">-- Chọn danh mục --</option>
                        <c:forEach items="${categories}" var="cat">
                            <option value="${cat.categoryId}"
                                ${cat.categoryId == product.categoryId ? 'selected' : ''}>
                                ${cat.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Giá (VNĐ) <span style="color:red">*</span></label>
                    <input type="number" name="price" required min="1" step="any"
                           value="${product.price}" placeholder="VD: 250000"/>
                </div>

                <div class="form-group full">
                    <label>Mô tả</label>
                    <textarea name="description">${product.description}</textarea>
                </div>

                <div class="form-group full">
                    <label>URL Ảnh sản phẩm</label>
                    <input type="text" name="image" value="${product.image}"/>
                </div>
            </div>
        </div>

        <div class="btn-row">
            <button type="submit" class="btn btn-primary">💾 Lưu thay đổi</button>
            <a href="AdminProduct?action=list" class="btn btn-secondary">Huỷ</a>
        </div>
    </form>

    <%-- ===== BẢNG VARIANTS ===== --%>
    <div class="card" style="margin-top:24px">
        <div class="card-title">📦 Biến thể (Size / Color) hiện có</div>
        <table>
            <thead>
                <tr>
                    <th>Size</th>
                    <th>Màu sắc</th>
                    <th>Tồn kho</th>
                    <th>Đã bán</th>
                    <th>Cập nhật lần cuối</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${stats}" var="s">
                    <tr>
                        <td>${empty s.size  ? '—' : s.size}</td>
                        <td>${empty s.color ? '—' : s.color}</td>
                        <td>
                            <c:choose>
                                <c:when test="${s.totalInStock == 0}">
                                    <span class="stock-out">0 <span class="badge-out">Hết</span></span>
                                </c:when>
                                <c:when test="${s.totalInStock < 5}">
                                    <span class="stock-low">${s.totalInStock} <span class="badge-low">Thấp</span></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="stock-ok">${s.totalInStock}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${s.totalSold}</td>
                        <td>
                            <fmt:formatDate value="${s.updatedAtAsDate}" pattern="dd/MM/yyyy HH:mm"/>
                        </td>
                        <td>
                            <%-- Truyền data vào modal khi click --%>
                            <button type="button" class="btn btn-primary btn-sm"
                                onclick="openEditVariant(
                                    '${s.productStatsId}',
                                    '${s.size}',
                                    '${s.color}',
                                    '${s.totalInStock}'
                                )">✏️ Sửa</button>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty stats}">
                    <tr><td colspan="6" style="text-align:center;padding:20px;color:#aaa">
                        Chưa có biến thể nào
                    </td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <%-- ===== FORM THÊM VARIANT MỚI ===== --%>
    <div class="card">
        <div class="card-title">➕ Thêm biến thể mới</div>
        <form action="AdminProduct" method="post">
            <input type="hidden" name="action" value="addVariant">
            <input type="hidden" name="productId" value="${product.productId}">
            <div class="form-grid">
                <div class="form-group">
                    <label>Size</label>
                    <input type="text" name="size" placeholder="VD: XL" maxlength="20"/>
                </div>
                <div class="form-group">
                    <label>Màu sắc</label>
                    <input type="text" name="color" placeholder="VD: Đen" maxlength="50"/>
                </div>
                <div class="form-group">
                    <label>Tồn kho ban đầu <span style="color:red">*</span></label>
                    <input type="number" name="stock" required min="0" value="0"/>
                </div>
            </div>
            <div class="btn-row" style="margin-top:16px">
                <button type="submit" class="btn btn-success">➕ Thêm biến thể</button>
            </div>
        </form>
    </div>

</div>
</div>

<%-- ===== MODAL SỬA VARIANT ===== --%>
<div class="modal-overlay" id="editVariantModal">
    <div class="modal">
        <div class="modal-title">✏️ Sửa biến thể</div>
        <form action="AdminProduct" method="post">
            <input type="hidden" name="action" value="editVariant">
            <input type="hidden" name="productId" value="${product.productId}">
            <input type="hidden" name="productStatsId" id="modal_statsId">

            <div class="form-group">
                <label>Size</label>
                <input type="text" name="size" id="modal_size" maxlength="20"/>
            </div>
            <div class="form-group">
                <label>Màu sắc</label>
                <input type="text" name="color" id="modal_color" maxlength="50"/>
            </div>
            <div class="form-group">
                <label>Tồn kho <span style="color:red">*</span></label>
                <input type="number" name="stock" id="modal_stock" required min="0"/>
            </div>

            <div class="btn-row">
                <button type="submit" class="btn btn-primary">💾 Lưu</button>
                <button type="button" class="btn btn-secondary" onclick="closeModal()">Huỷ</button>
            </div>
        </form>
    </div>
</div>

<script>
    function openEditVariant(statsId, size, color, stock) {
        document.getElementById('modal_statsId').value = statsId;
        document.getElementById('modal_size').value   = size;
        document.getElementById('modal_color').value  = color;
        document.getElementById('modal_stock').value  = stock;
        document.getElementById('editVariantModal').classList.add('active');
    }

    function closeModal() {
        document.getElementById('editVariantModal').classList.remove('active');
    }

    // Click ra ngoài modal để đóng
    document.getElementById('editVariantModal').addEventListener('click', function(e) {
        if (e.target === this) closeModal();
    });
</script>

</body>
</html>
