<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container profile flex">
        <div class="left-btn__items admins_profile">
            <div class="left-btn__item btn-my_profile active">
                <div class="left-btn__item_text btn-my_profile active">
                    <fmt:message key="profile.my_profile"/>
                </div>
            </div>
            <div class="left-btn__item btn-my_application">
                <div class="left-btn__item_text btn-my_application">
                    <fmt:message key="profile.my_requests"/>
                </div>
            </div>
            <div class="left-btn__item btn-my_ads">
                <div class="left-btn__item_text btn-my_ads">
                    <fmt:message key="profile.my_ads"/>
                </div>
            </div>
            <div class="left-btn__item btn-my_formyads">
                <div class="left-btn__item_text btn-my_formyads">
                    <fmt:message key="profile.requests_for_my_ads"/>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/controller?command=ALL_USERS_PAGE">
            <div class="left-btn__item btn-all_users">
                <div class="left-btn__item_text btn-all_users">
                    <fmt:message key="admin_profile.all_users"/>
                </div>
            </div>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=ALL_ADVERTISEMENTS_PAGE&currentPage=1">
            <div class="left-btn__item btn-all_ads">
                <div class="left-btn__item_text btn-all_ads">
                    <fmt:message key="admin_profile.all_ads"/>
                </div>
            </div>
            </a>
            <div class="left-btn__item btn-all_requests">
                <div class="left-btn__item_text btn-all_requests">
                    <fmt:message key="admin_profile.all_requests"/>
                </div>
            </div>
        </div>
        <div class="right-profile">
            <div class="my_profile card-btn">
                <div class="profile-top">
                    <fmt:message key="profile.my_profile"/>
                </div>
                <div class="card-profile">
                    <div class="profile-info">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <div class="profile-text">
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_name"/>
                                </div>
                                <div class="name-botton" data-type="profile-info">
                                    ${user.firstName} ${user.lastName}
                                </div>
                                <input type="text" class="name-botton input__profile none" name="updatedFullName"
                                       data-type="profile-input">
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_email"/>
                                </div>
                                <div class="name-botton" data-type="profile-info">
                                    ${user.email}
                                </div>
                                <input type="text" class="name-botton input__profile none" name="updatedEmail"
                                       data-type="profile-input">
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_phone_number"/>
                                </div>
                                <div class="name-botton" data-type="profile-info">
                                    ${user.phone}
                                </div>
                                <input type="phone" class="name-botton input__profile none" name="updatedPhone"
                                       data-type="profile-input">
                            </div>
                            <div class="btn-profile__button flex">
                                <div class="profile-btn-edit" id="profile-edit">
                                    <fmt:message key="profile.my_profile_edit_button"/>
                                </div>
                                <button class="profile-btn-edit none" id="profile-edit-btn" type="submit" name="command"
                                        value="UPDATE_PROFILE_DATA">
                                    <fmt:message key="profile.my_profile_save_button"/>
                                </button>
                                <div class="profile-btn-edit reset none" id="profile-edit-btn"
                                     data-type="profile-cancel">
                                    <fmt:message key="profile.my_profile_cancel_button"/>
                                </div>
                            </div>
                            <div class="sign_up__err__message">
                                <c:if test="${not empty sessionScope.incorrectDataErrorMessage}">
                                    <fmt:message key="${sessionScope.incorrectDataErrorMessage}"/>
                                </c:if>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="my_applications card-btn none">
            <div class="profile-top">
                <fmt:message key="profile.my_requests"/>
            </div>
            <div class="card-profile">
                <div class="formyads">
                    <div class="top-name-ads">

                    </div>
                    <c:forEach var="elem" items="${usersRequestList}">
                        <div class="card-ads">
                            <div class="card-ads-info">
                                <div class="name">
                                    <a href="${pageContext.request.contextPath}/controller?command=advertisement_page&advertisementId=${elem.advertisement.id}">
                                            ${elem.advertisement.title}
                                    </a>
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
                                <div class="accept_status">
                                    <c:choose>
                                        <c:when test="${elem.isApproved() eq true}">
                                            <img src="${pageContext.request.contextPath}/img/true.svg" alt="accept">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/img/false.svg" alt="accept">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
        <div class="my_ads card-btn none">
            <div class="profile-top">
                <fmt:message key="profile.my_ads"/>
            </div>
            <div class="card-profile">
                <div class="formyads">
                    <div class="btn-row-create-ads">
                        <div class="btn-create-ads">
                            <a href="${pageContext.request.contextPath}/controller?command=add_an_advertisement_page">
                                <fmt:message key="profile.my_ads_create_button"/> </a>
                        </div>
                    </div>
                    <div class="top-name-ads">

                    </div>
                    <c:forEach var="elem" items="${sessionScope.usersAdvertisementList}">
                        <div class="card-ads-my-ads">
                            <div class="card-ads">
                                <div class="card-ads-info my-ads">
                                    <div class="name">
                                        <a href="${pageContext.request.contextPath}/controller?command=ADVERTISEMENT_PAGE&advertisementId=${elem.id}">${elem.title}</a>
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
                                <c:choose>
                                    <c:when test="${elem.flat.isFree() eq true}">
                                        <a class="btn-delete" href=
                                                "${pageContext.request.contextPath}/controller?command=SET_FLAT_IN_RENT&flatId=${elem.flat.id}&advertisementId=${elem.id}">
                                            <fmt:message key="profile.my_ads_rented_btn"/> </a>
                                    </c:when>
                                    <c:otherwise><a class="btn-update" href=
                                            "${pageContext.request.contextPath}/controller?command=SET_FLAT_IS_NOT_IN_RENT&flatId=${elem.flat.id}&advertisementId=${elem.id}">
                                        <fmt:message key="profile.my_ads_not_rented_btn"/> </a></c:otherwise>
                                </c:choose>
                                <a class="btn-update" href="${pageContext.request.contextPath}/controller?command=UPDATE_ADVERTISEMENT_PAGE&advertisementId=${elem.id}">
                                    <fmt:message key="profile.my_ads_update"/>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="application_for_my_ads card-btn none">
            <div class="profile-top">
                <fmt:message key="profile.requests_for_my_ads"/>
            </div>
            <div class="card-profile">
                <div class="formyads">
                    <div class="top-name-ads">

                    </div>
                    <c:forEach var="elem" items="${requestsOnUsersAdvertisementsList}">
                        <div class="card-ads">
                            <div class="card-ads-info">
                                <div class="name">
                                    <a href="${pageContext.request.contextPath}/controller?command=ADVERTISEMENT_PAGE&advertisementId=${elem.advertisement.id}">${elem.advertisement.title}</a>
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
                                    <a href="${pageContext.request.contextPath}/controller?command=APPROVE_REQUEST&requestId=${elem.id}"
                                       class="btn-accept">
                                        <img src="${pageContext.request.contextPath}/img/true.svg" alt="accept">
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/controller?command=DISAPPROVE_REQUEST&requestId=${elem.id}"
                                       class="btn-accept">
                                        <img src="${pageContext.request.contextPath}/img/false.svg" alt="accept">
                                    </a>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="all_users card-btn none">
            <div class="profile-top">
                <fmt:message key="admin_profile.all_users"/>
            </div>
            <div class="card-profile">
                <div class="formyads">
                    <c:forEach var="elem" items="${sessionScope.adminAllUsersList}">
                        <div class="card-ads-my-ads all_users_mb">
                            <div class="card-ads">
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
                            <div class="card-ads-btn admins_all_users_btn">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="userId" value="${elem.id}"/>
                                    <c:choose>
                                        <c:when test="${elem.isBanned() ne true}">
                                            <button class="btn-delete ban" name="command" value="BAN_USER">
                                                <fmt:message key="admin_profile.ban_user"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn-update ban" name="command" value="UNBAN_USER">
                                                <fmt:message key="admin_profile.unban_user"/>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <a href="#" class="btn-update up_rights"> Pick up rights
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="card-page">
                    <div class="prevision_page bgc-page">
                        <div class="icon">
                            <img src="img/arrow2.svg" alt="">
                        </div>
                        <div class="prev-text">
                            Prevision page
                        </div>
                    </div>
                    <div class="number-page">
                        1
                    </div>
                    <div class="next-page bgc-page">
                        <div class="next-page-text">
                            next-page
                        </div>
                        <div class="icon">
                            <img src="img/arrow1.svg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="all_ads card-btn none">
            <div class="profile-top">
                <fmt:message key="admin_profile.all_ads"/>
            </div>
<%--            <div class="card-profile">--%>
<%--                <div class="formyads">--%>
<%--                    <div class="top-name-ads">--%>
<%--                        #1--%>
<%--                    </div>--%>
<%--                    <c:forEach var="elem" items="${sessionScope.adminAllAdvertisementsList}">--%>
<%--                        <div class="card-ads-my-ads">--%>
<%--                            <div class="card-ads">--%>
<%--                                <div class="card-ads-info my-ads">--%>
<%--                                    <div class="name">--%>
<%--                                        <a href="${pageContext.request.contextPath}/controller?command=ADVERTISEMENT_PAGE&advertisementId=${elem.id}">${elem.title}</a>--%>
<%--                                    </div>--%>
<%--                                    <div class="date-from-to">--%>
<%--                                        <ctg:date-time value="${elem.dateOfCreation}"/>--%>
<%--                                    </div>--%>
<%--                                    <div class="price">--%>
<%--                                            ${elem.price}$--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="card-ads-btn">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${elem.flat.isFree() eq true}">--%>
<%--                                        <div class="admin_flat_status_info_free">--%>
<%--                                            <fmt:message key="admin_profile.flat_status_free"/>--%>
<%--                                        </div>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>--%>
<%--                                        <div class="admin_flat_status_info_not_free">--%>
<%--                                            <fmt:message key="admin_profile.flat_status_not_free"/>--%>
<%--                                        </div>--%>
<%--                                    </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--                <div class="card-page">--%>
<%--                    <div class="prevision_page bgc-page">--%>
<%--                        <div class="icon">--%>
<%--                            <img src="${pageContext.request.contextPath}/img/arrow2.svg" alt="">--%>
<%--                        </div>--%>
<%--                        <div class="prev-text">--%>
<%--                            Prevision page--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="number-page">--%>
<%--                        1--%>
<%--                    </div>--%>
<%--                    <div class="next-page bgc-page">--%>
<%--                        <div class="next-page-text">--%>
<%--                            next-page--%>
<%--                        </div>--%>
<%--                        <div class="icon">--%>
<%--                            <img src="${pageContext.request.contextPath}/img/arrow1.svg" alt="">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
        <div class="all_requests card-btn none">
            <div class="profile-top">
                <fmt:message key="admin_profile.all_requests"/>
            </div>
            <div class="card-profile">
                <div class="formyads">
                    <div class="top-name-ads">
                        #1
                    </div>
                    <div class="card-ads">
                        <div class="name_ads">
                            Дворец Лошицкий
                        </div>
                        <div class="btn-accept">
                            <img src="${pageContext.request.contextPath}/img/tick.svg" alt="accept">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>
</body>
</html>
