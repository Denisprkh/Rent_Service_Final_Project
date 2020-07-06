<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="sign_in.title"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="reg_form_sign_in">
        <div class="reg_form__items">
            <div class="reg_form__title">
                <fmt:message key="sign_in_form.title"/>
            </div>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input required name="email" placeholder="<fmt:message key="sign_in_form.email"/>" required
                       pattern="[a-zA-z0-9_.-]{1,40}@[a-zA-z0-9_-]{2,40}\.[a-z]{2,10}" title="Email format is incorrect"/>
                <input required name="password" id="password" placeholder="<fmt:message key="sign_in_form.password"/>"/>
                <button class="form_btn" type="submit" name="command" value="SIGN_IN">
                    <fmt:message key="sign_in_form.button"/>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>