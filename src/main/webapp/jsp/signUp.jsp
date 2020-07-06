<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="reg_page.title"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="reg_form">
        <div class="reg_form__items">
            <div class="reg_form__title">
                <fmt:message key="reg_form.title"/>
            </div>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input required name="firstName" placeholder="<fmt:message key="reg_form.first_name"/>"
                       required pattern="^[a-zA-Zа-яА-Я]{2,45}$" title="First name is incorrect"/>
                <input required name="lastName" placeholder="<fmt:message key="reg_form.last_name"/>"
                       required pattern="^[a-zA-Zа-яА-Я]{2,45}$"
                       title="Last name is incorrect"/>
                <input required name="email" placeholder="<fmt:message key="reg_form.email"/>" required
                       pattern="[a-zA-z0-9_.-]{1,40}@[a-zA-z0-9_-]{2,40}\.[a-z]{2,10}" title="Email is incorrect"/>
                <input required name="password" id="password"  type="password"
                       placeholder="<fmt:message key="reg_form.password"/>"/>
                <input required name="passwordTwo" id="password_confirm" type="password" placeholder="<fmt:message
                key="reg_form.confirm_password"/>"/>
                <input required name="phone" placeholder="<fmt:message key="reg_form.phone_number"/>"required
                       pattern="^(\+375[0-9]{2}[0-9]{7})$" title="Invalid format"/>
                <button class="form_btn" type="submit" name="command" value="SIGN_UP">
                    <fmt:message key="reg_form.button"/>
                </button>
            </form>
        </div>
    </div>
</div>
<script src="../js/main.js"></script>
</body>
</html>

<script type="text/javascript">
    window.onload = function () {
        document.getElementById("password").onchange = validatePassword;
        document.getElementById("password_confirm").onchange = validatePassword;
    }
    function validatePassword(){
        var pass2=document.getElementById("password_confirm").value;
        var pass1=document.getElementById("password").value;
        if(pass1!=pass2)
            document.getElementById("password_confirm").setCustomValidity("Passwords Don't Match");
        else
            document.getElementById("password_confirm").setCustomValidity('');
    }
</script>
