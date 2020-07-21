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
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container profile flex">
        <div class="left-btn__items">
            <div class="left-btn__item btn-my_profile active">
                <div class="left-btn__item_text btn-my_profile active">
                    My profile
                </div>
            </div>
            <div class="left-btn__item btn-my_application">
                <div class="left-btn__item_text btn-my_application">
                    My applications
                </div>
            </div>
            <div class="left-btn__item btn-my_ads">
                <div class="left-btn__item_text btn-my_ads">
                    My ads
                </div>
            </div>
            <div class="left-btn__item btn-my_formyads">
                <div class="left-btn__item_text btn-my_formyads">
                    Applications for my ads
                </div>
            </div>
        </div>
        <div class="right-profile">
            <div class="my_profile card-btn">
                <div class="profile-top">
                    My profile
                </div>
                <div class="card-profile flex">
                    <div class="profile-info">
                        <div class="name-top">
                            Name
                        </div>
                        <div class="name-botton">
                            Denis Prokhopenko
                        </div>
                        <div class="name-top">
                            E-mail
                        </div>
                        <div class="name-botton">
                            Ctatist3001@wardaftanks.ru
                        </div>
                        <div class="name-top">
                            Phone number
                        </div>
                        <div class="name-botton">
                            +375 29 352 64 30
                        </div>
                        <form action="#">
                            <button class="profile-btn-edit"> Edit profile </button>
                        </form>
                    </div>
                    <div class="profile-img">
                        <img src="img/profile.jpg" alt="profile-img">
                    </div>
                </div>
            </div>
            <div class="my_applications card-btn none">
                <div class="profile-top">
                    My applications
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
                    My ads
                </div>
                <div class="card-profile">
                    <div class="formyads">
                        <div class="btn-row-create-ads">
                            <div class="btn-create-ads">
                                <a href=""> create-ads </a>
                            </div>
                        </div>
                        <div class="top-name-ads">
                            #1
                        </div>
                        <div class="card-ads-my-ads">
                            <div class="card-ads">
                                <div class="card-ads-info my-ads">
                                    <div class="name">
                                        Дворец Лошицкий
                                    </div>
                                    <div class="date-from-to">
                                        08.07.2020
                                    </div>
                                    <div class="price">
                                        1300$
                                    </div>
                                </div>
                            </div>
                            <div class="card-ads-btn">
                                <a href="#" class="btn-delete"> Delete
                                </a>
                                <a href="#" class="btn-update"> Update
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-page">
                        <div class="prevision_page bgc-page">
                            <div class="icon">
                                <img src="${pageContext.request.contextPath}/img/arrow2.svg" alt="">
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
                                <img src="${pageContext.request.contextPath}/img/arrow1.svg" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="application_for_my_ads card-btn none">
                <div class="profile-top">
                    Applications for my ads
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
