<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Nhân viên</title>

    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', sans-serif; background: #f4f6f9; color: #333; }
        .container { padding: 24px; }
        h2 { font-size: 22px; font-weight: 700; margin-bottom: 20px; color: #1a1a2e; }

        .alert { padding: 12px 16px; border-radius: 8px; margin-bottom: 16px; font-size: 14px; }
        .alert-success { background: #d1fae5; color: #065f46; }
        .alert-error   { background: #fee2e2; color: #991b1b; }

        .stats { display: flex; gap: 16px; margin-bottom: 20px; }
        .stat-card {
            background: #fff;
            padding: 16px 24px;
            border-radius: 10px;
            box-shadow: 0 1px 4px rgba(0,0,0,.08);
            flex: 1;
        }
        .stat-card .num { font-size: 28px; font-weight: 700; color: #4f46e5; }
        .stat-card .lbl { font-size: 13px; color: #888; margin-top: 4px; }

        .toolbar { display: flex; justify-content: flex-end; margin-bottom: 16px; }
        .btn {
            padding: 9px 20px;
            border-radius: 8px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            display: inline-block;
        }
        .btn-primary  { background: #4f46e5; color: #fff; }
        .btn-primary:hover { background: #4338ca; }
        .btn-warning  { background: #f59e0b; color: #fff; }
        .btn-danger   { background: #ef4444; color: #fff; }
        .btn-success  { background: #10b981; color: #fff; }
        .btn-sm { padding: 5px 12px; font-size: 13px; }

        .table-wrap {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 1px 4px rgba(0,0,0,.08);
            overflow: hidden;
        }
        table { width: 100%; border-collapse: collapse; }
        thead { background: #1a1a2e; color: #fff; }
        thead th {
            padding: 13px 16px;
            text-align: left;
            font-size: 13px;
            font-weight: 600;
        }
        tbody tr { border-bottom: 1px solid #f0f0f0; }
        tbody tr:hover { background: #fafafa; }
        tbody td { padding: 12px 16px; font-size: 14px; vertical-align: middle; }

        .badge {
            padding: 4px 10px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 600;
        }
        .badge-active   { background: #d1fae5; color: #065f46; }
        .badge-inactive { background: #fee2e2; color: #991b1b; }

        .avatar {
            width: 36px;
            height: 36px;
            border-radius: 50%;
            background: #e0e7ff;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: 700;
            color: #4f46e5;
            font-size: 15px;
        }
        .actions { display: flex; gap: 6px; }
    </style>
</head>

<body>
<jsp:include page="/Admin/AdminHome.jsp"/>
<div class="main-content">
<div class="container">
    <h2>👨‍💼 Quản lý Nhân viên</h2>

    <c:if test="${param.msg eq 'added'}">
        <div class="alert alert-success">✅ Tạo tài khoản nhân viên thành công!</div>
    </c:if>
    <c:if test="${param.msg eq 'updated'}">
        <div class="alert alert-success">✅ Cập nhật thông tin thành công!</div>
    </c:if>
    <c:if test="${param.error eq 'notfound'}">
        <div class="alert alert-error">❌ Không tìm thấy nhân viên!</div>
    </c:if>

    <!-- Stats -->
    <div class="stats">
        <div class="stat-card">
            <div class="num">${totalEmployees}</div>
            <div class="lbl">Tổng nhân viên</div>
        </div>

        <div class="stat-card">
            <div class="num" style="color:#10b981">
                <c:set var="activeCount" value="0"/>
                <c:forEach items="${employees}" var="e">
                    <c:if test="${e.account.status.name() eq 'ACTIVE'}">
                        <c:set var="activeCount" value="${activeCount + 1}"/>
                    </c:if>
                </c:forEach>
                ${activeCount}
            </div>
            <div class="lbl">Đang hoạt động</div>
        </div>

        <div class="stat-card">
            <div class="num" style="color:#f59e0b">
                ${totalEmployees - activeCount}
            </div>
            <div class="lbl">Đã vô hiệu hoá</div>
        </div>
    </div>

    <!-- Toolbar -->
    <div class="toolbar">
        <a href="AdminEmployeeControl?action=add" class="btn btn-primary">
            ➕ Tạo tài khoản nhân viên
        </a>
    </div>

    <!-- Table -->
    <div class="table-wrap">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nhân viên</th>
                    <th>Tài khoản</th>
                    <th>Số điện thoại</th>
                    <th>Chức vụ</th>
                    <th>Trạng thái</th>
                    <th>Ngày tạo</th>
                    <th>Thao tác</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${employees}" var="e">
                    <tr>
                        <td>#${e.employeeId}</td>

                        <td>
                            <div style="display:flex;align-items:center;gap:10px">
                                <div class="avatar">
                                    ${fn:substring(e.employeeName, 0, 1)}
                                </div>
                                <strong>${e.employeeName}</strong>
                            </div>
                        </td>

                        <td style="color:#4f46e5;font-weight:600">
                            ${e.account.userName}
                        </td>

                        <td>${empty e.phone ? '—' : e.phone}</td>
                        <td>${empty e.position ? '—' : e.position}</td>

                        <td>
                            <c:choose>
                                <c:when test="${e.account.status.name() eq 'ACTIVE'}">
                                    <span class="badge badge-active">Active</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-inactive">Inactive</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <!-- FIX LỖI DATE: KHÔNG DÙNG fmt:formatDate -->
                        <td>${e.createdAtFormatted}</td>

                        <td>
                            <div class="actions">
                                <a href="AdminEmployeeControl?action=edit&id=${e.employeeId}"
                                   class="btn btn-warning btn-sm">✏️</a>

                                <form action="AdminEmployeeControl" method="post">
                                    <input type="hidden" name="action" value="toggleStatus">
                                    <input type="hidden" name="accountId" value="${e.account.accountId}">
                                    <input type="hidden" name="currentStatus" value="${e.account.status.name()}">

                                    <c:choose>
                                        <c:when test="${e.account.status.name() eq 'ACTIVE'}">
                                            <button type="submit"
                                                    class="btn btn-danger btn-sm"
                                                    onclick="return confirm('Vô hiệu hoá tài khoản này?')">
                                                🔴
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="submit"
                                                    class="btn btn-success btn-sm"
                                                    onclick="return confirm('Kích hoạt tài khoản này?')">
                                                🟢
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty employees}">
                    <tr>
                        <td colspan="8"
                            style="text-align:center;padding:30px;color:#aaa">
                            Chưa có nhân viên nào
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
            </div>
</body>
</html>