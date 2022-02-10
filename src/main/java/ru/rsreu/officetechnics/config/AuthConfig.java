package ru.rsreu.officetechnics.config;

import ru.rsreu.officetechnics.data.roles.Role;
import ru.rsreu.officetechnics.data.roles.RoleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthConfig {

    private static final Map<Role, ArrayList<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        ArrayList<String> userUrlPatterns = new ArrayList<>();
        userUrlPatterns.add("/profile");
        userUrlPatterns.add("/myDevices");
        userUrlPatterns.add("/receiveTender");
        userUrlPatterns.add("/problemTender");

        mapConfig.put(RoleType.WORKER.getRole(), userUrlPatterns);

        ArrayList<String> sysAdminUrlPatterns = new ArrayList<>();
        sysAdminUrlPatterns.add("/profile");
        sysAdminUrlPatterns.add("/devices");
        sysAdminUrlPatterns.add("/createDevice");
        sysAdminUrlPatterns.add("/deleteDevice");
        sysAdminUrlPatterns.add("/handleTender");
        sysAdminUrlPatterns.add("/handleProblem");
        sysAdminUrlPatterns.add("/problemTenderList");
        sysAdminUrlPatterns.add("/receiveTenderList");

        mapConfig.put(RoleType.SYS_ADMIN.getRole(), sysAdminUrlPatterns);

        ArrayList<String> moderatorUrlPatterns = new ArrayList<>();
        moderatorUrlPatterns.add("/profile");
        moderatorUrlPatterns.add("/workers");
        moderatorUrlPatterns.add("/problemTenderList");
        moderatorUrlPatterns.add("/receiveTenderList");
        moderatorUrlPatterns.add("/changeBlock");
        moderatorUrlPatterns.add("/deleteProblem");
        moderatorUrlPatterns.add("/deleteTender");

        mapConfig.put(RoleType.MODERATOR.getRole(), moderatorUrlPatterns);

        ArrayList<String> adminUrlPatterns = new ArrayList<>();
        adminUrlPatterns.add("/profile");
        adminUrlPatterns.add("/workers");
        adminUrlPatterns.add("/editUser");
        adminUrlPatterns.add("/deleteUser");

        mapConfig.put(RoleType.ADMIN.getRole(), adminUrlPatterns);
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static ArrayList<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }
}
