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
        <fmt:message key="profile.my_ads"/>
    </div>
    <div class="all_ads_handler">
        <div class="formyads">
            <div class="btn-row-create-ads">
                <div class="btn-create-ads">
                    <a href="${pageContext.request.contextPath}/controller?command=addAnAdvertisementPage">
                        <fmt:message key="profile.my_ads_create_button"/> </a>
                </div>
            </div>
            <c:forEach var="elem" items="${usersAdvertisementList}">
                <div class="card-ads-my-ads">
                    <div class="card-ads">
                        <div class="card-ads-info my-ads">
                            <div class="name">
                                <a href="${pageContext.request.contextPath}/controller?command=advertisementPage&advertisementId=${elem.id}">${elem.title}</a>
                            </div>
                            <div class="date-from-to">
                                <ctg:date-time value="${elem.dateOfCreation}"/>
                            </div>
                            <div class="price">
                                    ${elem.price}$
                            </div>
                        </div>
                    </div>
                    <div class="card-ads-btn">
                        <a  class="btn-delete" href="${pageContext.request.contextPath}/controller?command=deleteAdvertisement&advertisementId=${elem.id}">
                            <fmt:message key="advertisement.delete_button"/>
                        </a>
                        <c:choose>
                            <c:when test="${elem.flat.isFree() eq true}">
                                <a class="btn-delete" href=
                                        "${pageContext.request.contextPath}/controller?command=setFlatInRent&flatId=${elem.flat.id}&advertisementId=${elem.id}">
                                    <fmt:message key="profile.my_ads_rented_btn"/> </a>
                            </c:when>
                            <c:otherwise><a class="btn-update" href=
                                    "${pageContext.request.contextPath}/controller?command=setFlatIsNotInRent&flatId=${elem.flat.id}&advertisementId=${elem.id}">
                                <fmt:message key="profile.my_ads_not_rented_btn"/> </a></c:otherwise>
                        </c:choose>
                        <a class="btn-update"
                           href="${pageContext.request.contextPath}/controller?command=updateAdvertisementPage&advertisementId=${elem.id}">
                            <fmt:message key="profile.my_ads_update"/>
                        </a>
                    </div>
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
