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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/dependencyLibs/inputmask.dependencyLib.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/inputmask/inputmask.min.js"></script>
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
            <a href="${pageContext.request.contextPath}/controller?command=myRequestsPage">
                <div class="left-btn__item btn-my_application">
                    <div class="left-btn__item_text btn-my_application">
                        <fmt:message key="profile.my_requests"/>
                    </div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=myAdsPage">
                <div class="left-btn__item btn-my_ads">
                    <div class="left-btn__item_text btn-my_ads">
                        <fmt:message key="profile.my_ads"/>
                    </div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=requestsForMyAdsPage">
                <div class="left-btn__item btn-my_formyads">
                    <div class="left-btn__item_text btn-my_formyads">
                        <fmt:message key="profile.requests_for_my_ads"/>
                    </div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=allUsersPage&currentPage=1">
                <div class="left-btn__item btn-all_users">
                    <div class="left-btn__item_text btn-all_users">
                        <fmt:message key="admin_profile.all_users"/>
                    </div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=allAdvertisementsPage&currentPage=1">
                <div class="left-btn__item btn-all_ads">
                    <div class="left-btn__item_text btn-all_ads">
                        <fmt:message key="admin_profile.all_ads"/>
                    </div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=allRequestsPage&currentPage=1">
                <div class="left-btn__item btn-all_requests">
                    <div class="left-btn__item_text btn-all_requests">
                        <fmt:message key="admin_profile.all_requests"/>
                    </div>
                </div>
            </a>
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
                                       data-type="profile-input" required pattern="^[a-zA-Z-а-яА-Я]{2,45}\s[a-zA-Z-а-яА-Я]{2,45}$"
                                       title="<fmt:message key="profile.my_profile_invalid_full_name_format"/>"/>
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_email"/>
                                </div>
                                <div class="name-botton" data-type="profile-info">
                                    ${user.email}
                                </div>
                                <input type="text" class="name-botton input__profile none" name="updatedEmail"
                                       data-type="profile-input" required pattern="[a-zA-z0-9_.-]{1,35}@[a-zA-z0-9_-]{2,15}\.[a-z]{2,10}"
                                       title="<fmt:message key="sign_up_form.invalid_email_format"/>"/>
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_phone_number"/>
                                </div>
                                <div class="name-botton" data-type="profile-info">
                                    ${user.phone}
                                </div>
                                <input type="phone" class="name-botton input__profile none" name="updatedPhone"
                                       data-type="profile-input" required
                                       pattern="^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$" title="
                        <fmt:message key="sign_up_form.invalid_phone_number_format"/>"/>
                            </div>
                            <div class="btn-profile__button flex">
                                <div class="profile-btn-edit" id="profile-edit">
                                    <fmt:message key="profile.my_profile_edit_button"/>
                                </div>
                                <button class="profile-btn-edit none" id="profile-edit-btn" type="submit" name="command"
                                        value="updateProfileData">
                                    <fmt:message key="profile.my_profile_save_button"/>
                                </button>
                                <div class="profile-btn-edit reset none" id="profile-edit-btn"
                                     data-type="profile-cancel">
                                    <fmt:message key="profile.my_profile_cancel_button"/>
                                </div>
                            </div>
                            <div class="sign_up__err__message">
                                <c:if test="${not empty incorrectDataErrorMessage}">
                                    <fmt:message key="${incorrectDataErrorMessage}"/>
                                </c:if>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>
<script src="${pageContext.request.contextPath}/js/signUp.js"></script>
<script src="${pageContext.request.contextPath}/js/xssProtection.js"></script>
</body>
</html>
