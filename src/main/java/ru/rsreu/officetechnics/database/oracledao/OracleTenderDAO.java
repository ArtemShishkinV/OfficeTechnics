package ru.rsreu.officetechnics.database.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.officetechnics.data.devices.*;
import ru.rsreu.officetechnics.data.roles.RoleType;
import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.ConnectionPool;
import ru.rsreu.officetechnics.database.dao.TenderDAO;
import ru.rsreu.officetechnics.utils.NumericHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleTenderDAO implements TenderDAO {

    private static final String INSERT_TENDER = ProjectResourcer.getInstance().getString("query.insert.tender");
    private static final String INSERT_PROBLEM_TENDER = ProjectResourcer.getInstance().getString("query.insert.problem.tender");
    private static final String DELETE_DEVICE_TENDER_BY_DEVICE = ProjectResourcer.getInstance().getString("query.delete.device.tender.by.device");
    private static final String FIND_RECEIVING_TENDERS = ProjectResourcer.getInstance().getString("query.find.receiving.tenders");
    private static final String FIND_PROBLEM_TENDERS = ProjectResourcer.getInstance().getString("query.find.problem.tenders");
    private static final String SELECT_PROBLEM_TENDER = ProjectResourcer.getInstance().getString("query.select.problem.tender");
    private static final String UPDATE_PROBLEM_TENDER = ProjectResourcer.getInstance().getString("query.update.problem.tender");
    private static final String SELECT_DEVICE_TENDER = ProjectResourcer.getInstance().getString("query.select.device.tender");
    private static final String UPDATE_DEVICE_TENDER = ProjectResourcer.getInstance().getString("query.update.device.tender");
    private static final String DELETE_PROBLEM_TENDER = ProjectResourcer.getInstance().getString("query.delete.problem.tender");
    private static final String DELETE_DEVICE_TENDER = ProjectResourcer.getInstance().getString("query.delete.device.tender");

    @Override
    public ArrayList<DeviceProblem> findProblemTenders() {
        ArrayList<DeviceProblem> deviceProblems = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PROBLEM_TENDERS);
            preparedStatement.setInt(1, 0);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                deviceProblems.add(giveDeviceProblem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deviceProblems;
    }

    @Override
    public ArrayList<DeviceAccess> findReceivingTenders() {
        ArrayList<DeviceAccess> deviceAccesses = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RECEIVING_TENDERS);
            preparedStatement.setInt(1, 0);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                deviceAccesses.add(giveDeviceAccess(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deviceAccesses;
    }

    @Override
    public DeviceProblem getDeviceProblem(int id) {
        DeviceProblem deviceProblem = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROBLEM_TENDER);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                deviceProblem = giveDeviceProblem(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return deviceProblem;
    }

    @Override
    public DeviceProblem getDeviceProblem(String id) {
        return getDeviceProblem(Integer.parseInt(id));
    }

    @Override
    public DeviceAccess getDeviceTender(int id) {
        DeviceAccess deviceAccess = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVICE_TENDER);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                deviceAccess = giveDeviceAccess(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return deviceAccess;
    }

    @Override
    public DeviceAccess getDeviceTender(String id) {
        return getDeviceTender(Integer.parseInt(id));
    }

    @Override
    public void createTenderForDevice(Worker worker, Device device) {

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TENDER);

            preparedStatement.setInt(1, worker.getId());
            preparedStatement.setInt(2, device.getId());
            preparedStatement.setInt(3, TenderType.RECEIVING.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteDeviceTender(Device device) {
        deleteQuery(DELETE_DEVICE_TENDER_BY_DEVICE, device.getId());
    }

    @Override
    public void deleteDeviceTender(DeviceAccess deviceAccess) {
        deleteQuery(DELETE_DEVICE_TENDER, deviceAccess.getId());
    }

    @Override
    public void createProblemTender(Worker worker, Device device, String text) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROBLEM_TENDER);

            preparedStatement.setInt(1, worker.getId());
            preparedStatement.setInt(2, device.getId());
            preparedStatement.setString(3, text);
            preparedStatement.setInt(4, ProblemType.PROCESSING.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateDeviceProblem(DeviceProblem deviceProblem) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROBLEM_TENDER);

            preparedStatement.setInt(1, deviceProblem.getProblemType().getId());
            preparedStatement.setInt(2, deviceProblem.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateDeviceTender(DeviceAccess deviceAccess) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEVICE_TENDER);

            preparedStatement.setInt(1, deviceAccess.getTenderType().getId());
            preparedStatement.setInt(2, deviceAccess.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteProblemTender(DeviceProblem deviceProblem) {
        deleteQuery(DELETE_PROBLEM_TENDER, deviceProblem.getId());
    }

    private void deleteQuery(String query, int id) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private DeviceProblem giveDeviceProblem(ResultSet rs) throws SQLException {
        DeviceProblem deviceProblem = new DeviceProblem();

        deviceProblem.setId(rs.getInt("problem_id"));
        deviceProblem.setWorker(giveWorker(rs));
        deviceProblem.setDevice(giveDevice(rs));
        deviceProblem.setProblemType(ProblemType.getType(rs.getInt("status")));
        deviceProblem.setText(rs.getString("text"));

        return deviceProblem;
    }

    private DeviceAccess giveDeviceAccess(ResultSet rs) throws SQLException {
        DeviceAccess deviceAccess = new DeviceAccess();

        deviceAccess.setId(rs.getInt("access_id"));
        deviceAccess.setWorker(giveWorker(rs));
        deviceAccess.setDevice(giveDevice(rs));
        deviceAccess.setTenderType(TenderType.getType(rs.getInt("status")));

        return deviceAccess;
    }

    private Worker giveWorker(ResultSet rs) throws SQLException {
        Worker worker = new Worker(rs.getInt("idd"), rs.getString("login"), rs.getString("password"),
                RoleType.getRole(rs.getInt("role_id")), NumericHelper.convertToBool(rs.getInt("blocked")),
                NumericHelper.convertToBool(rs.getInt("status")));
        System.out.println(worker.getId());
        return worker;
    }

    private Device giveDevice(ResultSet rs) throws SQLException {
        return new Device(rs.getInt("device_id"), rs.getString("title"), rs.getInt("worker_id"));
    }

}
