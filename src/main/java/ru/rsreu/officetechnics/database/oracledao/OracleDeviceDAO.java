package ru.rsreu.officetechnics.database.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.officetechnics.data.devices.Device;
import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.ConnectionPool;
import ru.rsreu.officetechnics.database.dao.DeviceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleDeviceDAO implements DeviceDAO {

    private static final String SELECT_ALL_DEVICES = ProjectResourcer.getInstance().getString("query.select.all.devices");
    private static final String SELECT_ALL_AVAILABLE_DEVICES = ProjectResourcer.getInstance().getString("query.select.all.available");
    private static final String SELECT_DEVICE_BY_ID = ProjectResourcer.getInstance().getString("query.select.device.by.id");
    private static final String SELECT_ALL_DEVICES_BY_OWNER = ProjectResourcer.getInstance().getString("query.select.all.devices.by.owner");
    private static final String UPDATE_DEVICE = ProjectResourcer.getInstance().getString("query.update.device");
    private static final String DELETE_DEVICE = ProjectResourcer.getInstance().getString("query.delete.device");
    private static final String CREATE_DEVICE = ProjectResourcer.getInstance().getString("query.create.device");

    @Override
    public ArrayList<Device> findAll() {
        ArrayList<Device> devices = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEVICES);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                devices.add(giveDevice(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return devices;
    }

    @Override
    public ArrayList<Device> findAllAvailable(Worker worker) {
        ArrayList<Device> devices = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AVAILABLE_DEVICES);
            preparedStatement.setInt(1, worker.getId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                devices.add(getDeviceById(rs.getInt(1)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return devices;
    }



    @Override
    public ArrayList<Device> findAllByWorker(Worker worker) {
        ArrayList<Device> devices = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEVICES_BY_OWNER);
            preparedStatement.setInt(1, worker.getId());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                devices.add(giveDevice(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return devices;
    }


    @Override
    public Device getDeviceById(int id) {
        Device device = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVICE_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                device = giveDevice(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return device;
    }

    @Override
    public Device getDeviceById(String id) {
        return getDeviceById(Integer.parseInt(id));
    }

    @Override
    public void updateDevice(Device device) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVICE);
            preparedStatement.setString(1, device.getTitle());
            preparedStatement.setInt(2, device.getOwnerId());
            preparedStatement.setInt(3, device.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteDevice(Device device) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEVICE);
            preparedStatement.setInt(1, device.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createDevice(Device device) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DEVICE);
            preparedStatement.setString(1, device.getTitle());
            preparedStatement.setInt(2, device.getOwnerId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Device giveDevice(ResultSet resultSet) throws SQLException {
        Device device = new Device();

        device.setId(resultSet.getInt(1));
        device.setTitle(resultSet.getString(2));
        device.setOwnerId(resultSet.getInt(3));

        return device;
    }
}
