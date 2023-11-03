package com.gutosoethe.vo;

import com.gutosoethe.model.Role;
import com.gutosoethe.model.Usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsuarioVo implements Serializable {
    private static final long serialVersionUID = -6542627582498216112L;

    private long id;
    private String username;
    private String password;
    private Set<RoleVo> roles;

    public UsuarioVo() {
    }

    public UsuarioVo(long id, String username, String password, Set<RoleVo> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;

    }

    public UsuarioVo(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        // Inicialize um conjunto de RoleVo
        Set<RoleVo> roleVos = new HashSet<>();
        // Converta cada Role em RoleVo e adicione ao conjunto
        if (usuario.getRole() != null && !usuario.getRole().isEmpty()) {
            for (Role role : usuario.getRole()) {
                roleVos.add(new RoleVo(role));
            }
        }
        this.roles = roleVos;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleVo> getRole() {
        return roles;
    }

    public void setRole(Set<RoleVo> role) {
        this.roles = role;
    }
}
