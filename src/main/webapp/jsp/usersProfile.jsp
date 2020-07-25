<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <input type="text" class="name-botton input__profile none" name="updatedFullName" data-type="profile-input">
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_email"/>
                                </div>
                                <div class="name-botton" data-type="profile-info">
                                    ${user.email}
                                </div>
                                <input type="text" class="name-botton input__profile none" name="updatedEmail" data-type="profile-input">
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_phone_number"/>
                                </div>
                                <div class="name-botton" data-type="profile-info">
                                    ${user.phone}
                                </div>
                                <input type="phone" class="name-botton input__profile none" name="updatedPhone" data-type="profile-input">
                                <div class="name-top">
                                    <fmt:message key="profile.my_profile_password"/>
                                </div>
                                <div class="name-botton" data-type="profile-info" >

                                </div>
                                <input type="password" class="name-botton input__profile none" name="updatedPassword" data-type="profile-input">
                            </div>
                            <div class="btn-profile__button flex">
                                <div class="profile-btn-edit" id="profile-edit">
                                    <fmt:message key="profile.my_profile_edit_button"/>
                                </div>
                                <button class="profile-btn-edit none" id="profile-edit-btn" type="submit" name="command"
                                value="UPDATE_PROFILE_DATA">
                                    <fmt:message key="profile.my_profile_save_button"/>
                                </button>
                                <div class="profile-btn-edit reset none" id="profile-edit-btn" data-type="profile-reset" >
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
                        <c:forEach var="elem" items="${advertisementList}">
                        <div class="card-ads-my-ads">
                            <a href="${pageContext.request.contextPath}/controller?command=advertisement_page&advertisementId=${elem.id}">
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
                            </a>
                            <div class="card-ads-btn">
                                <a href="${pageContext.request.contextPath}/controller?command=delete_advertisement&advertisementId=${elem.id}"
                                   class="btn-delete"> <fmt:message key="profile.my_ads_delete_button"/>
                                </a>
                                <a href="#" class="btn-update"> <fmt:message key="profile.my_ads_update_button"/>
                                </a>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <div class="card-page">
                        <div class="prevision_page bgc-page">
                            <div class="icon">
                                <img src="${pageContext.request.contextPath}/img/arrow2.svg" alt="">
                            </div>
                            <div class="prev-text">
                                <fmt:message key="pagination.previous_page_button"/>
                            </div>
                        </div>
                        <div class="number-page">
                            1
                        </div>
                        <div class="next-page bgc-page">
                            <div class="next-page-text">
                                <fmt:message key="pagination.next_page_button"/>
                            </div>
                            <div class="icon">
                                <img src="${pageContext.request.contextPath}/img/arrow1.svg" alt="">
                            </div>
                        </div>
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
                            #1
                        </div>
                        <div class="card-ads">
                            <div class="card-ads-info">
                                <div class="name">
                                    Дворец Лошицкий
                                </div>
                                <div class="date-from-to">
                                    08.07.2020-10.07.2020
                                </div>
                                <div class="date-to">
                                    03.07.2020; 17:25
                                </div>
                                <div class="phone">
                                    +375 25 111 22 33
                                </div>
                            </div>
                            <a href="#" class="btn-accept">
                                <img src="${pageContext.request.contextPath}/img/tick.svg" alt="accept">
                            </a>
                        </div>
                    </div>
                    <div class="card-page">
                        <div class="prevision_page bgc-page">
                            <div class="icon">
                                <img src="${pageContext.request.contextPath}/img/arrow2.svg" alt="">
                            </div>
                            <div class="prev-text">
                                <fmt:message key="pagination.previous_page_button"/>
                            </div>
                        </div>
                        <div class="number-page">
                            1
                        </div>
                        <div class="next-page bgc-page">
                            <div class="next-page-text">
                                <fmt:message key="pagination.next_page_button"/>
                            </div>
                            <div class="icon">
                                <img src="${pageContext.request.contextPath}/img/arrow1.svg" alt="">
                            </div>
                        </div>
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
