<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание пользователя</title>
    <style>
        <jsp:include page="../css/style.css"/>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div class="user">
    <div class="container">
        <form class="user-inner" action="createUser" method="post">
            <div class="user-create">
                <div class="user-create-item">
                    <div class="user-create-item-title"> Логин:</div>
                    <div><input class="input user-create-input" type="text" name="login"></div>
                </div>
                <div class="user-create-item">
                    <div class="user-create-item-title"> Пароль:</div>
                    <div><input class="input user-create-input" type="password" name="password"></div>
                </div>
                <div class="select">
                    <select name="format" id="format">
                        <option disabled selected>Выберите роль:</option>
                        <c:forEach var="item" items="${requestScope.roles}">
                            <option value="${item.getId()}">${item.getTitle()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="user-create-item">
                    <input class="text-input user-create-submit" type="submit" value="Создать"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

