
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Inactive</title>
    </head>
    <body>
        <h1>📊 Customer Inactive</h1>
  
        <ul>
    <li>Tổng khách hàng hoạt động : ${totalCustomerInactive}</li>
    </ul>
    <table border="1">
    <tr>
        <th>Customer ID</th>
        <th>Full Name</th>
        <th>User Name</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Status</th>
        <th>Address</th>
        <th>Point</th>
    </tr>

    <c:forEach items="${customerInactive}" var="c">
    <tr>
            <td>${c.customerId}</td>
            <td>${c.fullName}</td>
            <td>${c.account.userName}</td>
            <td>${c.phone}</td>
            <td>${c.email}</td>
            <td>${c.gender}</td>
            <td>${c.account.status}</td>
            <td>${c.address}</td>
            <td>${c.point}</td>
        </tr>
</c:forEach>
</table>
    <a href="AdminCustomerControl">quay về trang quản lí khách hàng cho admin</a>
    
    </body>
</html>
