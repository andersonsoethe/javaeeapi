package com.gutosoethe.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.gutosoethe.generics.GenericPessoaBo;
import com.gutosoethe.model.Pessoa;

@ApplicationScoped
public class PessoaJpaDao extends GenericJpaDao<Pessoa, Long> implements GenericPessoaBo {

    @Inject
    private EntityManager entityManager;

    @Inject
    private GenericJpaDao genericJpaDao;

    public PessoaJpaDao() {
        super(Pessoa.class);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Pessoa getByIdSupports(long id){
        return findById(id);
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Pessoa getById(long id){
        return getByIdSupports(id);
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Pessoa> findAll(){
        return findAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(Pessoa pessoa){
        save(pessoa);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Pessoa update(Pessoa pessoa){
        return update(pessoa);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void removeById(long id) throws SQLException {
        Pessoa pessoa = getById(id);
        delete(pessoa);
    }
}
