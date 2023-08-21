package com.gutosoethe.bo;

import com.gutosoethe.model.Departamento;
import com.gutosoethe.vo.DepartamentoVo;

public class DepartamentoBo extends GenericsBo<Departamento, DepartamentoVo, Long>{

    @Override
    public void adicionar(DepartamentoVo entity) {
        Departamento departamento = new Departamento();
        departamento.setNome(entity.getNome());
        departamento.setDescricao(entity.getDescricao());
        jpaGenericsDao.save(departamento);
    }

    @Override
    public DepartamentoVo atualizar(Long id, DepartamentoVo entity) {
        Departamento departamento = jpaGenericsDao.findById(id);
        if (entity.getNome() != null){
            departamento.setNome(entity.getNome());
        }
        if (entity.getDescricao() != null){
            departamento.setDescricao(entity.getDescricao());
        }
        jpaGenericsDao.update(departamento);
        return conversor.convertSource(departamento);
    }
}
