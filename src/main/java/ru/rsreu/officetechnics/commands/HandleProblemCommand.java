package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.data.devices.DeviceProblem;
import ru.rsreu.officetechnics.data.devices.ProblemType;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.TenderDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HandleProblemCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private TenderDAO tenderDAO;

    static {
        daoFactory = DAOFactory.getInstance(DatabaseType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        tenderDAO = daoFactory.getTenderDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String id = request.getParameter("problemId");
        String type = request.getParameter("type");

        DeviceProblem deviceProblem = tenderDAO.getDeviceProblem(id);

        if (deviceProblem == null) return;

        deviceProblem.setProblemType(ProblemType.getType(Integer.parseInt(type)));

        tenderDAO.updateDeviceProblem(deviceProblem);
    }
}
