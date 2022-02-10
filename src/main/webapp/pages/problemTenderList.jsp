<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.rsreu.officetechnics.data.devices.ProblemType" %>
<html>
<head>
    <title>Заявки на получение</title>
    <style>
        <jsp:include page="../css/style.css"/>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<div class="receiveTender">
    <div class="container" id="tenders">
        <div class="receiveTender-inner">
            <div class="receiveTender-list">
                <div class="receiveTender-list-title">
                    Заявки на ремонт
                </div>
                <c:forEach var="device" items="${requestScope.devices}">
                    <div class="receiveTender-item">
                        <div class="receiveTender-item-info">
                            <div class="receiveTender-item-info-block"><b>Работник:</b>${device.getWorker().getLogin()}
                            </div>
                            <div class="receiveTender-item-info-block">
                                <b>Устройство:</b>${device.getDevice().getTitle()}</div>
                        </div>
                        <div class="receiveTender-item-description">
                            <div class="receiveTender-item-title">
                                <b>Текст проблемы</b>
                            </div>
                            <div class="receiveTender-item-text">
                                    ${device.getText()}
                            </div>
                        </div>
                        <c:if test="${worker.getRole().equals(RoleType.SYS_ADMIN.getRole())}">
                            <div class="form_radio_group form_radio_group_receiveTender">
                                <div class="form_radio_group-item">
                                    <input id="radio-${device.hashCode()}" type="radio"
                                           name="radio-${device.getDevice().getId()}"
                                           value="${ProblemType.COMPLETED.getId()}">
                                    <label for="radio-${device.hashCode()}">${ProblemType.COMPLETED.getText()}</label>
                                </div>
                                <div class="form_radio_group-item">
                                    <input id="radio-${device.hashCode()+1}" type="radio"
                                           name="radio-${device.getDevice().getId()}"
                                           value="${ProblemType.NOT_REPAIRED.getId()}">
                                    <label for="radio-${device.hashCode()+1}">${ProblemType.NOT_REPAIRED.getText()}</label>
                                </div>
                            </div>
                            <div class="receiveTender-submit">
                                <input class="input create-submit receiveTender-input-submit"
                                       onclick="handleProblem(${device.getId()}, ${device.getDevice().getId()})"
                                       type="button"
                                       value="Ответить"/>
                            </div>
                        </c:if>
                        <c:if test="${worker.getRole().equals(RoleType.MODERATOR.getRole())}">
                            <div class="receiveTender-submit">
                                <input class="input create-submit receiveTender-input-submit"
                                       onclick="deleteProblem(${device.getId()})" type="button"
                                       value="Удалить"/>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

</body>
</html>
