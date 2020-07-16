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
    <title><fmt:message key="error_page_500.title"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
    <section class="handler">
        <div class="container">
            <div class="pgnt__items">
                <div class="left__item">
                    <div class="error_image">
                     <img src="${pageContext.request.contextPath}/img/errorImage.svg">
                     </div>
                </div>
        <div class="right__item">
            <div class="code_error_text">
                <fmt:message key="error_page_500.code"/>
             </div>
                <div class="info_error_text">
                <fmt:message key="error_page_500.info"/>
                  </div>
             </div>
                 </div>
                    </div>
                    </section>
</body>
</html>
