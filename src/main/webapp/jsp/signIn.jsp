<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="style.css"/>
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header_items"  >
            <a class="header_logo" href="${pageContext.request.contextPath}/main.jsp">
                <div class="header_logo__img">
                    <img src="${pageContext.request.contextPath}/../img/logo.svg" alt="logo">
                </div>
                <div class="header_logo__name">
                    Flat rent service
                </div>
            </a>
            <div class="header_btn">
                <div class="header_signin btnt">
                    Sign in
                </div>
                <div class="header_register btnt">
                    <a href="${pageContext.request.contextPath}signUp.jsp">Register</a>
                </div>
                <div class="header_language">
                    <img src="${pageContext.request.contextPath}/../img/eng.svg" alt="">
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="reg_form_sign_in">
        <div class="reg_form__items">
            <div class="reg_form__title">
                Sign in
            </div>
            <form action="controller" method="post">
                <input required name="email" placeholder="email" required
                       pattern="[a-zA-z0-9_.-]{1,40}@[a-zA-z0-9_-]{2,40}\.[a-z]{2,10}" title="Email format is incorrect"/>
                <input required name="password" id="password" placeholder="password"/>
                <button class="form_btn" type="submit" name="command" value="SIGN_IN">
                    Sign in
                </button>
            </form>
        </div>
    </div>
</div>
<script src="../js/main.js"></script>
</body>
</html>