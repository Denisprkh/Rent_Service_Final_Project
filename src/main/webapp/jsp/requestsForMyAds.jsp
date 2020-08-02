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
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">


    <div class="all_ads_top">
        <a class="search filters" href="${root}/controller?command=profilePage">
            <fmt:message key="admin_profile.back_button"/>
        </a>
        <fmt:message key="profile.requests_for_my_ads"/>
    </div>
    <div class="all_ads_handler">
        <div class="formyads">
            <c:forEach var="elem" items="${requestsOnUsersAdvertisementsList}">
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
                                ${elem.user.phone}
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${elem.isApproved() ne true}">
                            <a href="${pageContext.request.contextPath}/controller?command=approveRequest&requestId=${elem.id}"
                               class="btn-accept">
                                <img src="${pageContext.request.contextPath}/img/true.svg" alt="accept">
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/controller?command=disapproveRequest&requestId=${elem.id}"
                               class="btn-accept">
                                <img src="${pageContext.request.contextPath}/img/false.svg" alt="accept">
                            </a>
                        </c:otherwise>
                    </c:choose>

                </div>
            </c:forEach>
        </div>
    </div>
    <div class="card-page">
        <c:if test="${currentPage != 1 && pagesQuantity > 0}">
            <div class="prevision_page bgc-page">
                <div class="icon">
                    <img src="${pageContext.request.contextPath}/img/arrow2.svg" alt="">
                </div>
                <div class="prev-text">
                    <a href="${pageContext.request.contextPath}/controller?command=ALL_USERS_PAGE&currentPage=${currentPage-1}">Previous
                        page</a>
                </div>
            </div>
        </c:if>


        <div class="number-page">
            <c:if test="${pagesQuantity ne 0 && pagesQuantity ne 1}">
                ${currentPage}
            </c:if>
        </div>
        <c:if test="${currentPage ne pagesQuantity && pagesQuantity > 0}">
            <div class="next-page bgc-page">
                <div class="next-page-text">
                    <a href="${pageContext.request.contextPath}/controller?command=ALL_USERS_PAGE&currentPage=${currentPage+1}">Next
                        page</a>
                </div>
                <div class="icon">
                    <img src="${root}/img/arrow1.svg" alt="">
                </div>
            </div>
        </c:if>
    </div>

</div>
</body>
</html>