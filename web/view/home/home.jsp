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
        </style>
    </head>
    <body>
        <jsp:include page="componentHome/header.jsp"/>
        
        <div class="container">
            <h2>Sản phẩm mới nhất</h2>
            <div class="product-grid">
                <c:forEach items="${products}" var="p">
                    <div class="product-card">
                        <img src="${p.image}" alt="${p.productName}" class="product-img">
                        <div class="product-name">${p.productName}</div>
                        <div class="product-price">
                            <fmt:formatNumber value="${p.price}" type="number" maxFractionDigits="0"/> VND
                        </div>
                        <div class="variants">
                            <div>Màu: 
                                <c:forEach items="${p.variants}" var="v" varStatus="vs">
                                    <span class="color-box" style="background-color: ${v.color}"></span>
                                    <c:if test="${!vs.last}">, </c:if>
                                </c:forEach>
                            </div>
                            <div>Size: 
                                <c:forEach items="${p.variants}" var="v" varStatus="vs">
                                    ${v.size}<c:if test="${!vs.last}">, </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        
        <jsp:include page="componentHome/footer.jsp"/>
    </body>
</html>
