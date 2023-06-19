package com.gutosoethe.bo;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.gutosoethe.dao.JpaGenericsDao;
import com.gutosoethe.util.ConversorGenerico;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

public abstract class GenericsBo<E, V, ID extends Serializable> implements CrudBo<E, V> {

    @Inject
    protected JpaGenericsDao<E, ID> jpaGenericsDao;

    private ConversorGenerico<E, V> conversor;

    public GenericsBo(){
        Type[] types =((ParameterizedTypeImpl) ((Class) getClass().getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments();
        Class<E> EntityClass = (Class<E>) types[0];
        Class<V> voClass = (Class<V>) types[1];
        this.conversor = new ConversorGenerico<>(EntityClass, voClass);
    }

    @Override
    @Transactional(TxType.NOT_SUPPORTED)
    public List<V> buscar(){
        List<E> entityList = (List<E>) jpaGenericsDao.findAll();
        return conversor.convert(entityList);
    }

    @Override
    @Transactional(TxType.NOT_SUPPORTED)
    public V buscaPorId(long id){
        return conversor.convertSource(jpaGenericsDao.findById(id));
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public V adicionar(V entity) {
        jpaGenericsDao.save(conversor.convertTarget(entity));
        return conversor.convertSource((E) entity);
    }

    @Transactional(TxType.REQUIRED)
    public void deletar(Serializable id) {
        jpaGenericsDao.delete(id);
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public V atualizar(ID id, V entity) {
        jpaGenericsDao.update(conversor.convertTarget(entity));
        return conversor.convertSource((E) entity);
    }
}