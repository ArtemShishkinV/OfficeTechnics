package ru.rsreu.officetechnics.database.dao;

import ru.rsreu.officetechnics.database.DatabaseType;

public abstract class DAOFactory {

    public static DAOFactory getInstance(DatabaseType type) {
        return type.getDAOFactory();
    }

    public abstract RoleDAO getRoleDAO();

    public abstract DeviceDAO getDeviceDAO();

    public abstract WorkerDAO getWorkerDAO();

    public abstract TenderDAO getTenderDAO();
}
