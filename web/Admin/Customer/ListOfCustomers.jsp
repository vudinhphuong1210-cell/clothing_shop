<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer List</title>
    </head>
    <body>
        <jsp:include page="/Admin/AdminHome.jsp"/>
        <div class="main-content">

            <form action="AdminCustomerControl" method="get">
                <label>Status:</label>
                <select name="status">
                    <%-- ✅ SỬA: value="" thay vì "AdminCustomerControl"
                         Khi value rỗng → servlet không parse enum → hiện tất cả --%>
                    <option value="">-- All --</option>
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                    <option value="Banned">Banned</option>
                </select>
                <button type="submit">Search</button>
            </form>

            <ul>
                <li>Tổng Khách Hàng : ${totalCustomers}</li>
            </ul>

            <table border="1">
                <tr>
                    <th>Customer ID</th>
                    <th>Full Name</th>
                    <th>User Name</th>
                    <th>Phone Number</th>
                    <th>Email</th>
                    <th>Gender</th>
                    <th>Status</th>
                    <th>Address</th>
                    <th>Point</th>
                    <th>Ban</th>
                </tr>

                <c:forEach items="${customers}" var="c">
                    <tr>
                        <td>${c.customerId}</td>
                        <td>${c.fullName}</td>
                        <td>${c.account.userName}</td>
                        <td>${c.phone}</td>
                        <td>${c.email}</td>
                        <td>${c.gender}</td>
                        <td>${c.account.status}</td>
                        <td>${c.address}</td>
                        <td>${c.point}</td>
                        <td>
                            <form action="AdminCustomerControl" method="POST">
                                <input type="hidden" name="accountId" value="${c.account.accountId}">
                                <c:choose>
                                    <c:when test="${c.account.status.name() eq 'ACTIVE'}">
                                        <input type="hidden" name="action" value="ban">
                                        <button type="submit">Ban</button>
                                    </c:when>
                                    <c:when test="${c.account.status.name() eq 'BANNED'}">
                                        <input type="hidden" name="action" value="unban">
                                        <button type="submit">Unban</button>
                                    </c:when>
                                </c:choose>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
