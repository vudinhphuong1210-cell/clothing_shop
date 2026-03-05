<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 30px;
            }

            .container {
                max-width: 900px;
                margin: auto;
            }

            .order-info {
                background: #f8f9fa;
                padding: 15px;
                border-radius: 6px;
                margin-bottom: 20px;
            }

            .order-info p {
                margin: 6px 0;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: center;
            }

            th {
                background: #f2f2f2;
            }

            .total-row {
                font-weight: bold;
                background: #fafafa;
            }

            .btn-back {
                display: inline-block;
                margin-top: 20px;
                padding: 8px 14px;
                background: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 4px;
            }

            .btn-back:hover {
                background: #0056b3;
            }

            .status-PENDING {
                color: orange;
                font-weight: bold;
            }
            .status-COMPLETED {
                color: green;
                font-weight: bold;
            }
            .status-CANCELLED {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">

            <h2>Order Detail</h2>

            <c:if test="${empty order}">
                <p style="color:red;">Order not found.</p>
            </c:if>

            <c:if test="${not empty order}">


                <div class="order-info">
                    <p><strong>Order ID:</strong> ${order.orderId}</p>

                    <p>
                        <strong>Order Date:</strong>
                        <td>${o.orderDate}</td>
                    </p>

                    <p>
                        <strong>Status:</strong>
                        <span class="status-${order.orderStatus}">
                            ${order.orderStatus}
                        </span>
                    </p>

                    <p><strong>Payment Status:</strong> ${order.paymentStatus}</p>

                    <p>
                        <strong>Total Amount:</strong>
                        <fmt:formatNumber value="${order.totalAmount}" type="currency"/>
                    </p>
                </div>


                <h3>Products</h3>

                <table>
                    <tr>
                        <th>#</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Subtotal</th>
                    </tr>

                    <c:forEach var="d" items="${details}" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${d.productName}</td>
                            <td>${d.quantity}</td>

                            <td>
                                <fmt:formatNumber value="${d.price}" type="currency"/>
                            </td>

                            <td>
                                <fmt:formatNumber value="${d.price * d.quantity}" type="currency"/>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr class="total-row">
                        <td colspan="4">Grand Total</td>
                        <td>
                            <fmt:formatNumber value="${order.totalAmount}" type="currency"/>
                        </td>
                    </tr>
                </table>

                <a href="my-orders" class="btn-back">Back to My Orders</a>

            </c:if>

        </div>
    </body>
</html>
