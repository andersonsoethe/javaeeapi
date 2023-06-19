package com.gutosoethe.bo;


import javax.inject.Inject;

import com.gutosoethe.dao.PessoaJpaDao;
import com.gutosoethe.exception.BusinessExecption;
import com.gutosoethe.model.Pessoa;
import com.gutosoethe.util.ConversorGenerico;
import com.gutosoethe.vo.PessoaVo;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class PessoaBo extends GenericsBo<Pessoa, PessoaVo, ID> {

    @Inject
    private PessoaJpaDao pessoaJpaDao;

    private ConversorGenerico<Pessoa, PessoaVo> conversor;

    @Override
    public PessoaVo adicionar(PessoaVo entity) {
        if (entity.getIdade() < 18){
            throw new BusinessExecption("A idade deve ter mais de 18 anos");
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(entity.getNome());
        pessoa.setEmail(entity.getEmail());
        pessoa.setIdade(entity.getIdade());
        pessoa.setPhone(entity.getPhone());
//        long id = (entity.getDepartamento().getId());
//        if (buscar(entity.getDepartamento().getId()).isEmpty()){
//
//        }
        pessoa.setDepartamento(entity.getDepartamento());
        pessoaJpaDao.save(pessoa);
        return conversor.convertSource(pessoa) ;
    }

    @Override
    public PessoaVo atualizar(long id, PessoaVo entity) {
        Pessoa pessoa = pessoaJpaDao.findById(id);
        if (entity.getNome() != null){
            pessoa.setNome(entity.getNome());
        }
        if (entity.getEmail() != null){
            pessoa.setEmail(entity.getEmail());
        }
        if (entity.getIdade() != null){
            pessoa.setIdade(entity.getIdade());
        }
        if (entity.getPhone() != null){
            pessoa.setPhone(entity.getPhone());
        }
        if (entity.getDepartamento() !=null){
            pessoa.setDepartamento(entity.getDepartamento());
        }
        pessoaJpaDao.update(pessoa);
        return conversor.convertSource(pessoa);
    }
}
