package com.gutosoethe.bo;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.gutosoethe.dao.PessoaJpaDao;
import com.gutosoethe.dto.PessoaDTO;
import com.gutosoethe.exception.BusinessExecption;
import com.gutosoethe.model.Pessoa;
import com.gutosoethe.util.ConversorGenerico;
import com.gutosoethe.vo.PessoaVo;

@ApplicationScoped
public class PessoaBo {

    @Inject
    private PessoaJpaDao pessoaJpaDao;

    ConversorGenerico<Pessoa, PessoaVo> conversor = new ConversorGenerico<>(Pessoa.class, PessoaVo.class);
//    List<PessoaVo> pessoaVoList = // sua lista de PessoaVo
//    List<Pessoa> pessoaList = conversor.convert(pessoaVoList);

    @Transactional(TxType.NOT_SUPPORTED)
//    public List<PessoaVo> listarPessoas(){
//        return PessoaVo.convert(pessoaJpaDao.findAll());
//    }
    public List<PessoaVo> listarPessoas(){
        List<Pessoa> pessoaList = pessoaJpaDao.findAll();
        return conversor.convert(pessoaList);
    }

    @Transactional(TxType.NOT_SUPPORTED)
    public PessoaVo listarById(long id){
        return conversor.convertObject(pessoaJpaDao.findById(id));
    }

    @Transactional(TxType.REQUIRED)
    public PessoaVo adicionarPessoa(PessoaDTO p) {
        if (p.getIdade() < 18){
            throw new BusinessExecption("A idade deve ter mais de 18 anos");
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(p.getNome());
        pessoa.setEmail(p.getEmail());
        pessoa.setIdade(p.getIdade());
        pessoa.setPhone(p.getPhone());
        pessoaJpaDao.save(pessoa);
        return conversor.convertObject(pessoa);
    }

    @Transactional(TxType.REQUIRED)
    public void removerPessoa(long pessoaId) {
        pessoaJpaDao.delete(pessoaId);
    }

    @Transactional(TxType.REQUIRED)
    public PessoaVo atualizarPessoa(long id, PessoaDTO p) {
        Pessoa pessoa = pessoaJpaDao.findById(id);
        if (p.getNome() != null){
            pessoa.setNome(p.getNome());
        }
        if (p.getEmail() != null){
            pessoa.setEmail(p.getEmail());
        }
        if (p.getIdade() != null){
            pessoa.setIdade(p.getIdade());
        }
        if (p.getPhone() != null){
            pessoa.setPhone(p.getPhone());
        }
        pessoaJpaDao.update(pessoa);
        return conversor.convertObject(pessoa);
    }
}