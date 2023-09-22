package com.gutosoethe.dto;

import com.gutosoethe.vo.PessoaVo;
import com.gutosoethe.vo.ProdutoVo;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ProdutoDTO {



    private long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O campo descricao não pode ser nulo")
    @NotBlank(message = "O campo descricao não pode estar em branco")
    private String descricao;

    @NotNull(message = "O campo valor não pode ser nulo")
    private Double valor;

    public ProdutoDTO() {
    }

    public ProdutoDTO(long id, String nome, String descricao, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public ProdutoDTO(ProdutoVo produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    //fazer os conversores

}
