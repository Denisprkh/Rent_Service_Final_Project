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
    <form action="${pageContext.request.contextPath}/controller" method="post">
    <div class="top-filters">
        <div class="container">
            <div class="top-filters__items">
                <input type="city" name="filterCity" placeholder="<fmt:message key="main_page_filter.city"/>" class="city filters">
                <input type="number" min="1" name="filterRooms" placeholder="<fmt:message key="main_page_filter.rooms"/>" class="rooms filters">
                <input type="hidden" name="newSearch" value="" id="newSearch"/>
                <input type="hidden" name="currentPage" value="1">
                <input type="number" min="1" name="filterPrice" placeholder="<fmt:message key="main_page_filter.price"/>" class="price filters">
                <button class="search filters" id="filterBtn"type="submit" name="command" value="FIND_ADVERTISEMENTS_BY_FILTER">
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
                <input type="district" name="filterDistrict" placeholder="<fmt:message key="main_page_filter.district"/>" class="filters"/>
                <input type="text"  name="filterStreet" placeholder="<fmt:message key="main_page_filter.floor"/>" class="filters"/>
                <input type="number" name="filterLivingArea" placeholder="<fmt:message key="main_page_filter.living_area"/>" class="filters"/>
            </div>
            <div class="filters-right-bottom__items">
                <label class="filters-right-bottom__item">
                    <input type="checkbox" name="filterIsHasFurniture" value="true"/>
                    <span><fmt:message key="main_page_filter.furniture"/></span>
                </label>
                <label class="filters-right-bottom__item">
                    <input type="checkbox" name="filterIsHasHomeAppliances" value="true"/>
                    <span><fmt:message key="main_page_filter.home_appliances"/></span>
                </label>
                <label class="filters-right-bottom__item">
                    <input type="checkbox" name="filterPossibleWithChildren" value="true"/>
                    <span><fmt:message key="main_page_filter.children"/></span>
                </label>
                <label class="filters-right-bottom__item">
                    <input type="checkbox" name="filterPossibleWithPets" value="true"/>
                    <span><fmt:message key="main_page_filter.pets"/></span>
                </label>
            </div>
        </div>
        </form>
        <div class="list-flat__items">
            <c:if test="${empty sessionScope.advertisementList}">
                <div class="nothing_found-message">
                    <fmt:message key="main_page.nothing_found_message"/>
                </div>
            </c:if>
        <c:forEach var="elem" items="${advertisementList}">
            <a href="${pageContext.request.contextPath}/controller?command=advertisement_page&advertisementId=${elem.getId()}">
            <div class="list-flat__item">
                <img src="data:image/jpg;base64,${elem.flat.flatPhotos.get(0).base64PhotoData}" alt="flat_one" class="List-flat__img">
                <div class="list-flat__text">
                    <div class="text-top">
                        ${elem.title}  ${elem.id}
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
                        ${elem.getPrice()}$
                    </div>
                </div>
            </div>
            </a>
            </c:forEach>
            <div class="card-page">
                <c:if test="${currentPage != 1 && pagesQuantity > 0}">
                <div class="prevision_page bgc-page">
                    <div class="icon">
                        <img src="${pageContext.request.contextPath}/img/arrow2.svg" alt="">
                    </div>
                    <div class="prev-text">
                        <c:choose>
                            <c:when test="${not empty sessionScope.advertisementFilter}">
                                <a href="controller?command=FIND_ADVERTISEMENTS_BY_FILTER&currentPage=${currentPage-1}">Previous page</a>
                            </c:when>
                            <c:otherwise>
                                <a href="controller?command=FIND_ALL_ADVERTISEMENTS&currentPage=${currentPage-1}">Prevision page</a>">Next page</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                </c:if>


                <div class="number-page">
                    <c:if test="${pagesQuantity ne 1}">
                        ${currentPage}
                    </c:if>
                </div>
                <c:if test="${currentPage ne pagesQuantity && pagesQuantity > 0}">
                <div class="next-page bgc-page">
                    <div class="next-page-text">
                        <c:choose>
                            <c:when test="${not empty sessionScope.advertisementFilter}">
                                <a href="controller?command=FIND_ADVERTISEMENTS_BY_FILTER&currentPage=${currentPage+1}">Next page</a>
                            </c:when>
                            <c:otherwise>
                                <a href="controller?command=FIND_ALL_ADVERTISEMENTS&currentPage=${currentPage+1}">Next page</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="icon">
                        <img src="${pageContext.request.contextPath}img/arrow1.svg" alt="">
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

<script>
    var filterBtn = document.querySelector("#filterBtn")
    var newSearchInput = document.querySelector('#newSearch')
    filterBtn.addEventListener('click',() => { newSearchInput.value='newSearchInput'})
    console.log(filterBtn)
</script>