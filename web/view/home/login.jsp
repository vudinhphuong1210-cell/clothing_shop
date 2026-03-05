<%-- 
    Document   : login
    Created on : Feb 21, 2026, 12:25:29 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>

    </head>
    <body>

        <h2>Login</h2>

        <form action="login" method="post">

            Username or Email:
            <input type="text" name="username" required />
            <br><br>

            Password:
            <input type="password" name="password" required />
            <br><br>

            <input type="checkbox" name="remember" value="true" />
            Remember me
            <br><br>


            <button type="submit">Login</button>
        </form>

        <br>

        <!-- Login with Google -->
        <br>
        <form action="${pageContext.request.contextPath}/googleLogin" method="get">
            <button type="submit">
                <img src="https://www.gstatic.com/images/branding/product/1x/gsa_512dp.png" width="20"/>
                Sign in with Google
            </button>
        </form>


        <br>

        <a href="forgotPassword.jsp">Forgot password?</a>
        <br>
        <p>Don't have an account? <a href="register">Register here</a></p>

        <p style="color:red">${error}</p>
        <p style="color:green">${message}</p>

    </body>
</html>
