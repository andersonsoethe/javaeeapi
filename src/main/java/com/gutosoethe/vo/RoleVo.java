package com.gutosoethe.vo;

import com.gutosoethe.model.Role;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoleVo implements Serializable {

    private static final long serialVersionUID = 6652320797755954080L;

    private long id;

    private String role;

    public RoleVo(){
    }

    public RoleVo(long id, String  role) {
        this.id = id;
        this.role = role;
    }

    public RoleVo(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role convert(){return new Role(id, role);}

}

