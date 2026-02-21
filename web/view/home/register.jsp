<%-- 
    Document   : register
    Created on : Feb 21, 2026, 2:22:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Register</h2>

        <form action="register" method="post">

            <div>
                <label>First Name</label>
                <input type="text" name="firstName" required>
            </div>

            <div>
                <label>Last Name</label>
                <input type="text" name="lastName" required>
            </div>

            <div>
                <label>Username</label>
                <input type="text" name="username" required>
            </div>

            <div>
                <label>Passwords</label>
                <input type="password" name="password" required>
            </div>

            <div>
                <label>Email</label>
                <input type="email" name="email" required>
            </div>

            <button type="submit">REGISTER</button>

        </form>
    </body>
</html>
