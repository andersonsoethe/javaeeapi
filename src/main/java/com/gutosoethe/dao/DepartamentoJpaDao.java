package com.gutosoethe.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import com.gutosoethe.model.Departamento;

@ApplicationScoped
public class DepartamentoJpaDao extends JpaGenericsDao<Departamento, Long> {

}
