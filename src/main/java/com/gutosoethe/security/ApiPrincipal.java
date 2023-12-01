package com.gutosoethe.security;


import java.security.Principal;

public class ApiPrincipal implements Principal {
    private String name;
    private String[] roles;

    public ApiPrincipal(String name, String[] roles) {
        this.name = name;
        this.roles = roles;
    }

    @Override
    public String getName() {
        return null;
    }

    public String[] getRoles() {
        return roles;
    }
}
