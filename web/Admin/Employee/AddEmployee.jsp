<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tạo tài khoản Nhân viên</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', sans-serif; background: #f4f6f9; color: #333; }
        .container { padding: 24px; max-width: 600px; }
        h2 { font-size: 22px; font-weight: 700; margin-bottom: 20px; color: #1a1a2e; }
        .back-link { display: inline-flex; gap: 6px; color: #4f46e5; font-size: 14px;
                     text-decoration: none; margin-bottom: 20px; }

        .card { background: #fff; border-radius: 12px;
                box-shadow: 0 1px 4px rgba(0,0,0,.08); padding: 28px; }
        .card-title { font-size: 15px; font-weight: 700; margin-bottom: 20px;
                      padding-bottom: 10px; border-bottom: 2px solid #f0f0f0;
                      color: #1a1a2e; }

        .form-group { display: flex; flex-direction: column; gap: 6px; margin-bottom: 16px; }
        label { font-size: 13px; font-weight: 600; color: #555; }
        input, select {
            padding: 10px 12px; border: 1px solid #ddd; border-radius: 8px;
            font-size: 14px; font-family: inherit; transition: border .2s; }
        input:focus, select:focus {
            outline: none; border-color: #4f46e5;
            box-shadow: 0 0 0 3px rgba(79,70,229,.1); }
        input.error { border-color: #ef4444; }

        .divider { border: none; border-top: 2px dashed #e5e7eb; margin: 20px 0; }
        .section-label { font-size: 12px; font-weight: 700; text-transform: uppercase;
                         letter-spacing: .05em; color: #888; margin-bottom: 12px; }

        .alert-error { background: #fee2e2; color: #991b1b; padding: 12px 16px;
                       border-radius: 8px; margin-bottom: 16px; font-size: 14px; }

        .btn-row { display: flex; gap: 12px; margin-top: 8px; }
        .btn { padding: 10px 24px; border-radius: 8px; border: none;
               font-size: 14px; font-weight: 600; cursor: pointer;
               text-decoration: none; display: inline-block; }
        .btn-primary   { background: #4f46e5; color: #fff; }
        .btn-primary:hover { background: #4338ca; }
        .btn-secondary { background: #f3f4f6; color: #333; }

        .hint { font-size: 12px; color: #aaa; margin-top: 3px; }

        /* Password toggle */
        .pw-wrap { position: relative; }
        .pw-wrap input { padding-right: 44px; width: 100%; }
        .pw-toggle { position: absolute; right: 12px; top: 50%;
                     transform: translateY(-50%); background: none;
                     border: none; cursor: pointer; font-size: 18px; padding: 0; }
    </style>
</head>
<body>
<jsp:include page="/Admin/AdminHome.jsp"/>
<div class="main-content">
<div class="container">
    <a href="AdminEmployeeControl" class="back-link">← Quay về danh sách</a>
    <h2>➕ Tạo tài khoản Nhân viên</h2>

    <c:if test="${not empty error}">
        <div class="alert-error">❌ ${error}</div>
    </c:if>

    <div class="card">
        <form action="AdminEmployeeControl" method="post">
            <input type="hidden" name="action" value="add">

            <%-- Thông tin tài khoản --%>
            <div class="card-title">🔐 Thông tin tài khoản</div>

            <div class="form-group">
                <label>Tên đăng nhập <span style="color:red">*</span></label>
                <input type="text" name="userName" required
                       value="${formData['userName'] != null ? formData['userName'][0] : ''}"
                       placeholder="VD: nhanvien01" maxlength="100"
                       autocomplete="off"/>
                <span class="hint">Chỉ dùng chữ thường, số, không dấu cách</span>
            </div>

            <div class="form-group">
                <label>Mật khẩu <span style="color:red">*</span></label>
                <div class="pw-wrap">
                    <input type="password" name="password" id="pwInput" required
                           placeholder="Tối thiểu 6 ký tự" minlength="6" maxlength="255"
                           autocomplete="new-password"/>
                    <button type="button" class="pw-toggle" onclick="togglePw()">👁️</button>
                </div>
            </div>

            <hr class="divider">

            <%-- Thông tin nhân viên --%>
            <div class="card-title">👤 Thông tin nhân viên</div>

            <div class="form-group">
                <label>Họ và tên <span style="color:red">*</span></label>
                <input type="text" name="employeeName" required
                       value="${formData['employeeName'] != null ? formData['employeeName'][0] : ''}"
                       placeholder="VD: Nguyễn Văn A" maxlength="255"/>
            </div>

            <div class="form-group">
                <label>Số điện thoại</label>
                <input type="tel" name="phone"
                       value="${formData['phone'] != null ? formData['phone'][0] : ''}"
                       placeholder="VD: 0901234567" maxlength="20"/>
            </div>

            <div class="form-group">
                <label>Chức vụ</label>
                <select name="position">
                    <option value="">-- Chọn chức vụ --</option>
                    <option value="office staff">Office Staff</option>
                    <option value="shipper">Shipper</option>
                    
                </select>
            </div>

            <div class="btn-row">
                <button type="submit" class="btn btn-primary">💾 Tạo tài khoản</button>
                <a href="AdminEmployeeControl" class="btn btn-secondary">Huỷ</a>
            </div>
        </form>
    </div>
</div>

<script>
    function togglePw() {
        const input = document.getElementById('pwInput');
        input.type = input.type === 'password' ? 'text' : 'password';
    }
</script>
</div>
</body>
</html>
