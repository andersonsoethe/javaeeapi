package com.gutosoethe.bo;

import com.gutosoethe.dao.UsuarioJpaDao;
import com.gutosoethe.model.Role;
import com.gutosoethe.model.Usuario;
import com.gutosoethe.vo.RoleVo;
import com.gutosoethe.vo.UsuarioVo;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioBo extends GenericsBo<Usuario, UsuarioVo, Long, UsuarioJpaDao> {

    @Override
    public void adicionar(UsuarioVo entity) {
        Usuario usuario = new Usuario();
        usuario.setUsername(entity.getUsername());
        usuario.setPassword(entity.getPassword());
        List<Role> roles = new ArrayList<>();
        for (RoleVo roleVo : entity.getRole()) {
            roles.add(roleVo.convert());
        }
        usuario.setRole(roles);
        jpaGenericsDao.get().save(usuario);
    }

    @Override
    public UsuarioVo atualizar(Long id, UsuarioVo entity) {
        Usuario usuario = jpaGenericsDao.get().findById(id);
        if (entity.getUsername() != null){
            usuario.setUsername(entity.getUsername());
        }
        if (entity.getPassword() != null){
            usuario.setPassword(entity.getPassword());
        }
        if (entity.getRole() != null){
            List<Role> roles = new ArrayList<>();
            for (RoleVo roleVo : entity.getRole()) {
                roles.add(roleVo.convert());
            }
            usuario.setRole(roles);
        }
        jpaGenericsDao.get().update(usuario);
        return conversor.convertSource(usuario);
    }

    public UsuarioVo buscaPorUsername(String username){
        Usuario usuario = jpaGenericsDao.get().findByUsername(username);
        return conversor.convertSource(usuario);
    }

    public boolean userIsValid(String username, String password){
        Usuario usuario = jpaGenericsDao.get().findByUsername(username);
        return username.equals(usuario.getUsername()) && password.equals(usuario.getPassword());
    }
}
