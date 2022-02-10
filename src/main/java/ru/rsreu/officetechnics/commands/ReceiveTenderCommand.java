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
import java.util.ArrayList;

public class ReceiveTenderCommand extends FrontCommand {

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
        Worker worker = (Worker)session.getAttribute("worker");

        ArrayList<Device> availableDevices = deviceDAO.findAllAvailable(worker);

        request.setAttribute("devices", availableDevices);

        forward("receiveTender");
    }

    @Override
    public void send() throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        Worker worker = AppUtils.getLoginWorker(session);
        String deviceId = request.getParameter("format");
        Device device = deviceDAO.getDeviceById(deviceId);

        device.setOwnerId(0);

        deviceDAO.updateDevice(device);
        tenderDAO.createTenderForDevice(worker, device);

        redirect("profile");

    }
}
