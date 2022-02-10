package ru.rsreu.officetechnics.utils;

import ru.rsreu.officetechnics.data.users.Worker;

import javax.servlet.http.HttpSession;

public class AppUtils {

    public static Worker getLoginWorker(HttpSession session) {
        return (Worker) session.getAttribute("worker");
    }

    public static void storeLoginWorker(HttpSession session, Worker worker) {
        session.setAttribute("worker", worker);
    }

}
