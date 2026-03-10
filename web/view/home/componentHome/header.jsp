<%-- 
    Document   : header
    Created on : Feb 28, 2026, 8:36:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clothes Shop</title>
    </head>
    <body>
        <div>
            <h2>Clothes shop</h2>
        </div>

        <nav>
            <a href="${pageContext.request.contextPath}/category?id=1">NỮ</a>
            <a href="${pageContext.request.contextPath}/category?id=2">NAM</a>
            <a href="${pageContext.request.contextPath}/category?id=3">TRẺ EM</a>
            <a href="${pageContext.request.contextPath}/category?id=4">TRẺ SƠ SINH</a>
        </nav>

        <section>
            <button onclick="location.href='${pageContext.request.contextPath}/home'">🏠</button>
            <form action="${pageContext.request.contextPath}/search" method="get" style="display: inline-flex; align-items: center;">
                <input type="text" name="keyword" placeholder="Tìm kiếm sản phẩm..." required>
                <button type="submit">🔍</button>
            </form>
            
            <a href="${pageContext.request.contextPath}/profile" style="text-decoration: none; font-size: 1.5rem; margin: 0 10px;">👤</a>

            <a href="${pageContext.request.contextPath}/cart" style="text-decoration: none; font-size: 1.5rem; margin: 0 10px; position: relative;">
                🛒
                <c:if test="${not empty sessionScope.cartSize && sessionScope.cartSize > 0}">
                    <span style="background-color: red; color: white; border-radius: 50%; padding: 2px 6px; font-size: 0.8rem; position: absolute; top: -5px; right: -10px;">${sessionScope.cartSize}</span>
                </c:if>
            </a>
        </section>
    </body>
</html>
