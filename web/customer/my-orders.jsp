<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: center;
            }
            th {
                background-color: #f4f4f4;
            }
            .status-pending {
                color: orange;
            }
            .status-completed {
                color: green;
            }
            .status-cancelled {
                color: red;
            }
        </style>
    </head>
    <body>
        <h2>My Orders</h2>

        <c:if test="${param.error == 'true'}">
            <div style="
                 background-color:#ffe5e5;
                 color:#cc0000;
                 padding:10px;
                 border:1px solid #cc0000;
                 border-radius:5px;
                 margin-bottom:15px;
                 ">
                Unable to load order.
            </div>
        </c:if>

        <c:choose>
            <c:when test="${empty orders}">
                <p>You have no orders yet.</p>
            </c:when>

            <c:otherwise>
                <table>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Total Amount</th>
                        <th>Order Status</th>
                        <th>Payment Status</th>
                        <th>Action</th>
                    </tr>

                    <c:forEach var="o" items="${orders}">
                        <tr>
                            <td>${o.orderId}</td>
                            <td>${o.orderDate}</td>
                            <td>$${o.totalAmount}</td>

                            <td>
                                <span class="status-${o.orderStatus}">
                                    ${o.orderStatus}
                                </span>
                            </td>

                            <td>${o.paymentStatus}</td>

                            <td>
                                <a href="order-detail?id=${o.orderId}">
                                    View Detail
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

    </body>
</html>
