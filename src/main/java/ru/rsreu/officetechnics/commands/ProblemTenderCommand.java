package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.data.devices.Device;
import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.DeviceDAO;
import ru.rsreu.officetechnics.database.dao.TenderDAO;
import ru.rsreu.officetechnics.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProblemTenderCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private DeviceDAO deviceDAO;
    private TenderDAO tenderDAO;

    static {
        daoFactory = DAOFactory.getInstance(DatabaseType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        deviceDAO = daoFactory.getDeviceDAO();
        tenderDAO = daoFactory.getTenderDAO();
    }

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String deviceId = request.getParameter("id");

        Device device = deviceDAO.getDeviceById(deviceId);

        if (device == null) return;

        session.setAttribute("device", device);

        forward("problemTender");
    }

    @Override
    public void send() throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String text = request.getParameter("text");
        Worker worker = AppUtils.getLoginWorker(session);
        Device device = deviceDAO.getDeviceById(request.getParameter("id"));

        tenderDAO.deleteDeviceTender(device);
        tenderDAO.createProblemTender(worker, device, text);

        redirect("profile");
    }
}
