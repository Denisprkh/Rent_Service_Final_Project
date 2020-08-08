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
        <fmt:message key="admin_profile.all_users"/>
    </div>
    <div class="all_ads_handler">
        <div class="formyads">
            <c:forEach var="elem" items="${adminAllUsersList}">
                <div class="card-ads-my-ads all_users_mb">
                    <div class="card-ads all_users_info">
                        <div class="card-ads-info my-ads">
                            <div class="id">
                                    ${elem.id}
                            </div>
                            <div class="name">
                                    ${elem.firstName} ${elem.lastName}
                            </div>
                            <div class="e-mail">
                                    ${elem.email}
                            </div>
                            <div class="phone">
                                    ${elem.phone}
                            </div>
                        </div>
                    </div>
                    <c:if test="${elem.id ne sessionScope.user.id}">
                        <div class="card-ads-btn admins_all_users_btn">
                            <c:choose>
                                <c:when test="${elem.isBanned() ne true}">
                                    <a href="${root}/controller?command=banUser&userId=${elem.id}"
                                       class="btn-delete ban"> <fmt:message key="admin_profile.ban_user"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${root}/controller?command=unBanUser&userId=${elem.id}"
                                       class="btn-update"> <fmt:message key="admin_profile.unban_user"/>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${elem.userRole == 'USER'}">
                                    <a href="${root}/controller?command=giveAdminRights&userId=${elem.id}"
                                       class="btn-update up_rights">
                                        <fmt:message key="admin_profile.give_admin_rights"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${root}/controller?command=pickUpAdminRights&userId=${elem.id}"
                                       class="btn-delete up_rights">
                                        <fmt:message key="admin_profile.pick_up_admin_rights"/>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>
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
                    <a href="${pageContext.request.contextPath}/controller?command=allUsersPage&currentPage=${currentPage-1}">Previous
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
                    <a href="${pageContext.request.contextPath}/controller?command=allUsersPage&currentPage=${currentPage+1}">Next
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
