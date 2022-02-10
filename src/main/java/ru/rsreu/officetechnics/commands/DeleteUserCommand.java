package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.WorkerDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand extends FrontCommand {

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
    public void send() throws ServletException, IOException {
        String id = request.getParameter("id");

        Worker worker = workerDAO.getWorkerById(id);

        if (worker == null) return;

        workerDAO.deleteWorker(worker);
    }
}
