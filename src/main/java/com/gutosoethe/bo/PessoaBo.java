package com.gutosoethe.bo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.gutosoethe.dao.JpaGenericsDao;
import com.gutosoethe.dao.PessoaJpaDao;
import com.gutosoethe.exception.BusinessExecption;
import com.gutosoethe.model.Departamento;
import com.gutosoethe.model.Pessoa;
import com.gutosoethe.vo.PessoaVo;

@ApplicationScoped
public class PessoaBo extends GenericsBo<Pessoa, PessoaVo, Long, PessoaJpaDao> {

    @Override
    public void adicionar(PessoaVo entity) {
        if (entity.getIdade() < 18){
            throw new BusinessExecption("A idade deve ter mais de 18 anos");
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(entity.getNome());
        pessoa.setEmail(entity.getEmail());
        pessoa.setIdade(entity.getIdade());
        pessoa.setPhone(entity.getPhone());
        Departamento departamento = entity.getDepartamento().convert();
        pessoa.setDepartamento(departamento);
        jpaGenericsDao.get().save(pessoa);
    }

    @Override
    public PessoaVo atualizar(Long id, PessoaVo entity) {
        Pessoa pessoa = jpaGenericsDao.get().findById(id);
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
            pessoa.setDepartamento(entity.getDepartamento().convert());
        }
        jpaGenericsDao.get().update(pessoa);
        return conversor.convertSource(pessoa);
    }
}
