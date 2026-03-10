<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng của bạn</title>
        <style>
            .container { max-width: 1200px; margin: 0 auto; padding: 20px; min-height: 50vh;}
            h1 { text-align: center; margin-bottom: 30px; }
            table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
            th, td { padding: 15px; text-align: left; border-bottom: 1px solid #ddd; }
            th { background-color: #f8f9fa; }
            .item-img { width: 80px; height: auto; border-radius: 4px; }
            .cart-summary { text-align: right; font-size: 1.2em; margin-bottom: 20px; }
            .total-price { color: #e44d26; font-weight: bold; font-size: 1.5em; }
            .btn { padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; color: #fff;}
            .btn-update { background-color: #17a2b8; }
            .btn-remove { background-color: #dc3545; }
            .btn-checkout { background-color: #28a745; font-size: 1.2em; padding: 15px 30px; }
            .btn-continue { background-color: #6c757d; float: left; }
            .actions-col { display: flex; gap: 10px; }
        </style>
    </head>
    <body>
        <jsp:include page="/view/home/componentHome/header.jsp"/>
        
        <div class="container">
            <h1>Giỏ hàng của bạn</h1>

            <c:if test="${not empty sessionScope.cartError}">
                <div style="background-color: #f8d7da; color: #721c24; padding: 15px; border-radius: 4px; border: 1px solid #f5c6cb; margin-bottom: 20px;">
                    <strong>Lỗi:</strong> ${sessionScope.cartError}
                </div>
                <c:remove var="cartError" scope="session" />
            </c:if>

            <c:set var="order" value="${sessionScope.order}"/>
            
            <c:if test="${order == null || order.orderDetails.size() == 0}">
                <div style="text-align: center;">
                    <p>Giỏ hàng của bạn đang trống.</p>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-continue" style="float:none; display:inline-block; margin-top: 20px;">Tiếp tục mua sắm</a>
                </div>
            </c:if>

            <c:if test="${order != null && order.orderDetails.size() > 0}">
                <table>
                    <thead>
                        <tr>
                            <th>Sản phẩm</th>
                            <th>Màu sắc</th>
                            <th>Kích cỡ</th>
                            <th>Đơn giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${order.orderDetails}" var="detail">
                            <tr>
                                <td>
                                    <div style="display: flex; align-items: center; gap: 10px;">
                                        <img src="${pageContext.request.contextPath}/${detail.productImage}" class="item-img">
                                        <a href="${pageContext.request.contextPath}/product-detail?id=${detail.productId}" style="text-decoration:none; color:#000;">${detail.productName}</a>
                                    </div>
                                </td>
                                <td>${detail.color}</td>
                                <td>${detail.size}</td>
                                <td><fmt:formatNumber value="${detail.price}" type="number" maxFractionDigits="0"/> VND</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="display:inline;">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="statsId" value="${detail.productStatsId}">
                                        <input type="number" name="quantity" value="${detail.quantity}" min="1" style="width: 60px;">
                                        <button type="submit" class="btn btn-update">Cập nhật</button>
                                    </form>
                                </td>
                                <td><fmt:formatNumber value="${detail.subTotal}" type="number" maxFractionDigits="0"/> VND</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/cart?action=remove&statsId=${detail.productStatsId}" class="btn btn-remove">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="cart-summary">
                    Tổng tiền: <span class="total-price"><fmt:formatNumber value="${order.totalAmount}" type="number" maxFractionDigits="0"/> VND</span>
                </div>

                <div style="overflow: hidden;">
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-continue">Tiếp tục mua sắm</a>
                    <a href="${pageContext.request.contextPath}/payment" class="btn btn-checkout" style="float: right;">Thanh toán ngay</a>
                </div>
            </c:if>
        </div>

        <jsp:include page="/view/home/componentHome/footer.jsp"/>
    </body>
</html>
