package com.gutosoethe.bo;

import com.gutosoethe.dao.PessoaJpaDao;
import com.gutosoethe.dao.ProdutoJpaDao;
import com.gutosoethe.exception.BusinessExecption;
import com.gutosoethe.model.Departamento;
import com.gutosoethe.model.Pessoa;
import com.gutosoethe.model.Produto;
import com.gutosoethe.vo.PessoaVo;
import com.gutosoethe.vo.ProdutoVo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoBo extends GenericsBo<Produto, ProdutoVo, Long, ProdutoJpaDao> {

    @Override
    public void adicionar(ProdutoVo entity) {
        if (entity.getValor() < 0){
            throw new BusinessExecption("O valor não pode ser negativo");
        }
        Produto produto = new Produto();
        produto.setNome(entity.getNome());
        produto.setDescricao(entity.getDescricao());
        produto.setValor(entity.getValor());
        jpaGenericsDao.get().save(produto);
    }

    @Override
    public ProdutoVo atualizar(Long id, ProdutoVo entity) {
        Produto produto = jpaGenericsDao.get().findById(id);
        if (entity.getNome() != null){
            produto.setNome(entity.getNome());
        }
        if (entity.getDescricao() != null){
            produto.setDescricao(entity.getDescricao());
        }
        if (entity.getValor() != null){
            produto.setValor(entity.getValor());
        }
        jpaGenericsDao.get().update(produto);
        return conversor.convertSource(produto);
    }
}
