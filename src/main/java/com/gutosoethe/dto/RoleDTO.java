package com.gutosoethe.dto;

import com.gutosoethe.vo.RoleVo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class RoleDTO implements Serializable {

    private long id;

    @NotNull(message = "O campo role não pode ser nulo")
    @NotBlank(message = "O campo role não pode estar em branco")
    private String role;

    public RoleDTO(){
    }

    public RoleDTO(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleDTO(RoleVo roleVo) {
        this.id = roleVo.getId();
        this.role = roleVo.getRole();

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

    public static List<RoleDTO> convert(List<RoleVo> roleVoList){
        return roleVoList.stream().map(RoleDTO::new).collect(Collectors.toList());
    }

    public static RoleDTO convert(RoleVo roleVo){
        return new RoleDTO(roleVo);
    }

    public RoleVo convert(){
        return new RoleVo(id, role);
    }
}

