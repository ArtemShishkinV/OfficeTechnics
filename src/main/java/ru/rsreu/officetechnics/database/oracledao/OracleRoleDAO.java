package ru.rsreu.officetechnics.database.oracledao;

import com.prutzkow.resourcer.ProjectResourcer;
import ru.rsreu.officetechnics.data.roles.Role;
import ru.rsreu.officetechnics.database.ConnectionPool;
import ru.rsreu.officetechnics.database.dao.RoleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OracleRoleDAO implements RoleDAO {

    private static final String SELECT_ALL_ROLES = ProjectResourcer.getInstance().getString("query.select.all.roles");
    private static final String SELECT_ROLE_BY_ID = ProjectResourcer.getInstance().getString("query.select.role.by.id");

    @Override
    public ArrayList<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                roles.add(new Role(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role getRoleById(int id) {

        Role role = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                role = new Role(id, rs.getString(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return role;
    }

    @Override
    public Role getRoleById(String id) {
        return getRoleById(Integer.parseInt(id));
    }
}
