<%-- 
    Document   : home
    Created on : Feb 21, 2026, 12:27:41 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Clothing Shop</title>
        <style>
            .product-grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
                gap: 20px;
                padding: 20px;
            }
            .product-card {
                border: 1px solid #ddd;
                padding: 15px;
                border-radius: 8px;
                text-align: center;
            }
            .product-img {
                width: 100%;
                height: 300px;
                object-fit: cover;
                border-radius: 4px;
            }
            .product-name {
                font-weight: bold;
                margin: 10px 0;
            }
            .product-price {
                color: #e44d26;
                font-size: 1.1em;
            }
            .variants {
                margin-top: 10px;
                font-size: 0.9em;
                color: #666;
            }
            .color-box {
                display: inline-block;
                width: 15px;
                height: 15px;
                border-radius: 50%;
                margin-right: 5px;
                border: 1px solid #ccc;
            }
            .sort-container {
                display: flex;
                align-items: center;
            }
            .sort-select {
                padding: 8px 12px;
                border-radius: 4px;
                border: 1px solid #ddd;
                background-color: #fff;
                font-size: 14px;
                cursor: pointer;
            }
            .sort-select:focus {
                outline: none;
                border-color: #007bff;
            }
        </style>
    </head>
    <body>
        <jsp:include page="componentHome/header.jsp"/>
        
        <div class="container">
            <div style="display: flex; justify-content: space-between; align-items: center; padding: 0 20px;">
                <h2>Sản phẩm mới nhất</h2>
                <div class="sort-container">
                    <select class="sort-select" id="sortProducts" onchange="sortData()">
                        <option value="" ${currentSort == null || currentSort == '' ? 'selected' : ''}>Mặc định</option>
                        <option value="name_asc" ${currentSort == 'name_asc' ? 'selected' : ''}>Tên từ A-Z</option>
                        <option value="name_desc" ${currentSort == 'name_desc' ? 'selected' : ''}>Tên từ Z-A</option>
                        <option value="price_asc" ${currentSort == 'price_asc' ? 'selected' : ''}>Giá thấp đến cao</option>
                        <option value="price_desc" ${currentSort == 'price_desc' ? 'selected' : ''}>Giá cao đến thấp</option>
                    </select>
                </div>
            </div>
            <div class="product-grid">
                <c:forEach items="${products}" var="p">
                    <div class="product-card">
                        <a href="${pageContext.request.contextPath}/product-detail?id=${p.productId}" style="text-decoration:none; color:inherit;">
                            <img src="${p.image}" alt="${p.productName}" class="product-img">
                            <div class="product-name">${p.productName}</div>
                        </a>
                        <div class="product-price">
                            <fmt:formatNumber value="${p.price}" type="number" maxFractionDigits="0"/> VND
                        </div>
                        <div class="variants">
                            <div>Màu: 
                                <c:forEach items="${p.variants}" var="v" varStatus="vs">
                                    <span class="color-box" style="background-color: ${v.color}" title="${v.color}"></span>
                                    <c:if test="${!vs.last}">, </c:if>
                                </c:forEach>
                            </div>
                            <div>Size: 
                                <c:forEach items="${p.variants}" var="v" varStatus="vs">
                                    ${v.size}<c:if test="${!vs.last}">, </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div style="margin-top: 15px; display: flex; gap: 10px; justify-content: center;">
                            <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                                <input type="hidden" name="id" value="${p.productId}">
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="quantity" value="1">
                                <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                                <input type="hidden" name="id" value="${p.productId}">
                                <input type="hidden" name="action" value="buyNow">
                                <input type="hidden" name="quantity" value="1">
                                <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        
        <jsp:include page="componentHome/footer.jsp"/>
        
        <script>
            function sortData() {
                const sortBy = document.getElementById('sortProducts').value;
                const url = new URL(window.location.href);
                if (sortBy) {
                    url.searchParams.set('sort', sortBy);
                } else {
                    url.searchParams.delete('sort');
                }
                window.location.href = url.href;
            }
        </script>
    </body>
</html>
