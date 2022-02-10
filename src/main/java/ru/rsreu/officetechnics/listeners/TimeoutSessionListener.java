package ru.rsreu.officetechnics.listeners;

import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.WorkerDAO;
import ru.rsreu.officetechnics.utils.AppUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class TimeoutSessionListener implements HttpSessionListener {

    private static final int MAX_INACTIVE_INTERVAL = 30 * 60;

    private final WorkerDAO workerDAO = DAOFactory.getInstance(DatabaseType.ORACLE).getWorkerDAO();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Worker worker = AppUtils.getLoginWorker(session);

        worker.setStatusAuthorize(false);

        workerDAO.updateWorker(worker);
    }
}
