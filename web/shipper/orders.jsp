<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shipper - Orders</title>
        <style>
            table {
                width: 80%;
                border-collapse: collapse;
                margin: 20px auto;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #f4f4f4;
            }
            .success {
                color: green;
                text-align: center;
            }
            .error {
                color: red;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h2 style="text-align:center;">Danh sách đơn cần giao</h2>

        <c:if test="${param.msg == 'success'}">
            <p class="success">Xác nhận thu tiền thành công</p>
        </c:if>

        <c:if test="${param.msg == 'failed'}">
            <p class="error">Xác nhận thất bại</p>
        </c:if>

        <table>
            <tr>
                <th>Order ID</th>
                <th>Customer ID</th>
                <th>Order Date</th>
                <th>Total Amount</th>
                <th>Status</th>
                <th>Address</th>
                <th>Action</th>
            </tr>

            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.customerId}</td>
                    <td>
                        ${order.orderDate}
                    </td>  
                    <td>${order.totalAmount}</td>
                    <td>${order.orderStatus}</td>
                    <td>${order.address}</td>

                    <td>
                        <c:if test="${order.orderStatus.name() == 'SHIPPED'}">
                            <form action="${pageContext.request.contextPath}/shipper/orders" method="post">
                                <input type="hidden" name="orderId" value="${order.orderId}">
                                <button type="submit">Đã thu tiền</button>
                            </form>
                        </c:if>

                        <c:if test="${order.orderStatus.name() != 'SHIPPED'}">

                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
