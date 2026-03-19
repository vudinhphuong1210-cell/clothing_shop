<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Order Dashboard</title>
        <style>
            table {
                width: 90%;
                margin: auto;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: center;
            }
            th {
                background: lightgray;
            }
        </style>
    </head>
    <body>

        <h2 style="text-align:center">Order Management</h2>

        <table>
            <tr>
                <th>OrderId</th>
                <th>CustomerId</th>
                <th>Date</th>
                <th>Total</th>
                <th>Status</th>
                <th>Address</th>
                <th>Update</th>
            </tr>

            <c:forEach var="o" items="${orderList}">
                <tr>
                    <td>${o.orderId}</td>
                    <td>${o.customerId}</td>
                    <td>${o.orderDate}</td>
                    <td>${o.totalAmount}</td>

                    <td>
                        <form action="updateOrderStatus" method="post">
                            <input type="hidden" name="orderId" value="${o.orderId}"/>

                            <select name="status">
                                <option value="PENDING" ${o.orderStatus.name() == 'PENDING' ? 'selected' : ''}>Pending</option>
                                <option value="CONFIRMED" ${o.orderStatus.name() == 'CONFIRMED' ? 'selected' : ''}>Confirmed</option>
                                <option value="SHIPPED" ${o.orderStatus.name() == 'SHIPPED' ? 'selected' : ''}>Shipped</option>
                                <option value="DELIVERED" ${o.orderStatus.name() == 'DELIVERED' ? 'selected' : ''}>Delivered</option>
                                <option value="CANCELLED" ${o.orderStatus.name() == 'CANCELLED' ? 'selected' : ''}>Cancelled</option>
                            </select>
                    </td>

                    <td>${o.address}</td>

                    <td>
                        <button type="submit">Update</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>

    </body>
</html>