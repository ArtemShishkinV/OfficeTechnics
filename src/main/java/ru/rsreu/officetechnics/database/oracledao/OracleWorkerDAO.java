package ru.rsreu.officetechnics.database.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.officetechnics.data.roles.RoleType;
import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.database.ConnectionPool;
import ru.rsreu.officetechnics.database.dao.WorkerDAO;
import ru.rsreu.officetechnics.utils.NumericHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleWorkerDAO implements WorkerDAO {

    private static final String SELECT_ALL_WORKERS = ProjectResourcer.getInstance().getString("query.select.all.workers");
    private static final String SELECT_WORKER_BY_ID = ProjectResourcer.getInstance().getString("query.select.worker.by.id");
    private static final String SELECT_WORKER_BY_LOGIN = ProjectResourcer.getInstance().getString("query.select.worker.by.login");
    private static final String UPDATE_WORKER = ProjectResourcer.getInstance().getString("query.update.worker");
    private static final String DELETE_WORKER = ProjectResourcer.getInstance().getString("query.delete.worker");
    private static final String INSERT_WORKER = ProjectResourcer.getInstance().getString("query.insert.worker");

    @Override
    public ArrayList<Worker> findAll() {
        ArrayList<Worker> workers = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WORKERS);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                workers.add(giveWorker(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return workers;
    }

    @Override
    public Worker getWorkerById(int id) {

        Worker worker = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WORKER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                worker = giveWorker(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return worker;
    }

    @Override
    public Worker getWorkerById(String id) {
        return getWorkerById(Integer.parseInt(id));
    }

    @Override
    public Worker getWorkerByLogin(String login) {
        Worker worker = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WORKER_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                worker = giveWorker(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return worker;
    }

    @Override
    public void updateWorker(Worker worker) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WORKER);
            insertWorker(preparedStatement, worker);

            preparedStatement.setInt(6, worker.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteWorker(Worker worker) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WORKER);
            preparedStatement.setInt(1, worker.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addWorker(Worker worker) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WORKER);

            insertWorker(preparedStatement, worker);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Worker giveWorker(ResultSet resultSet) throws SQLException {
        Worker worker = new Worker();

        worker.setId(resultSet.getInt("worker_id"));
        worker.setLogin(resultSet.getString("login"));
        worker.setPassword(resultSet.getString("password"));
        worker.setRole(RoleType.getRole(resultSet.getInt("role_id")));
        worker.setBlocked(NumericHelper.convertToBool(resultSet.getInt("blocked")));
        worker.setStatusAuthorize(NumericHelper.convertToBool(resultSet.getInt("status")));

        return worker;
    }

    private void insertWorker(PreparedStatement preparedStatement, Worker worker) throws SQLException {
        preparedStatement.setString(1, worker.getLogin());
        preparedStatement.setString(2, worker.getPassword());
        preparedStatement.setInt(3, worker.getRole().getId());
        preparedStatement.setInt(4, NumericHelper.convertToInt(worker.isBlocked()));
        preparedStatement.setInt(5, NumericHelper.convertToInt(worker.isAuthorized()));
    }
}
