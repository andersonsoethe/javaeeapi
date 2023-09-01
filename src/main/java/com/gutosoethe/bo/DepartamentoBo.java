package com.gutosoethe.bo;

import com.gutosoethe.dao.DepartamentoJpaDao;
import com.gutosoethe.dao.JpaGenericsDao;
import com.gutosoethe.model.Departamento;
import com.gutosoethe.vo.DepartamentoVo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartamentoBo extends GenericsBo<Departamento, DepartamentoVo, Long, DepartamentoJpaDao>{

    @Override
    public void adicionar(DepartamentoVo entity) {
        Departamento departamento = new Departamento();
        departamento.setNome(entity.getNome());
        departamento.setDescricao(entity.getDescricao());
        jpaGenericsDao.get().save(departamento);
    }

    @Override
    public DepartamentoVo atualizar(Long id, DepartamentoVo entity) {
        Departamento departamento = jpaGenericsDao.get().findById(id);
        if (entity.getNome() != null){
            departamento.setNome(entity.getNome());
        }
        if (entity.getDescricao() != null){
            departamento.setDescricao(entity.getDescricao());
        }
        jpaGenericsDao.get().update(departamento);
        return conversor.convertSource(departamento);
    }
}
