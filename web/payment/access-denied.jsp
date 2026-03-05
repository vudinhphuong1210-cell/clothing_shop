<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied - Từ chối truy cập</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .card {
                background: #ffffff;
                padding: 40px 30px;
                border-radius: 8px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                text-align: center;
                max-width: 450px;
                width: 90%;
            }

            .icon {
                font-size: 60px;
                margin-bottom: 10px;
            }

            h2 {
                color: #dc3545;
                margin-top: 0;
                font-size: 26px;
            }

            p {
                color: #6c757d;
                margin-bottom: 30px;
                font-size: 16px;
                line-height: 1.5;
            }

            .btn {
                display: inline-block;
                padding: 10px 25px;
                background-color: #007bff;
                color: #ffffff;
                text-decoration: none;
                border-radius: 5px;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }

            .btn:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>

        <div class="card">
            <div class="icon">🚫</div>
            <h2>Access Denied</h2>
            <p>Rất tiếc, bạn không có quyền truy cập vào khu vực này hoặc thao tác này không được phép đối với tài khoản của bạn.</p>
            <a href="${pageContext.request.contextPath}/home" class="btn">
                Quay về trang chủ
            </a>
        </div>

    </body>
</html>