package com.gutosoethe.bo;

import com.gutosoethe.dao.RoleJpaDao;
import com.gutosoethe.dao.UsuarioJpaDao;
import com.gutosoethe.model.Role;
import com.gutosoethe.model.Usuario;
import com.gutosoethe.vo.RoleVo;
import com.gutosoethe.vo.UsuarioVo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleBo extends GenericsBo<Role, RoleVo, Long, RoleJpaDao> {

    @Override
    public void adicionar(RoleVo entity) {
        Role role = new Role();
        role.setRole(entity.getRole());
        jpaGenericsDao.get().save(role);
    }

    @Override
    public RoleVo atualizar(Long id, RoleVo entity) {
        Role role = jpaGenericsDao.get().findById(id);
        if (entity.getRole() != null){
            role.setRole(entity.getRole());
        }
        jpaGenericsDao.get().update(role);
        return conversor.convertSource(role);
    }
}
