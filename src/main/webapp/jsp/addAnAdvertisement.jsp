<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent"/>

<html>
<head>
    <title><fmt:message key="add_an_advertisement.title"/></title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="adv">
    <div class="container">
        <div class="adv__handler__bckgrnd">
            <div class="adv__items">
                <p class="adv__title">
                    <fmt:message key="add_an_advertisement.form_title"/>
                </p>
                <form action="${pageContext.request.contextPath}/UploadController" enctype="multipart/form-data"
                      method="post">
                    <div class="row__items-title">
                        <p><fmt:message key="add_an_advertisement.advertisement_description"/></p>
                    </div>
                    <div class="row_items-input">
                        <input type="text" class="input__item first" name="title" placeholder=
                                "<fmt:message key="add_an_advertisement.advertisement_title"/>" required
                               pattern="^[a-zA-Zа-яА-Я\s]{2,45}$" title="<fmt:message key="add_an_advertisement.incorrect_title"/>"/>
                        <input type="text" class="input__item" name="price" placeholder=
                                "<fmt:message key="add_an_advertisement.price"/>" required pattern="^[0-9.]{1,45}$"
                        title="<fmt:message key="add_an_advertisement.incorrect_price"/>"/>

                    </div>
                    <div class="row__items-title">
                        <p><fmt:message key="add_an_advertisement.location_title"/></p>
                    </div>
                    <div class="row_items-input">
                        <input type="text" class="input__item first" name="city" placeholder=
                                "<fmt:message key="add_an_advertisement.location_city"/>" required
                               pattern="^[a-zA-Zа-яА-Я\s.-]{2,45}$" title="<fmt:message key="add_an_advertisement.incorrect_city"/>"/>
                        <input type="text" class="input__item" name="district" placeholder=
                                "<fmt:message key="add_an_advertisement.location_district"/>" required
                               pattern="^[a-zA-Zа-яА-Я\s.-]{2,45}$" title="<fmt:message key="add_an_advertisement.incorrect_district"/> "/>
                        <input type="text" class="input__item" name="street" placeholder=
                                "<fmt:message key="add_an_advertisement.location_street"/>" required
                               pattern="^[a-zA-Zа-яА-Я\s.-]{2,45}$" title="<fmt:message key="add_an_advertisement.incorrect_street"/> "/>
                        <input type="text" class="input__item end" name="house" placeholder=
                                "<fmt:message key="add_an_advertisement.location_house"/>" required
                               pattern="^\d+[a-zа-я\/\d]{0,10}$" title="<fmt:message key="add_an_advertisement.incorrect_house"/> "/>
                    </div>
                    <div class="row__items-title">
                        <p><fmt:message key="add_an_advertisement.flat_description_title"/></p>
                    </div>
                    <div class="row_items-input">
                        <input type="text" class="input__item first" name="rooms" placeholder=
                                "<fmt:message key="add_an_advertisement.flat_description_rooms"/>" required
                               pattern="^[0-9]{1,10}$" title="<fmt:message key="add_an_advertisement.incorrect_rooms"/> "/>
                        <input type="text" class="input__item" name="area" placeholder=
                                "<fmt:message key="add_an_advertisement.flat_description_area"/>" required
                               pattern="^[0-9.]{1,45}$" title="<fmt:message key="add_an_advertisement.incorrect_living_area"/>"/>
                    </div>

                    <div class="check-box__items">
                        <label class="check-box__item first">
                            <input type="checkbox" name="furniture" value="true"/>
                            <span class="check-box__title"><fmt:message
                                    key="add_an_advertisement.flat_description_furniture"/></span>
                        </label>
                        <label class="check-box__item">
                            <input type="checkbox" name="children" value="true"/>
                            <span class="check-box__title">
                            <fmt:message key="add_an_advertisement.flat_description_children"/></span>
                        </label>
                        <label class="check-box__item">
                            <input type="checkbox" name="homeAppliances" value="true"/>
                            <span><fmt:message key="add_an_advertisement.flat_description_home_appliances"/></span>
                        </label>
                        <label class="check-box__item">
                            <input type="checkbox" name="pets" value="true"/>
                            <span><fmt:message key="add_an_advertisement.flat_description_pets"/></span>
                        </label>
                        <label class="check-box__item">
                            <input type="checkbox" name="internet" value="true"/>
                            <span><fmt:message key="add_an_advertisement.flat_description_internet"/></span>
                        </label>
                    </div>
                    <div class="row__items-title">
                        <p><fmt:message key="add_an_advertisement.add_pictures_title"/></p>
                    </div>
                    <div class="row_items-input">
                        <label>
                            <img class="output__img none" src="" alt="">
                            <input class="input__items-file dbf" type="file" name="firstImg" id="id_files"
                                   accept="image/*" required/>
                            <span class="input_img_plus">+</span>
                        </label>
                        <label>
                            <img class="output__img none" src="" alt="">
                            <input class="input__items-file dbf" type="file" name="secondImg" id="id_files"
                                   accept="image/*" required/>
                            <span class="input_img_plus">+</span>
                        </label>
                        <label>
                            <img class="output__img none" src="" alt="">
                            <input class="input__items-file dbf" type="file" name="thirdImg" id="id_files"
                                   accept="image/*" required/>
                            <span class="input_img_plus">+</span>
                        </label>
                        <div class="img_info_text">
                            <fmt:message key="add_an_advertisement.img_amount_info"/>
                        </div>
                    </div>

                    <div class="row__items-title">
                        <p><fmt:message key="add_an_advertisement.give_more_info_title"/></p>
                    </div>

                    <div class="usrs__input">
                        <textarea class="usrs__input__item" rows="10" cols="45" name="usersDescription" placeholder=
                                "<fmt:message key="add_an_advertisement.give_more_info_text"/>"></textarea>
                    </div>

                    <button class="add__adv__btn" type="submit" name="command" value="createNewAdvertisement">
                        <fmt:message key="add_an_advertisement.button"/></button>
                </form>
                <div class="err__message">
                    <c:if test="${not empty sessionScope.addAdvertisementErrorMessage}">
                        <fmt:message key="${sessionScope.addAdvertisementErrorMessage}"/>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/js/xssProtection.js"></script>
<script src="${pageContext.request.contextPath}/js/advertisement.js"></script>
</body>
</html>
