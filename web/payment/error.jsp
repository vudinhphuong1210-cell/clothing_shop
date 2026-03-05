<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lỗi - Đơn hàng không tồn tại</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f8f9fa; padding: 50px; text-align: center; }
        .card { background: white; padding: 40px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        .error-title { color: #dc3545; font-size: 24px; font-weight: bold; margin-bottom: 15px;}
        .error-desc { color: #555; margin-bottom: 25px; line-height: 1.5; }
        .btn { display: inline-block; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; }
        .btn:hover { background: #0056b3; }
    </style>
</head>
<body>

<div class="card">
    <div class="error-title"> Đã có lỗi xảy ra!</div>
    
    <div class="error-desc">
        ${not empty errorMessage ? errorMessage : 'Không thể tìm thấy đơn hàng này trong hệ thống. Có thể đơn hàng đã bị xóa hoặc đường dẫn không chính xác.'}
    </div>
    
    <a href="${pageContext.request.contextPath}/home" class="btn">Quay Về Trang Chủ</a>
</div>

</body>
</html>