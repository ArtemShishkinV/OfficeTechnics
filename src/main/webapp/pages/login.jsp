<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>

    <style>
        <jsp:include page="../css/style.css"/>
    </style>
</head>
<body>
<div class="header">
    <div class="container">
        <div class="header-inner header-inner-auth">
            <div class="header-title">
                <div class="header-subtitle">Авторизация</div>
            </div>
        </div>
    </div>
</div>
<div class="auth">
    <div class="container">
        <form class="auth-inner" action="login" method="post">
            <div class="auth-block">
                <div class="auth-block-login">
                    <div class="auth-login-title"> Логин:</div>
                    <div><input class="input" type="text" name="login"></div>
                </div>
                <div class="auth-block-password">
                    <div class="auth-password-title"> Пароль:</div>
                    <div><input class="input" type="password" name="password"></div>
                </div>
                <div class="auth-block-button">
                    <input class="input auth-submit" type="submit" value="Войти"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
