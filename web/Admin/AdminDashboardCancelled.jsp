<%-- 
    Document   : AdminDashboardCancelled
    Created on : Feb 11, 2026, 8:02:21 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
    <li>Tổng Đơn hàng bị huỷ : ${totalOrdersCancelled}</li>
    </ul>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total</th>
        <th>Status</th>
        <th>Address</th>
    </tr>

    <c:forEach items="${eachOrderCancelled}" var="o">
    <tr>
        <td>${o.orderId}</td>
        <td>${o.customerId}</td>
        <td>${o.orderDate}</td>
        <td>${o.totalAmount}</td>
        <td>${o.orderStatus.name()}</td>
        <td>${o.address}</td>
    </tr>
</c:forEach>
</table>
    <a href="AdminDashboard">quay về trang dashboard cho admin</a>
    </body>
</html>
