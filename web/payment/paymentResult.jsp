<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Kết quả thanh toán</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f8f9fa;
                padding: 50px;
                text-align: center;
            }
            .card {
                background: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                max-width: 500px;
                margin: auto;
            }
            .success {
                color: #28a745;
            }
            .error {
                color: #dc3545;
            }
            .btn {
                display: inline-block;
                margin-top: 20px;
                padding: 10px 20px;
                background: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            }
            .btn:hover {
                background: #0056b3;
            }
        </style>
    </head>
    <body>

        <div class="card">
            <c:choose>

                <c:when test="${transResult == true}">
                    <h1 class="success">Đặt Hàng Thành Công!</h1>

                    <c:if test="${paymentMethod == 'COD'}">
                        <p>Cảm ơn bạn đã mua sắm. Đơn hàng <strong>#${orderId}</strong> của bạn sẽ được thanh toán bằng tiền mặt khi nhận hàng (COD)</p>
                    </c:if>

                    <c:if test="${paymentMethod == 'VNPAY'}">
                        <p>Giao dịch qua VNPAY thành công cho đơn hàng <strong>#${orderId}</strong></p>
                        <c:if test="${not empty message}">
                            <p><em>Ghi chú: ${message}</em></p>
                        </c:if>
                    </c:if>

                    <p>Chúng tôi sẽ sớm chuẩn bị và giao hàng cho bạn</p>
                </c:when>


                <c:otherwise>
                    <h1 class="error">Thanh Toán Thất Bại!</h1>

                    <c:if test="${not empty orderId}">
                        <p>Mã đơn hàng: <strong>#${orderId}</strong></p>
                    </c:if>

                    <p>${not empty message ? message : 'Đã có lỗi xảy ra hoặc bạn đã hủy giao dịch qua VNPAY.'}</p>
                    <p>Vui lòng thử đặt hàng lại hoặc chọn phương thức thanh toán khác</p>
                </c:otherwise>
            </c:choose>

            <a href="${pageContext.request.contextPath}/home" class="btn">Quay Về Trang Chủ</a>
        </div>

    </body>
</html>