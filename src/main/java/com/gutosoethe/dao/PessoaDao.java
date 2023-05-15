package com.gutosoethe.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.gutosoethe.model.Pessoa;

@ApplicationScoped
public class PessoaDao {

    @Inject
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.SUPPORTS)
    public Pessoa getByIdSupports(long id){
        return entityManager.find(Pessoa.class, id);
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Pessoa getById(long id){
        return getByIdSupports(id);
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Pessoa> findAll(){
        return entityManager.createQuery("From "+
                Pessoa.class.getName()).getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(Pessoa pessoa){
        entityManager.persist(pessoa);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void update(Pessoa pessoa){
        entityManager.merge(pessoa);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void removeById(long id){
        Pessoa pessoa = getById(id);
        delete(pessoa);
    }

    private void delete(Pessoa pessoa) {
        pessoa = entityManager.find(Pessoa.class, pessoa.getId());
        entityManager.remove(pessoa);
    }
}
