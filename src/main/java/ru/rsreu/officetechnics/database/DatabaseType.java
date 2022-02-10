package ru.rsreu.officetechnics.database;

import ru.rsreu.officetechnics.database.dao.DAOFactory;
import ru.rsreu.officetechnics.database.oracledao.OracleDAOFactory;

public enum DatabaseType {

    ORACLE {
        @Override
        public DAOFactory getDAOFactory() {
            return OracleDAOFactory.getInstance();
        }

    };

    public abstract DAOFactory getDAOFactory();

}
