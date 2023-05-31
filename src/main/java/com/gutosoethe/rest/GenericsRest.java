package com.gutosoethe.rest;

import java.util.List;

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

import com.gutosoethe.bo.PessoaBo;
import com.gutosoethe.dto.PessoaDTO;
import com.gutosoethe.util.ConversorGenerico;

public abstract class GenericsRest<T, U> implements CrudRest<T, U> {

    @Inject
    private PessoaBo pessoaBo;

    private ConversorGenerico<T, U> conversor;

    public GenericsRest(Class<T> voClass, Class<U> dtoClass) {
        this.conversor = new ConversorGenerico<>(voClass, dtoClass);
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<U> buscar() {
        List<T> voList = (List<T>) pessoaBo.listarPessoas();
        return conversor.convert(voList);
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public U buscaPorId(@PathParam("id") long id) {
        return conversor.convertObject((T) pessoaBo.listarById(id));
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionar(@Valid U entity) {
        return Response.status(Response.Status.CREATED).entity(pessoaBo.adicionarPessoa((PessoaDTO) entity)).build();
    }

    @Override
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") long id, U entity) {
        try {
            return Response.status(Response.Status.OK).entity(pessoaBo.atualizarPessoa(id, (PessoaDTO) entity)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deletar(@PathParam("id") long id) {
        pessoaBo.removerPessoa(id);
    }


}
