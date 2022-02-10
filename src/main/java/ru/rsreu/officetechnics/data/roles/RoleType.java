package ru.rsreu.officetechnics.data.roles;

import ru.rsreu.officetechnics.database.DatabaseType;
import ru.rsreu.officetechnics.database.dao.DAOFactory;

public enum RoleType {

    WORKER(1),
    SYS_ADMIN(2),
    MODERATOR(3),
    ADMIN(4);

    private final int id;

    RoleType(int id) {
        this.id = id;
    }

    public Role getRole() {
        return getRole(id);
    }

    public static Role getRole(int id) {
        return DAOFactory.getInstance(DatabaseType.ORACLE).getRoleDAO().getRoleById(id);
    }
}
