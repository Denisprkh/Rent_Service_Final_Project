<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="reg_page.title"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/dependencyLibs/inputmask.dependencyLib.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/inputmask/inputmask.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="reg_form">
        <div class="reg_form__items">
            <div class="reg_form__title">
                <fmt:message key="reg_form.title"/>
            </div>
            <script type="text/javascript">jQuery(function($){$(".phon").mask("+375 (99) 999-99-99");});</script>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input required name="firstName" placeholder="<fmt:message key="reg_form.first_name"/>"
                       required pattern="^[a-zA-Zа-яА-Я]{2,45}$"
                       title="<fmt:message key="sign_up_form.invalid_first_name_format"/>"/>
                <input required name="lastName" placeholder="<fmt:message key="reg_form.last_name"/>"
                       required pattern="^[a-zA-Zа-яА-Я]{2,45}$"
                       title="<fmt:message key="sign_up_form.invalid_last_name_format"/>"
                        <fmt:message key="sign_up_form.invalid_last_name_format"/>/>
                <input required name="email" placeholder="<fmt:message key="reg_form.email"/>" required
                       pattern="[a-zA-z0-9_.-]{1,40}@[a-zA-z0-9_-]{2,40}\.[a-z]{2,10}"
                       title="<fmt:message key="sign_up_form.invalid_email_format"/>"/>
                <input required name="password" id="password"  type="password"
                       placeholder="<fmt:message key="reg_form.password"/>"/>
                <input required name="passwordTwo" id="password_confirm" type="password" placeholder="<fmt:message
                key="reg_form.confirm_password"/>"/>
                <input type ="phone" required name="phone" placeholder="<fmt:message key="reg_form.phone_number"/>"required
                       pattern="^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$" title="
                        <fmt:message key="sign_up_form.invalid_phone_number_format"/>"/>
                <button class="form_btn" type="submit" name="command" value="SIGN_UP">
                         <fmt:message key="reg_form.button"/>
                </button>
                <div class="sign_up__err__message">
                    <c:if test="${not empty sessionScope.incorrectDataErrorMessage}">
                        <fmt:message key="${sessionScope.incorrectDataErrorMessage}"/>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<script>
    var inputsTel = document.querySelectorAll('input[type="phone"]');

    Inputmask({
        "mask": "+375(99)999-99-99",
        showMaskOnHover: false
    }).mask(inputsTel);

</script>
