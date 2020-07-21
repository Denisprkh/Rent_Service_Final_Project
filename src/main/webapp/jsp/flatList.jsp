<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 17.07.2020
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="main.jsp"/>
<div class="list-flat__items">
    <c:forEach var="elem" items="${advertisementList}">
    <div class="list-flat__item">
        <img src="${pageContext.request.contextPath}/img/Rectangle 27.png" alt="flat_one" class="List-flat__img">
        <div class="list-flat__text">
            <div class="text-top">
                    ${elem.getTitle()}  ${elem.getId()}
            </div>
            <div class="list-flat__place">
                <div class="icon-place">
                    <img src="${pageContext.request.contextPath}/img/map.svg" alt="place">
                </div>
                <div class="text-place">
                        ${elem.getFlat().getFlatAddress().getCity()}, ${elem.getFlat().getFlatAddress().getDistrict()}, ${elem.getFlat().getFlatAddress().getStreet()}, ${elem.getFlat().getFlatAddress().getHouse()}
                </div>
            </div>
            <div class="text-main">
                    ${elem.getFlat().getFlatDescription().getUsersDescription()}
            </div>
            <div class="text-price">
                    ${elem.getPrice()}
            </div>
        </div>
    </div>
    </c:forEach>
    <div class="card-page">
        <c:if test="${currentPage != 1}">
            <div class="prevision_page bgc-page">
                <div class="icon">
                    <img src="img/arrow2.svg" alt="">
                </div>
                <div class="prev-text">
                    <a href="controller?command=FIND_ALL_ADVERTISEMENTS&currentPage=${currentPage-1}">Prevision page</a>
                </div>
            </div>
        </c:if>

        <div class="number-page">
            ${currentPage}
        </div>
        <c:if test="${currentPage ne pagesQuantity}">
            <div class="next-page bgc-page">
                <div class="next-page-text">
                    <a href="controller?command=FIND_ALL_ADVERTISEMENTS&currentPage=${currentPage+1}">Next page</a>
                </div>
                <div class="icon">
                    <img src="img/arrow1.svg" alt="">
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>
