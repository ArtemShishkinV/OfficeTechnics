<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Заявка на ремонт</title>
    <style>
        <jsp:include page="../css/style.css"/>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div class="tender">
    <div class="container">
        <form class="tender-inner" action="receiveTender" method="post">
            <div class="tender-main">
                <div class="select">
                    <select name="format" id="format">
                        <c:choose>
                            <c:when test="${requestScope.devices.size() == 0}">
                                <option disabled selected>Нет доступных устройств</option>
                            </c:when>
                            <c:otherwise>
                                <option disabled selected>Выберите устройство:</option>
                                <c:forEach var="item" items="${requestScope.devices}">
                                    <option value="${item.getId()}">${item.getTitle()}</option>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
                <div class="tender-submit">
                    <c:choose>
                        <c:when test="${requestScope.devices.size() != 0}">
                            <input class="input input-tender" type="submit" value="Отправить"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
