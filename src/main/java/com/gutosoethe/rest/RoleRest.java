package com.gutosoethe.rest;

import com.gutosoethe.bo.RoleBo;
import com.gutosoethe.bo.UsuarioBo;
import com.gutosoethe.dao.RoleJpaDao;
import com.gutosoethe.dao.UsuarioJpaDao;
import com.gutosoethe.dto.RoleDTO;
import com.gutosoethe.dto.UsuarioDTO;
import com.gutosoethe.model.Role;
import com.gutosoethe.model.Usuario;
import com.gutosoethe.vo.RoleVo;
import com.gutosoethe.vo.UsuarioVo;

import javax.ws.rs.Path;

@Path("/roles")
public class RoleRest extends GenericsRest<RoleVo, RoleDTO, Role, Long, RoleJpaDao, RoleBo> {
    public RoleRest() {
        super(RoleVo.class, RoleDTO.class);
    }

}