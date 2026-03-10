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

        <!-- Google reCAPTCHA -->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
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

            <!-- reCAPTCHA -->
            <div class="g-recaptcha" data-sitekey="YOUR_SITE_KEY"></div>
            <br>

            <button type="submit">Login</button>
        </form>

        <br>

        <!-- Login with Google -->
        <form action="googleLogin" method="get">
            <button type="submit">Login with Google</button>
        </form>

        <br>

        <a href="forgotPassword.jsp">Forgot password?</a>
        <br>
        <p>Don't have an account? <a href="register">Register here</a></p>

        <p style="color:red">${error}</p>
        <p style="color:green">${message}</p>

    </body>
</html>
