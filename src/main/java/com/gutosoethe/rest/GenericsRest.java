package com.gutosoethe.rest;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gutosoethe.bo.GenericsBo;
import com.gutosoethe.dao.JpaGenericsDao;
import com.gutosoethe.util.ConversorGenerico;

public abstract class GenericsRest<V, D, E, ID extends Serializable, DAO extends JpaGenericsDao<E, ID>,
        B extends GenericsBo<E, V, ID, DAO>>
        implements CrudRest<V, D, ID> {

    @Inject
    private Instance<B> genericsBo;

    protected ConversorGenerico<V, D> conversor;

    public GenericsRest(){
    }

    public GenericsRest(Class<V> voClass, Class<D> dtoClass) {
        this.conversor = new ConversorGenerico<>(voClass, dtoClass);
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<D> buscar() {
        List<V> voList = genericsBo.get().buscar();
        return conversor.convert(voList);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public D buscaPorId(@PathParam("id") ID id) {
        return conversor.convertSource(genericsBo.get().buscaPorId(id));
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionar(@Valid D dto) {
        V vo = conversor.convertTarget(dto);
        genericsBo.get().adicionar(vo);
        return Response.status(Response.Status.CREATED).build();
    }

    @Override
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") ID id, D dto) {
            V vo = conversor.convertTarget(dto);
            genericsBo.get().atualizar(id, vo);
            return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deletar(@PathParam("id") ID id) {
        genericsBo.get().deletar(id);
    }
}
