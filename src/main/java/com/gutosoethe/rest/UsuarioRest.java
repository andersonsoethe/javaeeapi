package com.gutosoethe.rest;

import com.gutosoethe.bo.UsuarioBo;
import com.gutosoethe.dao.UsuarioJpaDao;
import com.gutosoethe.dto.UsuarioDTO;
import com.gutosoethe.model.Usuario;
import com.gutosoethe.util.ConversorGenerico;
import com.gutosoethe.vo.UsuarioVo;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

@Path("/usuarios")
public class UsuarioRest extends GenericsRest<UsuarioVo, UsuarioDTO, Usuario, Long, UsuarioJpaDao, UsuarioBo> {

    @Inject
    private Instance<UsuarioBo> usuarioBos;

    public UsuarioRest() {
        super(UsuarioVo.class, UsuarioDTO.class);
    }

    @GET
    @Path("/userIsValid")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean userIsValid(@QueryParam("username") String username, @QueryParam("password") String password) {
        return usuarioBos.get().userIsValid(username, password);
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioDTO buscaPorUsername(@PathParam("username")String username) {
        return conversor.convertSource(usuarioBos.get().buscaPorUsername(username));
    }
}