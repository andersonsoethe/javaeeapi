package com.gutosoethe.bo;

import com.gutosoethe.dao.JpaGenericsDao;
import com.gutosoethe.util.ConversorGenerico;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

@Named
public abstract class GenericsBo<E, V, ID extends Serializable> implements CrudBo<E, V, ID> {

    @Inject
    protected JpaGenericsDao<E, ID> jpaGenericsDao;

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
        List<E> entityList = jpaGenericsDao.findAll();
        return conversor.convert(entityList);
    }

    @Override
    @Transactional(TxType.NOT_SUPPORTED)
    public V buscaPorId(ID id){
        return conversor.convertSource(jpaGenericsDao.findById(id));
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public void adicionar(V entity) {
        jpaGenericsDao.save(conversor.convertTarget(entity));
    }

    @Transactional(TxType.REQUIRED)
    public void deletar(ID id) {
        jpaGenericsDao.delete(id);
    }

    @Override
    @Transactional(TxType.REQUIRED)
    public V atualizar(ID id, V entity) {
        jpaGenericsDao.update(conversor.convertTarget(entity));
        return conversor.convertSource((E) entity);
    }
}