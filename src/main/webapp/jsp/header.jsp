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
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header_items"  >
            <a class="header_logo" href="${pageContext.request.contextPath}/controller?command=mainPage&currentPage=1">
                <div class="header_logo__img">
                    <img src="${pageContext.request.contextPath}/img/logo.svg" alt="logo">
                </div>
                <div class="header_logo__name">
                    <fmt:message key="nav_bar.title"/>
                </div>
            </a>
                <c:if test="${sessionScope.userRole == 'GUEST'}">
            <div class="header_btn">
                    <div class="header_signin btnt">
                        <a href="${pageContext.request.contextPath}/controller?command=signInPage"><fmt:message key="nav_bar.sign_in"/></a>
                    </div>
                    <div class="header_register btnt">
                        <a href="${pageContext.request.contextPath}/controller?command=signUpPage"><fmt:message key="nav_bar.sign_up"/></a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.userRole == 'USER' || sessionScope.userRole == 'ADMIN'}">
                    <div class="header_profile_logo">
                        <a href="${pageContext.request.contextPath}/controller?command=profilePage">
                            <img src="${pageContext.request.contextPath}/img/userProfileLogo.svg"></a>
                    </div>
                <div class="header_btn">
                    <div class="header_register btnt">
                        <a href="${pageContext.request.contextPath}/controller?command=logOut">
                            <fmt:message key="nav_bar.log_out"/></a>
                    </div>
                </c:if>
                <div class="header_language">
                    <div class="menu">
                        <a class="menu__title" href="#"><fmt:message key="nav_bar.language"/></a>
                        <div class="menu__down">
                            <div class="menu__section">
                                <a href="${pageContext.request.contextPath}/controller?command=changeLanguage&language=en">
                                    <img src="${pageContext.request.contextPath}/img/eng.svg" alt=""></a></div>
                            <div class="menu__section">
                                <a href="${pageContext.request.contextPath}/controller?command=changeLanguage&language=ru">
                                    <img src="${pageContext.request.contextPath}/img/ru.svg" alt=""></a></div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</header>
</body>
</html>