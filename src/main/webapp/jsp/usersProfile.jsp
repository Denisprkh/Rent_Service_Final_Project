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
        <div class="left-btn__items">
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
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_password"/>
                                </div>
                                <div class="name-botton" >
                                        *************
                                </div>
                                <input  class="name-botton input__profile none" name="updatedPassword"
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
                                     data-type="profile-reset">
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
                                                ${elem.dateOfCreation}
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
                                        <a href="${pageContext.request.contextPath}/controller?command=APPROVE_REQUEST&requestId=${elem.id}" class="btn-accept">
                                            <img src="${pageContext.request.contextPath}/img/true.svg" alt="accept">
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/controller?command=DISAPPROVE_REQUEST&requestId=${elem.id}" class="btn-accept">
                                            <img src="${pageContext.request.contextPath}/img/false.svg" alt="accept">
                                        </a>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/usersProfile.js"></script>
</body>
</html>

<script>
    var inputsTel = document.querySelectorAll('input[type="phone"]');

    Inputmask({
        "mask": "+375(99)999-99-99",
        showMaskOnHover: false
    }).mask(inputsTel);

</script>
