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

import com.gutosoethe.annotation.ReponseStatus;
import com.gutosoethe.bo.PessoaBo;
import com.gutosoethe.dto.PessoaDTO;

@Path("/pessoas")
public class PessoaRest {

    @Inject
    private PessoaBo pessoaBo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PessoaDTO> buscaPessoas(){
        return PessoaDTO.convert(pessoaBo.listarPessoas());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PessoaDTO buscaPessoaById(@PathParam("id") long id){
        return PessoaDTO.convert(pessoaBo.listarById(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarPessoa(@Valid PessoaDTO p){
        try {
            return Response.status(Response.Status.CREATED).entity(pessoaBo.adicionarPessoa(p)).build();
        }catch (Exception e) {
            int status = 500;
            if (e.getClass().isAnnotationPresent(ReponseStatus.class)){
                status = e.getClass().getAnnotation(ReponseStatus.class).value();
            }
            return Response.status(status).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void atualizarPessoa(@PathParam("id") long id, PessoaDTO p){
        pessoaBo.atualizarPessoa(id, p);
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void removerPessoa(@PathParam("id") long id) {
        pessoaBo.removerPessoa(id);
    }
}