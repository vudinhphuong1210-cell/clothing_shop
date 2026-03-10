<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm Sản phẩm</title>
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
                max-width: 800px;
            }
            h2 {
                font-size: 22px;
                font-weight: 700;
                margin-bottom: 20px;
                color: #1a1a2e;
            }
            .back-link {
                display: inline-flex;
                gap: 6px;
                color: #4f46e5;
                font-size: 14px;
                text-decoration: none;
                margin-bottom: 20px;
            }

            .card {
                background: #fff;
                border-radius: 12px;
                box-shadow: 0 1px 4px rgba(0,0,0,.08);
                padding: 28px;
                margin-bottom: 20px;
            }
            .card-title {
                font-size: 15px;
                font-weight: 700;
                margin-bottom: 20px;
                padding-bottom: 10px;
                border-bottom: 2px solid #f0f0f0;
                color: #1a1a2e;
            }

            .form-grid {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 16px;
            }
            .form-group {
                display: flex;
                flex-direction: column;
                gap: 6px;
            }
            .form-group.full {
                grid-column: 1 / -1;
            }
            label {
                font-size: 13px;
                font-weight: 600;
                color: #555;
            }
            input, select, textarea {
                padding: 10px 12px;
                border: 1px solid #ddd;
                border-radius: 8px;
                font-size: 14px;
                font-family: inherit;
                transition: border 0.2s;
            }
            input:focus, select:focus, textarea:focus {
                outline: none;
                border-color: #4f46e5;
                box-shadow: 0 0 0 3px rgba(79,70,229,.1);
            }
            textarea {
                resize: vertical;
                min-height: 90px;
            }

            .section-sep {
                margin: 8px 0 20px;
                border: none;
                border-top: 2px dashed #e5e7eb;
            }
            .optional-tag {
                font-size: 11px;
                color: #aaa;
                font-weight: 400;
                margin-left: 6px;
            }

            .btn-row {
                display: flex;
                gap: 12px;
                margin-top: 8px;
            }
            .btn {
                padding: 10px 24px;
                border-radius: 8px;
                border: none;
                font-size: 14px;
                font-weight: 600;
                cursor: pointer;
            }
            .btn-primary {
                background: #4f46e5;
                color: #fff;
            }
            .btn-primary:hover {
                background: #4338ca;
            }
            .btn-secondary {
                background: #f3f4f6;
                color: #333;
            }
            .btn-secondary:hover {
                background: #e5e7eb;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/Admin/AdminHome.jsp"/>
        <div class="main-content">
            <div class="container">
                <a href="AdminProduct?action=list" class="back-link">← Quay về danh sách</a>
                <h2>➕ Thêm Sản phẩm mới</h2>

                <form action="AdminProduct" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="add">

                    <%-- Thông tin cơ bản --%>
                    <div class="card">
                        <div class="card-title">📝 Thông tin cơ bản</div>
                        <div class="form-grid">
                            <div class="form-group full">
                                <label>Tên sản phẩm <span style="color:red">*</span></label>
                                <input type="text" name="productName" required
                                       placeholder="VD: Áo thun basic oversize" maxlength="255"/>
                            </div>

                            <div class="form-group">
                                <label>Danh mục <span style="color:red">*</span></label>
                                <select name="categoryId" required>
                                    <option value="">-- Chọn danh mục --</option>
                                    <c:forEach items="${categories}" var="cat">
                                        <option value="${cat.categoryId}">${cat.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Giá (VNĐ) <span style="color:red">*</span></label>
                                <input type="number" name="price"
                                       required min="1"
                                       step="any"
                                       placeholder="VD: 250000"/>
                            </div>

                            <div class="form-group full">
                                <label>Mô tả</label>
                                <textarea name="description" placeholder="Mô tả ngắn về sản phẩm..."></textarea>
                            </div>

                            <div class="form-group full">
                                <label>Ảnh sản phẩm</label>
                                <input type="file"
                                       name="image"
                                       accept="image/*"
                                       required>
                            </div>
                        </div>
                    </div>

                   
                    
                    <%-- Variant đầu tiên (tuỳ chọn) --%>
                    <div class="card">
                        <div class="card-title">
                            📦 Biến thể đầu tiên
                            <span class="optional-tag">(tuỳ chọn — có thể thêm sau)</span>
                        </div>
                        <hr class="section-sep">
                        <div class="form-grid">
                            <div class="form-group">
                                <label>Size <span class="optional-tag">(S/M/L/XL...)</span></label>
                                <input type="text" name="size" placeholder="VD: M" maxlength="20"/>
                            </div>

                            <div class="form-group">
                                <label>Màu sắc <span class="optional-tag">(tuỳ chọn)</span></label>
                                <input type="text" name="color" placeholder="VD: Trắng" maxlength="50"/>
                            </div>

                            <div class="form-group">
                                <label>Số lượng tồn kho ban đầu</label>
                                <input type="number" name="stock" min="0" value="0" placeholder="0"/>
                            </div>
                        </div>
                    </div>

                    <div class="btn-row">
                        <button type="submit" class="btn btn-primary">💾 Lưu sản phẩm</button>
                        <a href="AdminProduct?action=list" class="btn btn-secondary">Huỷ</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
