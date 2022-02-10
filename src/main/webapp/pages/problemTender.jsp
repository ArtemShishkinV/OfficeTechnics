<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание заявки</title>
    <style>
        <jsp:include page="../css/style.css"/>
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div class="create">
    <div class="container">
        <form class="create-inner" action="problemTender?id=${device.getId()}" method="post">
            <div class="create-item">
                <div class="create-item-title"> Название устройства:</div>
                <div><input class="input create-input" type="text" name="title"
                            value="${device.getTitle()}" disabled/></div>
            </div>
            <div class="create-item">
                <div class="create-item-title"> Текст проблемы:</div>
                <div><textarea class="input create-input" name="text" cols="30" rows="10"></textarea></div>
            </div>
            <div>
                <input class="input create-submit" type="submit" value="Отправить"/>
            </div>
        </form>
    </div>
</div>

</body>
</html>
