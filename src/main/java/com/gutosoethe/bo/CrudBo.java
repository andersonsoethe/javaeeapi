package com.gutosoethe.bo;

import java.util.List;

public interface CrudBo<E, V> {

    V adicionar(V entity);

    V atualizar(long id, V entity);

    void deletar(long id);

    V buscaPorId(long id);

    List<V> buscar();

}
