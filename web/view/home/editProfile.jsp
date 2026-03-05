<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile - Clothing Shop</title>
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
                    <a href="${pageContext.request.contextPath}/profile" class="nav-item">Account Details</a>
                    <a href="${pageContext.request.contextPath}/editprofile" class="nav-item active">Edit Profile</a>
                </div>
            </div>

            <!-- Main Content -->
            <div class="content">
                <h2>Edit Profile</h2>
                
                <c:if test="${not empty error}">
                    <p style="color: red; margin-bottom: 20px;">${error}</p>
                </c:if>

                <form action="${pageContext.request.contextPath}/editprofile" method="POST">
                    <div class="info-group">
                        <label>Full Name</label>
                        <input type="text" name="fullName" value="${customer.fullName}" class="form-input" required placeholder="Enter full name">
                    </div>

                    <div class="info-group">
                        <label>Email</label>
                        <input type="email" name="email" value="${customer.email}" class="form-input" required placeholder="Enter email">
                    </div>

                    <div class="info-group">
                        <label>Phone Number</label>
                        <input type="text" name="phone" value="${customer.phone}" class="form-input" placeholder="Enter phone number">
                    </div>

                    <div class="info-group">
                        <label>Gender</label>
                        <div style="margin-top: 10px;">
                            <input type="radio" name="gender" value="MALE" ${customer.gender == 'MALE' ? 'checked' : ''}> Male
                            <input type="radio" name="gender" value="FEMALE" ${customer.gender == 'FEMALE' ? 'checked' : ''} style="margin-left: 20px;"> Female
                            <input type="radio" name="gender" value="OTHER" ${customer.gender == 'OTHER' ? 'checked' : ''} style="margin-left: 20px;"> Other
                        </div>
                    </div>

                    <div class="info-group">
                        <label>Address</label>
                        <textarea name="address" rows="3" class="form-input" placeholder="Enter address">${customer.address}</textarea>
                    </div>

                    <div style="margin-top: 30px;">
                        <button type="submit" class="save-btn">Save Changes</button>
                        <a href="${pageContext.request.contextPath}/profile" class="back-link" style="margin-left: 20px;">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="componentHome/footer.jsp" />
    </body>
</html>
