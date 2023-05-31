package com.gutosoethe.rest;

import java.util.List;

import javax.validation.Valid;

import javax.ws.rs.core.Response;

public interface CrudRest<T, U> {

    Response adicionar(@Valid U entity);

    Response atualizar(long id, U entity);

    void deletar(long id);

    U buscaPorId(long id);

    List<U> buscar();

}
