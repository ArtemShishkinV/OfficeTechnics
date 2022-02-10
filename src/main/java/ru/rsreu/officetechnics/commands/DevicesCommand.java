package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.DeviceDAO;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.data.devices.Device;
import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class DevicesCommand extends FrontCommand{
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
    public void process() throws ServletException, IOException {
        ArrayList<Device> devices = deviceDAO.findAll();

        request.setAttribute("devices", devices);

        forward("devices");
    }
}
