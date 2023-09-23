package com.gutosoethe.dao;

import com.gutosoethe.model.Produto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoJpaDao extends JpaGenericsDao<Produto, Long> {

}
