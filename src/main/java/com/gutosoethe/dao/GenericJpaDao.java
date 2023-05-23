package com.gutosoethe.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.gutosoethe.generics.GenericDao;
import com.gutosoethe.model.Pessoa;

public abstract class GenericJpaDao<T, ID extends Serializable> implements GenericDao<T, ID> {

    private final Class<T> persistentClass;

    private EntityManager entityManager;

    public GenericJpaDao(Class<T> persistentClass){
        this.persistentClass = persistentClass;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void save(T entity) throws SQLException {
         entityManager.persist(entity);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public T update(T entity) throws SQLException {
        return entityManager.merge(entity);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void delete(T entity) throws SQLException {
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
        return entityManager.createQuery("From "+
                Pessoa.class.getName()).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void flush() {
        entityManager.flush();
    }
}
