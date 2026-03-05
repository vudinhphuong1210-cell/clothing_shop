<%-- 
    Document   : header
    Created on : Feb 28, 2026, 8:36:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            <button>🛒</button>
        </section>
    </body>
</html>
