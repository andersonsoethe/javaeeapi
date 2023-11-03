package com.gutosoethe.dao;

import com.gutosoethe.model.Usuario;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioJpaDao extends JpaGenericsDao<Usuario, Long>{
}
