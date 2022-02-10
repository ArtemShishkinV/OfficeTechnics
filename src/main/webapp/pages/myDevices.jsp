<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мои устройства</title>

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
            <div class="devices-list myDevices-list">
                <div class="devices-list-title">
                    Список устройств
                </div>
                <c:forEach var="device" items="${requestScope.devices}">
                    <div class="devices-item myDevices-item">
                        <div class="devices-item-title myDevices-item-title">
                                ${device.getTitle()}
                        </div>
                        <a href="problemTender?id=${device.getId()}" class="devices-item-repair">Устранение проблем</a>
                        <input class="devices-item-delete" type="button" onclick="refuseTender(${device.getId()})"
                               value="Отказаться">
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
