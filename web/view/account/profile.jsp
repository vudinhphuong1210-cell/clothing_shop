<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile - Clothing Shop</title>
    </head>
    <body>
        <div>
            <a href="${pageContext.request.contextPath}/home">Back to Home</a>
        </div>
        
        <h2>My Profile</h2>
        <a href="${pageContext.request.contextPath}/editprofile">Edit Profile</a>
        
        <hr>
        
        <p><strong>Username:</strong> ${sessionScope.account.userName}</p>
        <p><strong>Full Name:</strong> ${customer.fullName != null ? customer.fullName : "Not set"}</p>
        <p><strong>Email:</strong> ${customer.email != null ? customer.email : "Not set"}</p>
        <p><strong>Phone:</strong> ${customer.phone != null ? customer.phone : "Not set"}</p>
        <p><strong>Gender:</strong> ${customer.gender != null ? customer.gender : "Not set"}</p>
        <p><strong>Address:</strong> ${customer.address != null ? customer.address : "Not set"}</p>
        <p><strong>My Points:</strong> ${customer.point} Points</p>
        <p><strong>Member Since:</strong> ${customer.createdAt}</p>
    </body>
</html>
