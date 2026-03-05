<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile - Clothing Shop</title>
    </head>
    <body>
        <h2>Edit Profile</h2>
        
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/editprofile" method="POST">
            <div>
                <label>Full Name:</label>
                <input type="text" name="fullName" value="${customer.fullName}" required>
            </div>
            
            <div>
                <label>Email:</label>
                <input type="email" name="email" value="${customer.email}" required>
            </div>
            
            <div>
                <label>Phone Number:</label>
                <input type="text" name="phone" value="${customer.phone}">
            </div>
            
            <div>
                <label>Gender:</label>
                <input type="radio" name="gender" value="MALE" ${customer.gender == 'MALE' ? 'checked' : ''}> Male
                <input type="radio" name="gender" value="FEMALE" ${customer.gender == 'FEMALE' ? 'checked' : ''}> Female
                <input type="radio" name="gender" value="OTHER" ${customer.gender == 'OTHER' ? 'checked' : ''}> Other
            </div>
            
            <div>
                <label>Address:</label>
                <textarea name="address" rows="3">${customer.address}</textarea>
            </div>
            
            <div>
                <button type="submit">Save Changes</button>
                <a href="${pageContext.request.contextPath}/profile">Cancel</a>
            </div>
        </form>
    </body>
</html>
