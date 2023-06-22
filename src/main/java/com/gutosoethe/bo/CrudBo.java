package com.gutosoethe.bo;

import java.io.Serializable;
import java.util.List;

public interface CrudBo<E, V, ID extends Serializable> {

    V adicionar(V entity);

    V atualizar(ID id, V entity);

    void deletar(ID id);

    V buscaPorId(ID id);

    List<V> buscar();

}
