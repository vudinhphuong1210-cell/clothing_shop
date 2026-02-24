<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa thông tin Nhân viên</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', sans-serif; background: #f4f6f9; color: #333; }
        .container { padding: 24px; max-width: 600px; }
        h2 { font-size: 22px; font-weight: 700; margin-bottom: 20px; color: #1a1a2e; }
        .back-link { display: inline-flex; gap: 6px; color: #4f46e5; font-size: 14px;
                     text-decoration: none; margin-bottom: 20px; }

        .card { background: #fff; border-radius: 12px;
                box-shadow: 0 1px 4px rgba(0,0,0,.08); padding: 28px; margin-bottom: 20px; }
        .card-title { font-size: 15px; font-weight: 700; margin-bottom: 20px;
                      padding-bottom: 10px; border-bottom: 2px solid #f0f0f0; color: #1a1a2e; }

        .form-group { display: flex; flex-direction: column; gap: 6px; margin-bottom: 16px; }
        label { font-size: 13px; font-weight: 600; color: #555; }
        input, select {
            padding: 10px 12px; border: 1px solid #ddd; border-radius: 8px;
            font-size: 14px; font-family: inherit; }
        input:focus, select:focus {
            outline: none; border-color: #4f46e5;
            box-shadow: 0 0 0 3px rgba(79,70,229,.1); }
        input[readonly] { background: #f9fafb; color: #888; cursor: not-allowed; }

        .readonly-note { font-size: 12px; color: #aaa; margin-top: 3px; }

        .btn-row { display: flex; gap: 12px; margin-top: 8px; }
        .btn { padding: 10px 24px; border-radius: 8px; border: none;
               font-size: 14px; font-weight: 600; cursor: pointer;
               text-decoration: none; display: inline-block; }
        .btn-primary   { background: #4f46e5; color: #fff; }
        .btn-primary:hover { background: #4338ca; }
        .btn-secondary { background: #f3f4f6; color: #333; }
    </style>
</head>
<body>
<jsp:include page="/Admin/AdminHome.jsp"/>
<div class="main-content">
<div class="container">
    <a href="AdminEmployeeControl" class="back-link">← Quay về danh sách</a>
    <h2>✏️ Sửa thông tin Nhân viên #${employee.employeeId}</h2>

    <div class="card">
        <div class="card-title">🔐 Thông tin tài khoản</div>

        <div class="form-group">
            <label>Tên đăng nhập</label>
            <input type="text" value="${employee.account.userName}" readonly/>
            <span class="readonly-note">Không thể thay đổi tên đăng nhập</span>
        </div>
    </div>

    <div class="card">
        <div class="card-title">👤 Thông tin nhân viên</div>

        <form action="AdminEmployeeControl" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="employeeId" value="${employee.employeeId}">

            <div class="form-group">
                <label>Họ và tên <span style="color:red">*</span></label>
                <input type="text" name="employeeName" required
                       value="${employee.employeeName}" maxlength="255"/>
            </div>

            <div class="form-group">
                <label>Số điện thoại</label>
                <input type="tel" name="phone"
                       value="${employee.phone}" maxlength="20"/>
            </div>

            <div class="form-group">
                <label>Chức vụ</label>
                <select name="position">
                    <option value="">-- Chọn chức vụ --</option>
                    <option value="office staff"
                        ${employee.position eq 'office staff' ? 'selected' : ''}>
                        Office Staff
                    </option>
                    <option value="shipper"
                        ${employee.position eq 'shipper' ? 'selected' : ''}>
                        Shipper
                    </option>
                    
                </select>
            </div>

            <div class="btn-row">
                <button type="submit" class="btn btn-primary">💾 Lưu thay đổi</button>
                <a href="AdminEmployeeControl" class="btn btn-secondary">Huỷ</a>
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>
