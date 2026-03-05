<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Details - Clothing Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/view/home/profile.css">
    </head>
    <jsp:include page="componentHome/header.jsp" />
    <body>
        <div class="container">
            <!-- Sidebar -->
            <div class="sidebar">
                <div class="avatar-container">
                    <!-- Placeholder avatar image based on screenshot -->
                    <img src="https://i.pinimg.com/736x/f6/05/9a/f6059a4c5a0b73b4007a8298715764d7.jpg" alt="Avatar" class="avatar">
                </div>
                <div class="nav-links">
                    <a href="${pageContext.request.contextPath}/profile" class="nav-item active">Account Details</a>
                    <a href="${pageContext.request.contextPath}/editprofile" class="nav-item">Edit Profile</a>
                </div>
            </div>

            <!-- Main Content -->
            <div class="content">
                <h2>Account Details</h2>
                
                <div class="info-group">
                    <label>Username</label>
                    <div class="info-value">${sessionScope.account.userName}</div>
                </div>

                <div class="info-group">
                    <label>Role</label>
                    <div class="info-value">${sessionScope.account.role}</div>
                </div>

                <div class="info-group">
                    <label>First Name</label>
                    <div class="info-value">${customer.fullName != null ? customer.fullName : "N/A"}</div>
                </div>

                <div class="info-group">
                    <label>Last Name</label>
                    <div class="info-value">N/A</div> <!-- Only fullName is in model -->
                </div>

                <div class="info-group">
                    <label>Email</label>
                    <div class="info-value">${customer.email != null ? customer.email : "N/A"}</div>
                </div>

                <div class="info-group">
                    <label>Address</label>
                    <div class="info-value">${customer.address != null ? customer.address : "N/A"}</div>
                </div>

                <div class="info-group">
                    <label>Phone</label>
                    <div class="info-value">${customer.phone != null ? customer.phone : "N/A"}</div>
                </div>
            </div>
        </div>
        <jsp:include page="componentHome/footer.jsp" />
    </body>
</html>
