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
    <title><fmt:message key="admin_profile.all_ads"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">

    <div class="all_ads_top">
        <a class="search filters" href="${root}/controller?command=profilePage">
            <fmt:message key="admin_profile.back_button"/></a>
        <fmt:message key="admin_profile.all_ads"/>
    </div>

    <div class="all_ads_handler">
        <div class="formyads">
            <c:forEach var="elem" items="${adminAllAdvertisementsList}">
                <div class="card-ads-my-ads">
                    <div class="card-ads">
                        <div class="card-ads-info my-ads">
                            <div class="name">
                                    ${elem.title}
                            </div>
                            <div class="date-from-to">
                                    ${elem.dateOfCreation}
                            </div>
                            <div class="price">
                                    ${elem.price}
                            </div>
                        </div>
                    </div>
                    <form action="${root}/controller" method="post">
                        <input type="hidden" name="advertisementId" value="${elem.id}">
                        <button class="btn-delete" name="command" value="deleteAdvertisement">
                            <fmt:message key="advertisement.delete_button"/>
                        </button>
                    </form>
                    <c:choose>
                        <c:when test="${elem.flat.isFree() eq true}">
                            <div class="admin_flat_status_info_free">
                                <fmt:message key="admin_profile.flat_status_free"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="admin_flat_status_info_not_free">
                                <fmt:message key="admin_profile.flat_status_not_free"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <a class="btn-update"
                       href="${root}/controller?command=updateAdvertisementPage&advertisementId=${elem.id}">
                        <fmt:message key="profile.my_ads_update"/>
                    </a>
                    <c:if test="${elem.author.id ne sessionScope.user.id}">
                        <form action="${root}/controller" method="post">
                            <input type="hidden" name="userId" value="${elem.author.id}">
                            <button class="btn-delete" name="command" value="banUser">
                                <fmt:message key="admin_profile.ban_author_btn"/>
                            </button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <div class="card-page">
            <c:if test="${currentPage != 1 && pagesQuantity > 0}">
                <div class="prevision_page bgc-page">
                    <div class="icon">
                        <img src="${pageContext.request.contextPath}/img/arrow2.svg" alt="">
                    </div>
                    <div class="prev-text">
                        <a href="${pageContext.request.contextPath}/controller?command=allAdvertisementsPage&currentPage=${currentPage-1}">
                            <fmt:message key="pagination.previous_page_button"/>
                        </a>
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
                        <a href="${pageContext.request.contextPath}/controller?command=allAdvertisementsPage&currentPage=${currentPage+1}">
                            <fmt:message key="pagination.next_page_button"/>
                        </a>
                    </div>
                    <div class="icon">
                        <img src="${root}/img/arrow1.svg" alt="">
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
