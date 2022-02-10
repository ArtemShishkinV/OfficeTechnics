package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.data.devices.Device;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.DeviceDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDeviceCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private DeviceDAO deviceDAO;

    static {
        daoFactory = DAOFactory.getInstance(DatabaseType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        deviceDAO = daoFactory.getDeviceDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String deviceId = request.getParameter("id");

        Device device = deviceDAO.getDeviceById(deviceId);

        if (device == null) return;

        deviceDAO.deleteDevice(device);
    }
}
