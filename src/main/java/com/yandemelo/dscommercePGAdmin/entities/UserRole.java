package com.yandemelo.dscommercePGAdmin.entities;

public enum UserRole {
    
    ADMIN("admin"),
    OPERATOR("operator"),
    CLIENT("client");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}