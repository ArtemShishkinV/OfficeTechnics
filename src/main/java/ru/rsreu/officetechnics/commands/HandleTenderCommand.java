package ru.rsreu.officetechnics.commands;

import ru.rsreu.officetechnics.data.devices.Device;
import ru.rsreu.officetechnics.data.devices.DeviceAccess;
import ru.rsreu.officetechnics.data.devices.TenderType;
import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.dao.DeviceDAO;
import ru.rsreu.officetechnics.database.dao.TenderDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HandleTenderCommand extends FrontCommand {

    private static final DAOFactory daoFactory;

    private TenderDAO tenderDAO;
    private DeviceDAO deviceDAO;

    static {
        daoFactory = DAOFactory.getInstance(DatabaseType.ORACLE);
    }

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        tenderDAO = daoFactory.getTenderDAO();
        deviceDAO = daoFactory.getDeviceDAO();
    }

    @Override
    public void send() throws ServletException, IOException {
        String id = request.getParameter("tenderId");
        String type = request.getParameter("type");

        DeviceAccess deviceAccess = tenderDAO.getDeviceTender(id);

        if (deviceAccess == null) return;

        Device device = deviceAccess.getDevice();
        device.setOwnerId(deviceAccess.getWorker().getId());
        System.out.println(device);
        deviceAccess.setTenderType(TenderType.getType(Integer.parseInt(type)));

        deviceDAO.updateDevice(device);
        tenderDAO.updateDeviceTender(deviceAccess);
    }
}
