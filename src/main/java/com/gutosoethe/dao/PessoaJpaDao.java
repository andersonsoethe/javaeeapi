package com.gutosoethe.dao;

import javax.enterprise.context.ApplicationScoped;

import com.gutosoethe.model.Pessoa;

@ApplicationScoped
public class PessoaJpaDao extends JpaGenericsDao<Pessoa, Long> {

    public PessoaJpaDao() {
        super(Pessoa.class);
    }
}
