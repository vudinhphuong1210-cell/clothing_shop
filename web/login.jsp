<%-- 
    Document   : login
    Created on : Jan 6, 2026, 2:04:53 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Mini Supermarket Login</title>
        <style>
            .form-container {
                width: 100%;
                max-width: 340px;
                background: linear-gradient(145deg, #0f0f0f 0%, #080808 50%, #0a0a0a 100%);
                color: #ffffff;
                border: 1px solid rgba(212, 168, 75, 0.15);
                overflow: hidden;
                box-shadow: 0 30px 80px -20px rgba(0, 0, 0, 0.8);
                position: relative;
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
                box-sizing: border-box;
            }

            .form-container::before {
                content: "";
                display: block;
                height: 3px;
                background: linear-gradient(
                    90deg,
                    transparent 0%,
                    #b8922f 20%,
                    #d4a84b 50%,
                    #b8922f 80%,
                    transparent 100%
                    );
            }

            .form-content {
                padding: 22px 22px 22px;
            }

            .title-section {
                display: flex;
                align-items: center;
                gap: 10px;
                margin-bottom: 22px;
                padding-bottom: 10px;
                border-bottom: 2px solid transparent;
                border-image: linear-gradient(90deg, #d4a84b, transparent) 1;
            }

            .title-icon {
                width: 26px;
                height: 26px;
                color: #d4a84b;
            }

            .title {
                font-family: Impact, "Arial Black", sans-serif;
                font-size: 1.4rem;
                font-weight: 400;
                letter-spacing: 2px;
                text-transform: uppercase;
                background: linear-gradient(180deg, #ffffff 30%, #999999 100%);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                background-clip: text;
                margin: 0;
            }

            .form {
                display: flex;
                flex-direction: column;
                gap: 10px;
            }

            .input-group {
                background: linear-gradient(
                    135deg,
                    rgba(10, 22, 40, 0.8) 0%,
                    rgba(7, 18, 34, 0.9) 100%
                    );
                border: 1px solid #1a2d47;
                padding: 10px 12px;
                display: flex;
                align-items: center;
                gap: 12px;
                transition: all 0.3s ease;
                position: relative;
                box-sizing: border-box;
            }

            .input-group::before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                width: 3px;
                height: 0;
                background: linear-gradient(180deg, #d4a84b, #00d4ff);
                transition: height 0.3s ease;
            }

            .input-group:focus-within::before {
                height: 100%;
            }

            .input-group:focus-within {
                border-color: #d4a84b;
                transform: translateX(3px);
            }

            .input-group:hover {
                border-color: #2a4a70;
            }

            .input-icon {
                width: 34px;
                height: 34px;
                background: linear-gradient(
                    135deg,
                    rgba(30, 58, 95, 0.7) 0%,
                    rgba(20, 40, 70, 0.5) 100%
                    );
                border: 1px solid rgba(74, 158, 255, 0.2);
                display: flex;
                align-items: center;
                justify-content: center;
                flex-shrink: 0;
                transition: all 0.3s ease;
                box-sizing: border-box;
            }

            .input-group:focus-within .input-icon {
                background: #0a0a0a;
                border-color: #d4a84b;
            }

            .input-icon svg {
                width: 16px;
                height: 16px;
                color: #00d4ff;
                transition: all 0.3s ease;
            }

            .input-group:focus-within .input-icon svg {
                color: #d4a84b;
            }

            .input-content {
                flex: 1;
                min-width: 0;
            }

            .input-group label {
                display: block;
                color: #6b7280;
                font-size: 0.55rem;
                font-weight: 600;
                letter-spacing: 1.5px;
                text-transform: uppercase;
                margin-bottom: 3px;
                transition: all 0.3s ease;
            }

            .input-group:focus-within label {
                color: #d4a84b;
            }

            .input-group input {
                width: 100%;
                border: none;
                outline: none;
                background: transparent;
                color: #ffffff;
                font-size: 0.85rem;
                font-weight: 500;
                padding: 0;
                box-sizing: border-box;
            }

            .input-group input::placeholder {
                color: #3a4a5a;
            }

            .forgot {
                display: flex;
                justify-content: flex-end;
                margin-top: 6px;
            }

            .forgot a {
                color: #6b7280;
                text-decoration: none;
                font-size: 0.65rem;
                transition: color 0.3s ease;
            }

            .forgot a:hover {
                color: #d4a84b;
            }

            .sign {
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 8px;
                width: 100%;
                background: linear-gradient(135deg, #d4a84b 0%, #b8922f 100%);
                padding: 10px;
                color: #000;
                border: none;
                font-weight: 700;
                font-size: 0.7rem;
                letter-spacing: 2px;
                text-transform: uppercase;
                cursor: pointer;
                transition: all 0.3s ease;
                margin-top: 6px;
                box-sizing: border-box;
            }

            .sign:hover {
                transform: translateY(-2px);
                box-shadow: 0 10px 30px rgba(212, 168, 75, 0.4);
            }

            .sign-arrow {
                transition: transform 0.3s ease;
            }

            .sign:hover .sign-arrow {
                transform: translateX(4px);
            }

            .social-message {
                display: flex;
                align-items: center;
                padding-top: 18px;
                margin-bottom: 12px;
            }

            .line {
                height: 1px;
                flex: 1;
                background: linear-gradient(
                    90deg,
                    transparent,
                    rgba(255, 255, 255, 0.1),
                    transparent
                    );
            }

            .social-message .message {
                padding: 0 12px;
                font-size: 0.55rem;
                color: #6b7280;
                text-transform: uppercase;
                letter-spacing: 1.5px;
            }

            .social-icons {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 8px;
            }

            .social-icons .icon {
                height: 54px;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                gap: 6px;
                background: linear-gradient(
                    135deg,
                    rgba(10, 22, 40, 0.8) 0%,
                    rgba(7, 18, 34, 0.9) 100%
                    );
                border: 1px solid #1a2d47;
                cursor: pointer;
                transition: all 0.3s ease;
                box-sizing: border-box;
            }

            .social-icons .icon:hover {
                transform: translateY(-3px);
                border-color: #d4a84b;
            }

            .social-icons .icon svg {
                height: 18px;
                width: 18px;
                fill: #ffffff;
                transition: all 0.3s ease;
            }

            .social-icons .icon:hover svg {
                fill: #d4a84b;
            }

            .social-icons .icon span {
                font-size: 0.5rem;
                color: #6b7280;
                text-transform: uppercase;
                letter-spacing: 1px;
            }

            .social-icons .icon:hover span {
                color: #d4a84b;
            }

            .signup {
                text-align: center;
                font-size: 0.7rem;
                color: #6b7280;
                margin-top: 16px;
            }

            .signup a {
                color: #d4a84b;
                text-decoration: none;
                font-weight: 600;
            }

            .signup a:hover {
                color: #ffffff;
            }
             body {
            margin: 0;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color:#3a4a5a; 
        }
        
        </style>
    </head>
    <body>
        <div class="form-container">
            <div class="form-content">
                <div class="title-section">
                    <svg
                        class="title-icon"
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="1.5"
                        >
                    <path
                        d="M3 7v10a2 2 0 002 2h14a2 2 0 002-2V9a2 2 0 00-2-2h-6l-2-2H5a2 2 0 00-2 2z"
                        ></path>
                    </svg>
                    <p class="title">Dossier</p>
                </div>
                <form class="form" method="POST">
                    <div class="input-group">
                        <div class="input-icon">
                            <svg
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="1.5"
                                >
                            <path d="M20 21v-2a4 4 0 00-4-4H8a4 4 0 00-4 4v2"></path>
                            <circle cx="12" cy="7" r="4"></circle>
                            </svg>
                        </div>
                        <div class="input-content" >
                            <label for="username">Username</label>
                            <input
                                type="text"
                                name="username"
                                id="username"
                                placeholder="Enter your username"
                                />
                        </div>
                    </div>
                    <div class="input-group">
                        <div class="input-icon">
                            <svg
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="1.5"
                                >
                            <rect x="3" y="11" width="18" height="11" rx="2" ry="2"></rect>
                            <path d="M7 11V7a5 5 0 0110 0v4"></path>
                            </svg>
                        </div>
                        <div class="input-content">
                            <label for="password">Password</label>
                            <input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Enter your password"
                                />
                        </div>
                    </div>
                    <div class="forgot">
                        <a href="#">Forgot Password?</a>
                    </div>
                    <button class="sign" type="submit">
                        <span>Sign In</span>
                        <span class="sign-arrow">→</span>
                    </button>
                </form>
                <div class="social-message">
                    <div class="line"></div>
                    <p class="message">Or continue with</p>
                    <div class="line"></div>
                </div>
                <div class="social-icons">
                    <button aria-label="Log in with Google" class="icon">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                        <path
                            d="M16.318 13.714v5.484h9.078c-0.37 2.354-2.745 6.901-9.078 6.901-5.458 0-9.917-4.521-9.917-10.099s4.458-10.099 9.917-10.099c3.109 0 5.193 1.318 6.38 2.464l4.339-4.182c-2.786-2.599-6.396-4.182-10.719-4.182-8.844 0-16 7.151-16 16s7.156 16 16 16c9.234 0 15.365-6.49 15.365-15.635 0-1.052-0.115-1.854-0.255-2.651z"
                            ></path>
                        </svg>
                        <span>Google</span>
                    </button>
                    <button aria-label="Log in with GitHub" class="icon">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32">
                        <path
                            d="M16 0.396c-8.839 0-16 7.167-16 16 0 7.073 4.584 13.068 10.937 15.183 0.803 0.151 1.093-0.344 1.093-0.772 0-0.38-0.009-1.385-0.015-2.719-4.453 0.964-5.391-2.151-5.391-2.151-0.729-1.844-1.781-2.339-1.781-2.339-1.448-0.989 0.115-0.968 0.115-0.968 1.604 0.109 2.448 1.645 2.448 1.645 1.427 2.448 3.744 1.74 4.661 1.328 0.14-1.031 0.557-1.74 1.011-2.135-3.552-0.401-7.287-1.776-7.287-7.907 0-1.751 0.62-3.177 1.645-4.297-0.177-0.401-0.719-2.031 0.141-4.235 0 0 1.339-0.427 4.4 1.641 1.281-0.355 2.641-0.532 4-0.541 1.36 0.009 2.719 0.187 4 0.541 3.057-2.068 4.406-1.641 4.406-1.641 0.859 2.204 0.317 3.833 0.161 4.235 1.015 1.12 1.635 2.547 1.635 4.297 0 6.145-3.74 7.5-7.296 7.891 0.556 0.479 1.077 1.464 1.077 2.959 0 2.14-0.020 3.864-0.020 4.385 0 0.416 0.28 0.916 1.104 0.755 6.4-2.093 10.979-8.093 10.979-15.156 0-8.833-7.161-16-16-16z"
                            ></path>
                        </svg>
                        <span>GitHub</span>
                    </button>
                    <button aria-label="Log in with X" class="icon">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                        <path
                            d="M18.244 2.25h3.308l-7.227 8.26 8.502 11.24H16.17l-5.214-6.817L4.99 21.75H1.68l7.73-8.835L1.254 2.25H8.08l4.713 6.231zm-1.161 17.52h1.833L7.084 4.126H5.117z"
                            ></path>
                        </svg>
                        <span>X</span>
                    </button>
                </div>
                <p class="signup">Don't have an account? <a href="#">Sign up</a></p>
            </div>
        </div>

    </body>
</html>
