package ru.rsreu.officetechnics.wrapper;

import ru.rsreu.officetechnics.data.roles.Role;
import ru.rsreu.officetechnics.data.users.Worker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class WorkerRoleRequestWrapper extends HttpServletRequestWrapper {

    private final Worker worker;
    private final Role role;
    private final HttpServletRequest request;

    public WorkerRoleRequestWrapper(HttpServletRequest request, Worker worker, Role role) {
        super(request);
        this.worker = worker;
        this.role = role;
        this.request = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (role == null) {
            return this.request.isUserInRole(null);
        }

        return this.role.getTitle().equalsIgnoreCase(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.worker == null) {
            return this.request.getUserPrincipal();
        }

        return worker::getLogin;
    }
}
