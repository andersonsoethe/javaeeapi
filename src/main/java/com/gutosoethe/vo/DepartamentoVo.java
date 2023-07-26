package com.gutosoethe.vo;

import java.io.Serializable;

import com.gutosoethe.model.Departamento;


public class DepartamentoVo implements Serializable {

    private long id;

    private String nome;

    private String descricao;

    public DepartamentoVo(){

    }

    public DepartamentoVo(long id, String nome, String desc) {
        this.id = id;
        this.nome = nome;
        this.descricao = desc;
    }

    public DepartamentoVo(Departamento departamento) {
        this.id = departamento.getId();
        this.nome = departamento.getNome();
        this.descricao = departamento.getDescricao();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Departamento convert(){
        return new Departamento(id, nome, descricao);
    }
}
