package ru.rsreu.officetechnics.filter;

import ru.rsreu.officetechnics.data.users.Worker;
import ru.rsreu.officetechnics.utils.AppUtils;
import ru.rsreu.officetechnics.utils.AuthUtils;
import ru.rsreu.officetechnics.wrapper.WorkerRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String pathInfo = request.getPathInfo();

        Worker worker = AppUtils.getLoginWorker(request.getSession());

        if (pathInfo.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (worker != null) {
            wrapRequest = new WorkerRoleRequestWrapper(request, worker, worker.getRole());
        }

        if (AuthUtils.isSecurityPage(request)) {

            if (worker == null) {
                response.sendRedirect("/office/login");
                return;
            }

            boolean hasPermission = AuthUtils.hasPermission(wrapRequest);

            if (!hasPermission) {
                response.sendRedirect("/office/profile");
                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }
}