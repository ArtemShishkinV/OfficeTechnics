package ru.rsreu.officetechnics.database.oracledao;

import ru.rsreu.officetechnics.database.dao.*;

public class OracleDAOFactory extends DAOFactory {

    private static volatile OracleDAOFactory instance;

    private OracleDAOFactory() {

    }

    public static OracleDAOFactory getInstance() {
        if (instance == null) {
            synchronized (OracleDAOFactory.class) {
                instance = new OracleDAOFactory();
            }
        }
        return instance;
    }


    @Override
    public RoleDAO getRoleDAO() {
        return new OracleRoleDAO();
    }

    @Override
    public DeviceDAO getDeviceDAO() {
        return new OracleDeviceDAO();
    }

    @Override
    public WorkerDAO getWorkerDAO() {
        return new OracleWorkerDAO();
    }

    @Override
    public TenderDAO getTenderDAO() {
        return new OracleTenderDAO();
    }

}
