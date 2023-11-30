package com.gutosoethe.dto;

import com.gutosoethe.vo.RoleVo;
import com.gutosoethe.vo.UsuarioVo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = -721796695039147959L;

    private long id;

    @NotNull(message = "O campo username não pode ser nulo")
    @NotBlank(message = "O campo username não pode estar em branco")
    private String username;

    @NotNull(message = "O campo password não pode ser nulo")
    @NotBlank(message = "O campo password não pode estar em branco")
    private String password;

    @NotNull(message = "O campo role não pode ser nulo")
    private List<RoleDTO> roles;

    public UsuarioDTO() {
    }

    public UsuarioDTO(long id, String username, String password, List<RoleDTO> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UsuarioDTO(UsuarioVo usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        // Inicialize um conjunto de RoleDTO
        List<RoleDTO> roleDTOs = new ArrayList<>();
        // Converta cada RoleVo em RoleDTO e adicione ao conjunto
        if (usuario.getRole() != null && !usuario.getRole().isEmpty()) {
            for (RoleVo roleVo : usuario.getRole()) {
                roleDTOs.add(new RoleDTO(roleVo));
            }
        }
        this.roles = roleDTOs;
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

    public List<RoleDTO> getRole() {
        return roles;
    }

    public void setRole(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public static List<UsuarioDTO> convert(List<UsuarioVo> usuarioVoList){
        return usuarioVoList.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    public static UsuarioDTO convert(UsuarioVo usuarioVo){
        return new UsuarioDTO(usuarioVo);
    }

    public UsuarioVo convert(){
        List<RoleVo> roleVos = new ArrayList<>();
        for (RoleDTO role : roles) {
            roleVos.add(role.convert());
        }
        return new UsuarioVo(id, username, password, roleVos);
    }
}
