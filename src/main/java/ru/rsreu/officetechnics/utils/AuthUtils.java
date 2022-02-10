package ru.rsreu.officetechnics.utils;

import ru.rsreu.officetechnics.config.AuthConfig;
import ru.rsreu.officetechnics.data.roles.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

public class AuthUtils {

    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = request.getPathInfo();

        Set<Role> roles = AuthConfig.getAllAppRoles();

        for (Role role : roles) {
            ArrayList<String> urlPatterns = AuthConfig.getUrlPatternsForRole(role);

            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = request.getPathInfo();

        Set<Role> allRoles = AuthConfig.getAllAppRoles();

        for (Role role : allRoles) {

            if (!request.isUserInRole(role.getTitle())) {
                continue;
            }
            ArrayList<String> urlPatterns = AuthConfig.getUrlPatternsForRole(role);

            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

}
