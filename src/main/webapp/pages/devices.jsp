<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Устройства</title>

    <style>
        <jsp:include page="../css/style.css"/>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div class="devices">
    <div class="container" id="devices">
        <div class="devices-inner">
            <div class="devices-list">
                <div class="devices-list-title">
                    Список устройств
                </div>
                <c:forEach var="device" items="${requestScope.devices}">
                    <div class="devices-item">
                        <div class="devices-item-title">
                                ${device.getTitle()}
                        </div>
                        <input class="devices-item-delete" type="button" onclick="deleteDevice(${device.getId()})"
                               value="Удалить">
                    </div>
                </c:forEach>
                <div class="devices-item">
                    <input type="text" id="deviceTitle" class="input devices-create"
                           placeholder="Введите название устройства">
                    <input class="devices-item-delete" type="button"
                           onclick="createDevice()"
                           value="Создать">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
