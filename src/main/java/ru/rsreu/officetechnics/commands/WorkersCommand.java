package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.WorkerDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WorkersCommand extends FrontCommand {
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

        session.setAttribute("workers", workerDAO.findAll());

        forward("workers");
    }
}
