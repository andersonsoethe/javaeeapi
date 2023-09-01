package com.gutosoethe.bo;

import com.gutosoethe.dao.JpaGenericsDao;
import com.gutosoethe.util.ConversorGenerico;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericsBo<E, V, ID extends Serializable, D extends JpaGenericsDao<E, ID>>
        implements CrudBo<E, V, ID> {

    @Inject
    protected Instance<D> jpaGenericsDao;

    protected ConversorGenerico<E, V> conversor;

    public GenericsBo(){
        Type[] types =((ParameterizedTypeImpl) ((Class) getClass().getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments();
        Class<E> EntityClass = (Class<E>) types[0];
        Class<V> voClass = (Class<V>) types[1];
        this.conversor = new ConversorGenerico<>(EntityClass, voClass);
    }

    @Override
    @Transactional(TxType.NOT_SUPPORTED)
    public List<V> buscar(){
        List<E> entityList = jpaGenericsDao.get().findAll();
        return conversor.convert(entityList);
    }

    @Override
    @Transactional(TxType.NOT_SUPPORTED)
    public V buscaPorId(ID id){
        return conversor.convertSource(jpaGenericsDao.get().findById(id));
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public void adicionar(V entity) {
        jpaGenericsDao.get().save(conversor.convertTarget(entity));
    }

    @Transactional(TxType.REQUIRED)
    public void deletar(ID id) {
        jpaGenericsDao.get().delete(id);
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public V atualizar(ID id, V entity) {
        jpaGenericsDao.get().update(conversor.convertTarget(entity));
        return conversor.convertSource((E) entity);
    }
}