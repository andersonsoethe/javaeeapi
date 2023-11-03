package com.gutosoethe.dao;

import com.gutosoethe.model.Role;
import com.gutosoethe.model.Usuario;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleJpaDao extends JpaGenericsDao<Role, Long>{
}
