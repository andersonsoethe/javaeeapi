package com.gutosoethe.dao;

import com.gutosoethe.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class UsuarioJpaDao extends JpaGenericsDao<Usuario, Long>{
}
