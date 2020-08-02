<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/dist/css/datepicker.css">
    <script src="http://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/dist/js/datepicker.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container main__page_block">
        <div class="image_block">
            <div class="adv_img_first">
                <img src="data:image/jpg;base64,${advertisement.flat.flatPhotos.get(0).base64PhotoData}"/>
            </div>
            <div class="adv_img">
                <img src="data:image/jpg;base64,${advertisement.flat.flatPhotos.get(1).base64PhotoData}"/>
            </div>
            <div class="adv_img">
                <img src="data:image/jpg;base64,${advertisement.flat.flatPhotos.get(2).base64PhotoData}"/>
            </div>

        </div>
        <div class="page__name">
            <div class="page__name_text">
                ${advertisement.getTitle()}
            </div>
            <div class="page__name_price">
                ${advertisement.getPrice()}$
            </div>
        </div>
        <div class="description__elements">
            <div class="location__element">
                <fmt:message key="advertisement.location"/>
                <ul>
                    <li><span><fmt:message key="advertisement.city"/> ${advertisement.flat.flatAddress.city}</span></li>
                    <li><span><fmt:message
                            key="advertisement.district"/> ${advertisement.flat.flatAddress.district}</span></li>
                    <li><span><fmt:message key="advertisement.street"/> ${advertisement.flat.flatAddress.street}</span>
                    </li>
                    <li><span><fmt:message key="advertisement.house"/> ${advertisement.flat.flatAddress.house}</span>
                    </li>
                </ul>
            </div>
            <div class="flat__element">
                <fmt:message key="advertisement.flat"/>
                <ul>
                    <li><span><fmt:message
                            key="advertisement.rooms"/> ${advertisement.flat.flatDescription.rooms}</span></li>
                    <li><span><fmt:message
                            key="advertisement.area"/> ${advertisement.flat.flatDescription.livingArea}</span></li>
                </ul>
            </div>
        </div>
        <div class="descriptions_flags">
            <div class="flags">
                <div class="flag__text">
                    <fmt:message key="advertisement.flat_furniture"/>
                </div>
                <div class="Flag__icon">
                    <c:choose>
                        <c:when test="${advertisement.flat.flatDescription.hasFurniture == true}">
                            <img src="${pageContext.request.contextPath}/img/true.svg" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/img/false.svg" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="flags">
                <div class="flag__text">
                    <fmt:message key="advertisement.flat_children"/>
                </div>
                <div class="Flag__icon">
                    <c:choose>
                        <c:when test="${advertisement.flat.flatDescription.possibleWithChild == true}">
                            <img src="${pageContext.request.contextPath}/img/true.svg" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/img/false.svg" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="flags">
                <div class="flag__text">
                    <fmt:message key="advertisement.flat_home_appliances"/>
                </div>
                <div class="Flag__icon">
                    <c:choose>
                        <c:when test="${advertisement.flat.flatDescription.hasHomeAppliances == true}">
                            <img src="${pageContext.request.contextPath}/img/true.svg" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/img/false.svg" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="flags">
                <div class="flag__text">
                    <fmt:message key="advertisement.flat_pets"/>
                </div>
                <div class="Flag__icon">
                    <c:choose>
                        <c:when test="${advertisement.flat.flatDescription.possibleWithPets == true}">
                            <img src="${pageContext.request.contextPath}/img/true.svg" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/img/false.svg" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="flags">
                <div class="flag__text">
                    <fmt:message key="advertisement.flat_internet"/>
                </div>
                <div class="Flag__icon">
                    <c:choose>
                        <c:when test="${advertisement.flat.flatDescription.hasTheInternet == true}">
                            <img src="${pageContext.request.contextPath}/img/true.svg" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/img/false.svg" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="description__text">
            <div class="description__name">
                <fmt:message key="advertisement.flat_description"/>
            </div>
            <div class="description__text_main">
                ${advertisement.flat.flatDescription.usersDescription}
            </div>
        </div>
        <div class="main_page__data">
            <div class="data__icon">
                <img src="${pageContext.request.contextPath}/img/Calendaricon.svg" alt="">
            </div>
            <span><fmt:message key="advertisement.date_of_creation"/>
                <ctg:date-time value="${advertisement.dateOfCreation}"/></span>
        </div>
        <div class="btn-block">
            <c:if test="${(sessionScope.userRole == 'USER' || sessionScope.userRole =='ADMIN') && sessionScope.user.id ne
            sessionScope.advertisement.author.id}">
                <button class="main_page_btn btnt popup__forms">
                    <fmt:message key="advertisement.send_request_button"/>
                </button>
            </c:if>
            <c:if test="${sessionScope.userRole == 'ADMIN' || sessionScope.user.id eq sessionScope.advertisement.author.id}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="advertisementId" value="${sessionScope.advertisement.id}"/>
                    <button class="main_page_btn btnt" name="command" value="deleteAdvertisement">
                        <fmt:message key="advertisement.delete_button"/>
                    </button>
                </form>
            </c:if>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <div class="popup_container none">
            <div class="popup_content">
                <div class="main__page_block forms-date">
                    <div class="forms-date__close_icon">
                        <img src="${pageContext.request.contextPath}/img/false.svg" alt="" class="close_popup_icon">
                    </div>
                    <div class="forms-date_name">
                        <fmt:message key="advertisement.send_request_button"/>
                    </div>
                    <div class="forms-contant">
                        <div class="forms-date_input">
                            <input id="mindateinp" name="rentDate" data-range="true" data-multiple-dates-separator="-"
                                   class="datepicker-here" pattern="^([\d]{2}\.){2}[\d]{4}\-([\d]{2}\.){2}[\d]{4}$" title=""/>
                            <div class="forms-date_input__icon">
                                <img src="${pageContext.request.contextPath}/img/Calendaricon.svg" alt="">
                            </div>
                        </div>

                        <button class="forms-date_btn" name="command" value="sendARequest">
                            <fmt:message key="advertisement.send_button"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>
<script src="${pageContext.request.contextPath}/js/form.js"></script>
</body>
</html>
