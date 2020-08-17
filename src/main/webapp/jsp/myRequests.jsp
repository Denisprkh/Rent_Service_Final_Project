<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title><fmt:message key="profile.my_requests"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">


    <div class="all_ads_top">
        <a class="search filters" href="${root}/controller?command=profilePage">
            <fmt:message key="admin_profile.back_button"/>
        </a>
        <fmt:message key="profile.my_requests"/>
    </div>
    <div class="all_ads_handler">
        <div class="formyads">
            <c:choose>
                <c:when test="${empty usersRequestList}">
                    <div class="info_text">
                        <fmt:message key="profile.my_requests_no_requests_info"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="elem" items="${usersRequestList}">
                        <div class="card-ads">
                            <div class="card-ads-info">
                                <div class="name">
                                    <a href="${pageContext.request.contextPath}/controller?command=advertisementPage&advertisementId=${elem.advertisement.id}">${elem.advertisement.title}</a>
                                </div>
                                <div class="date-from-to">
                                    <ctg:date-time value="${elem.startDate}"/>-<ctg:date-time
                                        value="${elem.endDate}"/>
                                </div>
                                <div class="date-to">
                                    <ctg:date-time value="${elem.applicationDate}"/>
                                </div>
                                <div class="phone">
                                    <c:choose>
                                        <c:when test="${elem.isApproved() eq true}">${elem.advertisement.author.phone}</c:when>
                                        <c:otherwise>************</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${elem.isApproved() ne true}">
                                    <a href="#"
                                       class="btn-accept">
                                        <img src="${pageContext.request.contextPath}/img/false.svg" alt="accept">
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#"
                                       class="btn-accept">
                                        <img src="${pageContext.request.contextPath}/img/true.svg"
                                             alt="accept">
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>