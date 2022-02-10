package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.data.roles.Role;
import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.RoleDAO;
import ru.rsreu.officetechnics.database.dao.WorkerDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EditUserCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private WorkerDAO workerDAO;
    private RoleDAO roleDAO;

    static {
        daoFactory = DAOFactory.getInstance(DatabaseType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        workerDAO = daoFactory.getWorkerDAO();
        roleDAO = daoFactory.getRoleDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id.isEmpty()) {
            redirect("profile");
            return;
        }

        ArrayList<Role> roles = roleDAO.findAll();

        request.setAttribute("roles", roles);

        Worker worker = workerDAO.getWorkerById(id);

        if (worker == null) {
            redirect("profile");
            return;
        }

        request.setAttribute("editUser", worker);

        forward("editUser");
    }

    @Override
    public void send() throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        String roleId = request.getParameter("format");

        Worker worker = workerDAO.getWorkerById(id);

        if (worker == null) return;

        worker.setLogin(login);
        worker.setPassword(password);
        worker.setRole(roleDAO.getRoleById(roleId));
        workerDAO.updateWorker(worker);

        redirect("workers");
    }
}

