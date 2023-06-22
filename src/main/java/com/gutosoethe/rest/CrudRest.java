package com.gutosoethe.rest;

import java.util.List;

import javax.validation.Valid;

import javax.ws.rs.core.Response;

public interface CrudRest<V, D, ID> {

    Response adicionar(@Valid D dto);

    Response atualizar(ID id, D dto);

    void deletar(ID id);

    D buscaPorId(ID id);

    List<D> buscar();

}
