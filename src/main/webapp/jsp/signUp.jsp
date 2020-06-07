<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>registration</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header_items"  >
            <a class="header_logo" href="#">
                <div class="header_logo__img">
                    <img src="../img/logo.svg" alt="logo">
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
                    Register
                </div>
                <div class="header_language">
                    <img src="../img/eng.svg" alt="">
                </div>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="reg_form">
        <div class="reg_form__items">
            <div class="reg_form__title">
                Registration
            </div>
            <form action="/controller" method="post">
                <input required type="firstName" value="First name"/>
                <input required type="lastName" value="Last name"/>
                <input required type="email" value="E-mail"/>
                <input required type="password" value="Password"/>
                <input required type="passwordTwo" value="Confirm password"/>
                <input required type="phone" value="Phone number"/>
                <button class="form_btn" type="submit" name="command" value="SIGN_UP">
                    Register
                </button>
            </form>
        </div>
    </div>
</div>
<script src="../js/main.js"></script>
</body>
</html>