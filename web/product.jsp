<%-- 
    Document   : product
    Created on : Jan 17, 2026, 12:48:47 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <h1>Product</h1>
    <a href="logout">Logout</a>
    <ul>
        <c:forEach var="cate" items="${listCate}">
            <li>
                <a href="category?categoryID=${cate.categoryID}">
                    ${cate.categoryName}
                </a>
            </li>
        </c:forEach>
    </ul>

    <table border="1">
        <thead>
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Sold</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${listProduct}">
                <tr>
                    <td>
                        <img src="images/${p.image}" width="100">
                    </td>
                    <td>${p.productName}</td>
                    <td>${p.price}</td>
                    <td>${p.totalSold}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</html>
