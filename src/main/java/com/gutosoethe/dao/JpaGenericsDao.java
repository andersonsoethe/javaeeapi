package com.gutosoethe.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public abstract class JpaGenericsDao<T, ID extends Serializable> implements CrudDao<T, ID> {

    private final Class<T> persistentClass;

    @Inject
    private EntityManager entityManager;

    public JpaGenericsDao(Class<T> persistentClass){
        this.persistentClass = persistentClass;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void save(T entity) {
         entityManager.persist(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(ID id) {
        T entity = findById(id);
        entityManager.remove(entity);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public T findById(ID id) {
        return entityManager.find(persistentClass, id);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<T> findAll() {
        return entityManager.createQuery("Select t From "+
                persistentClass.getSimpleName() + " t").getResultList();
    }
}
