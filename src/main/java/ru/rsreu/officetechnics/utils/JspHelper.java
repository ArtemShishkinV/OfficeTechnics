package ru.rsreu.officetechnics.utils;

public class JspHelper {
    public static String giveWorkerStatusBlock(boolean blocked) {
        return blocked ? "Заблокирован" : "Разблокирован";
    }

    public static String giveAuthorizationStatus(boolean authorization) {
        if (authorization) {
            return "Авторизованный";
        } else {
            return "Неавторизованный";
        }
    }

}