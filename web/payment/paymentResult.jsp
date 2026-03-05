<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.lang.Boolean" %>

<%
    Boolean result = (Boolean) request.getAttribute("transResult");
    String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Kết quả thanh toán</title>
</head>
<body>

<h2>Kết quả thanh toán</h2>

<% if (result != null && result) { %>
    <h3 style="color: green;">Thanh toán thành công!</h3>
<% } else { %>
    <h3 style="color: red;">Thanh toán thất bại!</h3>
<% } %>

<p><%= message != null ? message : "" %></p>

<a href="<%=request.getContextPath()%>/home">Quay về trang chủ</a>

</body>
</html>