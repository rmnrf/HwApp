package com.netcracker.hwapp.enums;

public enum Permission {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write"),
    STUDENT_PERM("student:perms"),
    TEACHER_PERM("teacher:perms");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
