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
        <ul>
    <li>Tổng Đơn hàng : ${totalOrders}</li>
    </ul>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total</th>
        <th>Address</th>
    </tr>

    <c:forEach items="${eachOrder}" var="o">
    <tr>
        <td>${o.orderId}</td>
        <td>${o.customerId}</td>
        <td>${o.orderDate}</td>
        <td>${o.totalAmount}</td>
        
        <td>${o.address}</td>
    </tr>
</c:forEach>
</table>
    
    <ul>
    <li>Tổng Đơn hàng đã hoàn thành : ${totalOrdersDelivered}</li>
</ul>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total</th>
        <th>status</th>
        <th>Address</th>
    </tr>

    <c:forEach items="${eachOrderDelivered}" var="od">
    <tr>
        <td>${od.orderId}</td>
        <td>${od.customerId}</td>
        <td>${od.orderDate}</td>
        <td>${od.totalAmount}</td>
        <td>${od.orderStatus.name()}</td>
        <td>${od.address}</td>
    </tr>
</c:forEach>
    </table>

<ul>
    <li>Tổng Đơn hàng đã Bị huỷ : ${totalOrdersCancelled}</li>
</ul>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total</th>
        <th>status</th>
        <th>Address</th>
    </tr>

    <c:forEach items="${eachOrderCancelled}" var="od">
    <tr>
        <td>${od.orderId}</td>
        <td>${od.customerId}</td>
        <td>${od.orderDate}</td>
        <td>${od.totalAmount}</td>
        <td>${od.orderStatus.name()}</td>
        <td>${od.address}</td>
    </tr>
</c:forEach>
    </table>

<ul>
    <li>Tổng Đơn hàng đã xác nhận : ${totalOrdersConfirm}</li>
</ul>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total</th>
        <th>status</th>
        <th>Address</th>
    </tr>

    <c:forEach items="${eachOrderConfirm}" var="od">
    <tr>
        <td>${od.orderId}</td>
        <td>${od.customerId}</td>
        <td>${od.orderDate}</td>
        <td>${od.totalAmount}</td>
        <td>${od.orderStatus.name()}</td>
        <td>${od.address}</td>
    </tr>
</c:forEach>
    </table>

<ul>
    <li>Tổng Đơn hàng đang chờ để xác nhận : ${totalOrdersPending}</li>
</ul>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total</th>
        <th>status</th>
        <th>Address</th>
    </tr>

    <c:forEach items="${eachOrderPending}" var="od">
    <tr>
        <td>${od.orderId}</td>
        <td>${od.customerId}</td>
        <td>${od.orderDate}</td>
        <td>${od.totalAmount}</td>
        <td>${od.orderStatus.name()}</td>
        <td>${od.address}</td>
    </tr>
</c:forEach>
    </table>

<ul>
    <li>Tổng Đơn hàng đã giao : ${totalOrdersShipped}</li>
</ul>
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Date</th>
        <th>Total</th>
        <th>status</th>
        <th>Address</th>
    </tr>

    <c:forEach items="${eachOrderShipped}" var="od">
    <tr>
        <td>${od.orderId}</td>
        <td>${od.customerId}</td>
        <td>${od.orderDate}</td>
        <td>${od.totalAmount}</td>
        <td>${od.orderStatus.name()}</td>
        <td>${od.address}</td>
    </tr>
</c:forEach>
    </table>
    </body>
</html>
