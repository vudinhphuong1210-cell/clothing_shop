<%-- 
    Document   : AdminDashboard
    Created on : Feb 11, 2026, 3:45:22 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
    </head>
    <body>
        <h1>📊 Admin Dashboard</h1>
        
        <form action="AdminDashboard" method="get">
    <label>Status:</label>
    <select name="status">
        <option value="Admin-dashboard">-- All --</option>
        <option value="Pending">Pending</option>
        <option value="Confirmed">Confirmed</option>
        <option value="Shipped">Shipped</option>
        <option value="Delivered">Delivered</option>
        <option value="Cancelled">Cancelled</option>
    </select>
    <button type="submit">Search</button>
</form>
        
        <ul>
    <li>Tổng Đơn hàng : ${totalOrders}</li>
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

    <c:forEach items="${eachOrder}" var="o">
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
    
    
    </body>
</html>
