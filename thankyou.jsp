
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thank You</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body class="container text-center mt-5">
        <div class="card p-5 shadow">
        <h1 class="text-success">Đặt hàng thành công!</h1>
        <p class="lead">${param.msg}</p> 
        <p>Mã đơn hàng của bạn đã được lưu vào hệ thống.</p>
        <div class="mt-4">
            <a href="home.jsp" class="btn btn-primary">Tiếp tục mua sắm</a>
            <a href="my-orders" class="btn btn-outline-secondary">Xem đơn hàng của tôi</a>
        </div>
    </div>
    </body>
</html>
