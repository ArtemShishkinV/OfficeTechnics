package ru.rsreu.officetechnics.database.dao;

import ru.rsreu.officetechnics.data.roles.Role;

import java.util.ArrayList;

public interface RoleDAO {

    ArrayList<Role> findAll();

    Role getRoleById(int id);

    Role getRoleById(String id);

}
