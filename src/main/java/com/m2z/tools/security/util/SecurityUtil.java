package com.m2z.tools.security.util;

import com.m2z.tools.security.model.PrincipleUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SecurityUtil {

    public static String ROLE_ROOT = "ROLE_ROOT";
    public static String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String PROJECT_PREFIX = "PROJECT_";
    public static final String READ_SUFFIX = "_READ";
    public static final String WRITE_SUFFIX = "_WRITE";

    public static final String READ = "READ";
    public static final String WRITE = "WRITE";

    private static <T extends UserDetails> T transformPrinciple(Object auth, Class<T> cls) {
        if (cls.isInstance(auth)) {
            return cls.cast(auth);
        }
        throw new RuntimeException(
                String.format(
                        "Could not cast auth to %s class either wrong instance type or null",
                        cls.getName()));
    }

    public static PrincipleUser getSecurityContextUser() {
        return transformPrinciple(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                PrincipleUser.class);
    }

    public static HashMap<String, String> getProjects() {
        Collection<? extends GrantedAuthority> grantedAuthorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> authorities =
                grantedAuthorities.stream().map(GrantedAuthority::getAuthority).toList();
        List<String> projects =
                authorities.stream()
                        .filter((a) -> a.startsWith(PROJECT_PREFIX))
                        .map((a) -> a.substring(PROJECT_PREFIX.length()))
                        .toList();
        HashMap<String, String> map = new HashMap<>();
        for (String project : projects) {
            if (project.endsWith(READ_SUFFIX)) {
                map.put(project.substring(0, project.length() - READ_SUFFIX.length()), READ);
            } else if (project.endsWith(WRITE_SUFFIX)) {
                map.put(project.substring(0, project.length() - WRITE_SUFFIX.length()), WRITE);
            }
        }
        return map;
    }
}
