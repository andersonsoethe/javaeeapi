package com.gutosoethe.rest;

import com.gutosoethe.bo.UsuarioBo;
import com.gutosoethe.dao.UsuarioJpaDao;
import com.gutosoethe.dto.UsuarioDTO;
import com.gutosoethe.model.Usuario;
import com.gutosoethe.vo.UsuarioVo;

import javax.ws.rs.Path;

@Path("/usuarios")
public class UsuarioRest extends GenericsRest<UsuarioVo, UsuarioDTO, Usuario, Long, UsuarioJpaDao, UsuarioBo> {
    public UsuarioRest() {
        super(UsuarioVo.class, UsuarioDTO.class);
    }

}