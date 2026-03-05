<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đơn hàng không hợp lệ</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f8f9fa; padding: 50px; text-align: center; }
        .card { background: white; padding: 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        .warning-title { color: #ffc107; font-size: 24px; font-weight: bold; margin-bottom: 15px; }
        .warning-desc { color: #555; margin-bottom: 25px; line-height: 1.5; }
        .btn { display: inline-block; padding: 10px 20px; background: #6c757d; color: white; text-decoration: none; border-radius: 5px; margin: 5px; }
        .btn:hover { background: #5a6268; }
        .btn-primary { background: #007bff; }
        .btn-primary:hover { background: #0056b3; }
    </style>
</head>
<body>

<div class="card">
    <div class="warning-title">Trạng thái không hợp lệ</div>
    
    <div class="warning-desc">
        Đơn hàng này <strong>không thể tiến hành thanh toán</strong> vào lúc này.<br><br>
        Nguyên nhân có thể là do đơn hàng đã được thanh toán trước đó, đã được xác nhận bởi cửa hàng, hoặc đã bị hủy.
    </div>
    
    <div>
        <a href="${pageContext.request.contextPath}/customer/my-orders" class="btn btn-primary">Xem Lịch Sử Đơn Hàng</a>
        <a href="${pageContext.request.contextPath}/home" class="btn">Về Trang Chủ</a>
    </div>
</div>

</body>
</html>