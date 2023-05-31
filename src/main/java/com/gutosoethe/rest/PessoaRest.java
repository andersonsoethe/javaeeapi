package com.gutosoethe.rest;

import javax.ws.rs.Path;

import com.gutosoethe.dto.PessoaDTO;
import com.gutosoethe.vo.PessoaVo;


@Path("/pessoas")
public class PessoaRest extends GenericsRest<PessoaVo, PessoaDTO> {

    public PessoaRest() {
        super(PessoaVo.class, PessoaDTO.class);
    }

//    @Inject
//    private PessoaBo pessoaBo;
//
//    ConversorGenerico<PessoaVo, PessoaDTO> conversor = new ConversorGenerico<>(PessoaVo.class, PessoaDTO.class);
//    //    List<PessoaVo> pessoaVoList = // sua lista de PessoaVo
//    //    List<Pessoa> pessoaList = conversor.convert(pessoaVoList);
//
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<PessoaDTO> buscaPessoas(){
//        List<PessoaVo> pessoaVoList = pessoaBo.listarPessoas();
//        return conversor.convert(pessoaVoList);
//    }
//
//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public PessoaDTO buscaPessoaById(@PathParam("id") long id){
//        return conversor.convertObject(pessoaBo.listarById(id));
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response adicionarPessoa(@Valid PessoaDTO p){
//        try {
//            return Response.status(Response.Status.CREATED).entity(pessoaBo.adicionarPessoa(p)).build();
//        }catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response atualizarPessoa(@PathParam("id") long id, PessoaDTO p) {
//        try {
//            return Response.status(Response.Status.OK).entity(pessoaBo.atualizarPessoa(id, p)).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public void removerPessoa(@PathParam("id") long id) {
//        pessoaBo.removerPessoa(id);
//    }
}