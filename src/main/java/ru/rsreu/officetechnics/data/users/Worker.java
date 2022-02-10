package ru.rsreu.officetechnics.data.users;

import ru.rsreu.officetechnics.data.Entity;
import ru.rsreu.officetechnics.data.roles.Role;

import java.util.Objects;

public class Worker extends Entity {

    private String login;
    private String password;
    private Role role;
    private boolean blocked;
    private boolean status;

    public Worker() {

    }

    public Worker(int id, String login, String password, Role role, boolean blocked, boolean status) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
        this.status = status;
    }

    public Worker(String login, String password, Role role, boolean blocked, boolean status) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAuthorized() {
        return status;
    }

    public void setStatusAuthorize(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        if (!super.equals(o)) return false;
        Worker worker = (Worker) o;
        return blocked == worker.blocked && status == worker.status && Objects.equals(login, worker.login) && Objects.equals(password, worker.password) && Objects.equals(role, worker.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, role, blocked, status);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                ", status=" + status +
                '}';
    }
}
