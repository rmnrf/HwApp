package com.netcracker.hwapp.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    STUDENT(Set.of(Permission.STUDENT_PERM)),
    TEACHER(Set.of(Permission.TEACHER_PERM)),
    ADMIN(Set.of(Permission.STUDENT_PERM, Permission.TEACHER_PERM));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
