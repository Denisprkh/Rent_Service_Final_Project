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
    <title><fmt:message key="profile.my_ads"/> </title>
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
            <c:choose>
                <c:when test="${empty usersAdvertisementList}">
                    <div class="info_text">
                        <fmt:message key="profile.my_ads_no_ads_info"/>
                    </div>
                </c:when>
                <c:otherwise>
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
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="advertisementId" value="${elem.id}"/>
                                    <button class="btn-delete" name="command" value="deleteAdvertisement">
                                        <fmt:message key="advertisement.delete_button"/>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="flatId" value="${elem.flat.id}"/>
                                    <input type="hidden" name="advertisementId" value="${elem.id}">
                                    <c:choose>
                                        <c:when test="${elem.flat.isFree() eq true}">
                                            <button class="btn-delete" name="command" value="setFlatInRent">
                                                <fmt:message key="profile.my_ads_rented_btn"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn-update" name="command" value="setFlatIsNotInRent">
                                                <fmt:message key="profile.my_ads_not_rented_btn"/>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <a class="btn-update"
                                   href="${pageContext.request.contextPath}/controller?command=updateAdvertisementPage&advertisementId=${elem.id}">
                                    <fmt:message key="profile.my_ads_update"/>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
