
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied</title>
    </head>
    <body>
        <h2 style="color:red;">Access Denied</h2>
        <p>Bạn không có quyền truy cập trang này</p>

        <a href="<%= request.getContextPath() %>/home.jsp">
            Quay về trang chủ
        </a>
    </body>
</html>
