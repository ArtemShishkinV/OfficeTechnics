<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.rsreu.officetechnics.utils.JspHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <style>
        <jsp:include page="../css/style.css"/>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div class="users">
    <div class="container" id="users">
        <div class="users-inner">
            <div class="users-list">
                <div class="users-list-title">
                    Список пользователей
                </div>
                <c:forEach var="worker" items="${workers}">
                    <div class="users-item">
                        <div class="users-item-info">
                            <div class="users-item-info-block">
                                <div class="users-item-info-block-title">Логин</div>
                                <div class="users-item-info-block-text">${worker.getLogin()}</div>
                            </div>
                            <div class="users-item-info-block">
                                <div class="users-item-info-block-title">Роль</div>
                                <div class="users-item-info-block-text">${worker.getRole().getTitle()}</div>
                            </div>
                            <div class="users-item-info-block">
                                <div class="users-item-info-block-title">Блокировка</div>
                                <div class="users-item-info-block-text">${JspHelper.giveWorkerStatusBlock(worker.isBlocked())}</div>
                            </div>
                            <div class="users-item-info-block">
                                <div class="users-item-info-block-title">Авторизация</div>
                                <div class="users-item-info-block-text">${JspHelper.giveAuthorizationStatus(worker.isAuthorized())}</div>
                            </div>
                        </div>
                        <div class="users-item-actions">
                            <c:if test="${sessionScope.worker.getRole().equals(RoleType.MODERATOR.getRole())}">
                                <c:choose>
                                    <c:when test="${worker.isBlocked()}">
                                        <input type="button" onclick="changeBlock(${worker.getId()})"
                                               value="Разблокировать"
                                               class="users-item-actions-action">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="button" onclick="changeBlock(${worker.getId()})"
                                               value="Заблокировать"
                                               class="users-item-actions-action">
                                    </c:otherwise>
                                </c:choose>

                            </c:if>
                            <c:if test="${sessionScope.worker.getRole().equals(RoleType.ADMIN.getRole())}">
                                <a class="users-item-actions-action"
                                   href="editUser?id=${worker.getId()}">Редактировать</a>
                                <input type="button" onclick="deleteUser(${worker.getId()})" value="Удалить"
                                       class="users-item-actions-action">
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${sessionScope.worker.getRole().equals(RoleType.ADMIN.getRole())}">
                    <a href="createUser" class="users-create">
                        Создать пользователя
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>

