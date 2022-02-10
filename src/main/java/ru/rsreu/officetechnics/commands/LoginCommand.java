package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.WorkerDAO;
import ru.rsreu.officetechnics.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private WorkerDAO workerDAO;

    static {
        daoFactory = DAOFactory.getInstance(DatabaseType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        workerDAO = daoFactory.getWorkerDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        Worker worker = AppUtils.getLoginWorker(session);

        if (worker != null) {
            redirect("profile");
        } else {
            forward("login");
        }
    }

    @Override
    public void send() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");


        Worker worker = this.workerDAO.getWorkerByLogin(login);

        if (worker == null || !worker.getPassword().equals(password) || worker.isBlocked()) {
            forward("login");
        } else {
            HttpSession session = request.getSession();

            worker.setStatusAuthorize(true);
            workerDAO.updateWorker(worker);

            AppUtils.storeLoginWorker(session, worker);

            redirect("profile");
        }
    }

}
