package com.gutosoethe.vo;

import java.io.Serializable;

import com.gutosoethe.model.Pessoa;

public class PessoaVo implements Serializable {

    private long id;

    private String nome;

    private String email;

    private String phone;

    private Integer idade;

    private DepartamentoVo departamento;

    public PessoaVo() {
    }

    public PessoaVo(long id, String nome, String email, String phone, Integer idade, DepartamentoVo departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.phone = phone;
        this.idade = idade;
        this.departamento = departamento;
    }

    public PessoaVo(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.idade = pessoa.getIdade();
        this.phone = pessoa.getPhone();
        this.departamento = new DepartamentoVo(pessoa.getDepartamento());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public DepartamentoVo getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoVo departamento) {
        this.departamento = departamento;
    }
}
