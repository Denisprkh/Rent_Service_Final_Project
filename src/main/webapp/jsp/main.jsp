<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>rigister</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <form action="controller" method="post">
    <div class="top-filters">
        <div class="container">
            <div class="top-filters__items">
                <input type="city" placeholder="<fmt:message key="main_page_filter.city"/>" class="city filters">
                <input type="number" placeholder="<fmt:message key="main_page_filter.rooms"/>" class="rooms filters">
                <input type="number" placeholder="<fmt:message key="main_page_filter.price"/>" class="price filters">
                <button class="search filters" type="submit" name="command" value="find">
            <fmt:message key="main_page_filter.search"/></button>
            </div>
        </div>
    </div>
</section>
<main>
    <div class="container flex">
        <div class="filters-right">
            <span><fmt:message key="main_page_filter.filter_title"/></span>
            <div class="filters-right-top__items">
                <input type="district" placeholder="<fmt:message key="main_page_filter.district"/>" class="filters">
                <input type="number" placeholder="<fmt:message key="main_page_filter.floor"/>" class="filters">
                <input type="number" placeholder="<fmt:message key="main_page_filter.living_area"/>" class="filters">
                <select class="filters filters_select">
                    <option><fmt:message key="main_page_filter.any_repair"/></option>
                    <option><fmt:message key="main_page_filter.european_quality"/></option>
                    <option><fmt:message key="main_page_filter.redecorating"/></option>
                    <option><fmt:message key="main_page_filter.design_repair"/></option>
                    <option><fmt:message key="main_page_filter.without_repair"/></option>
                </select>
            </div>
            <div class="filters-right-bottom__items">
                <label class="filters-right-bottom__item">
                    <input type="checkbox">
                    <span><fmt:message key="main_page_filter.furniture"/></span>
                </label>
                <label class="filters-right-bottom__item">
                    <input type="checkbox">
                    <span><fmt:message key="main_page_filter.home_appliances"/></span>
                </label>
                <label class="filters-right-bottom__item">
                    <input type="checkbox">
                    <span><fmt:message key="main_page_filter.children"/></span>
                </label>
                <label class="filters-right-bottom__item">
                    <input type="checkbox">
                    <span><fmt:message key="main_page_filter.pets"/></span>
                </label>
            </div>
        </div>
        </form>
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
        </div>
    </div>
    </main>
</div>
</body>
</html>
