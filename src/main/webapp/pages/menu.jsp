<%@ page import="ru.rsreu.officetechnics.data.users.Worker" %>
<%@ page import="ru.rsreu.officetechnics.data.roles.RoleType" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="menu">
    <div class="container">
        <div class="menu-inner">
            <%
                Worker worker = (Worker) session.getAttribute("worker");
            %>
            <c:choose>
                <c:when test="${worker.getRole().equals(RoleType.WORKER.getRole())}">
                    <a href="myDevices" class="menu-item">Мои устройства</a>
                    <a href="receiveTender" class="menu-item">Заявка на получение</a>
                </c:when>
                <c:when test="${worker.getRole().equals(RoleType.SYS_ADMIN.getRole())}">
                    <a href="devices" class="menu-item">Список техники</a>
                    <a href="problemTenderList" class="menu-item">Заявки на ремонт</a>
                    <a href="receiveTenderList" class="menu-item">Заявки на получение</a
                </c:when>
                <c:when test="${worker.getRole().equals(RoleType.MODERATOR.getRole())}">
                    <a href="workers" class="menu-item">Список пользователей</a>
                    <a href="problemTenderList" class="menu-item">Заявки на ремонт</a>
                    <a href="receiveTenderList" class="menu-item">Заявки на получение</a
                </c:when>
                <c:when test="${worker.getRole().equals(RoleType.ADMIN.getRole())}">
                    <a href="workers" class="menu-item">Список пользователей</a>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>