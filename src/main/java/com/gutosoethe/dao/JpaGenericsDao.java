package com.gutosoethe.dao;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.gutosoethe.model.Usuario;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

public abstract class JpaGenericsDao<P, ID extends Serializable> implements CrudDao<P, ID> {

    private final Class<P> persistentClass;

    @Inject
    private EntityManager entityManager;

    public JpaGenericsDao(){
        Type[] types =((ParameterizedTypeImpl) ((Class) getClass().getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments();
        this.persistentClass = (Class<P>)  types[0];
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void save(P entity) {
         entityManager.persist(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(P entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(ID id) {
        P entity = findById(id);
        entityManager.remove(entity);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public P findById(ID id) {
        return entityManager.find(persistentClass, id);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<P> findAll() {
        return entityManager.createQuery("Select t From "+
                persistentClass.getSimpleName() + " t").getResultList();
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public P findByUsername(String username) {
        try {
            String queryString = "SELECT t FROM " + persistentClass.getSimpleName() + " t WHERE t.username = :username";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("username", username);
            return (P) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
