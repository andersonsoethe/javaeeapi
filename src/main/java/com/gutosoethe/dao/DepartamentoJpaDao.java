package com.gutosoethe.dao;

import javax.persistence.EntityManager;

import com.gutosoethe.model.Departamento;

public class DepartamentoJpaDao extends JpaGenericsDao<Departamento, Long> {

    public DepartamentoJpaDao(EntityManager entityManager) {
    }
}
