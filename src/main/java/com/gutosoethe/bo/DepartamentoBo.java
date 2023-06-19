package com.gutosoethe.bo;

import com.gutosoethe.model.Departamento;
import com.gutosoethe.util.ConversorGenerico;
import com.gutosoethe.vo.DepartamentoVo;
import com.sun.xml.internal.bind.v2.model.core.ID;


public class DepartamentoBo extends GenericsBo<Departamento, DepartamentoVo, ID>{

    private ConversorGenerico<Departamento, DepartamentoVo> conversor;

    @Override
    public DepartamentoVo adicionar(DepartamentoVo entity) {
        Departamento departamento = new Departamento();
        departamento.setNome(entity.getNome());
        departamento.setDescricao(entity.getDescricao());
        jpaGenericsDao.save(departamento);
        return conversor.convertSource(departamento);
    }

    @Override
    public DepartamentoVo atualizar(ID id, DepartamentoVo entity) {
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
