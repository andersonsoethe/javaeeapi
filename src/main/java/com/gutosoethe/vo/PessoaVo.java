package com.gutosoethe.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.gutosoethe.model.Pessoa;

//gerado pelo chatGPT
public class PessoaVo implements Serializable {

    private static final long serialVersionUID = 0L;

    private long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O campo email não pode ser nulo")
    @NotBlank(message = "O campo email não pode estar em branco")
    @Email
    private String email;

    @NotNull(message = "O campo email não pode ser nulo")
    @NotBlank(message = "O campo email não pode estar em branco")
    private String phone;

    @NotNull(message = "O campo email não pode ser nulo")
    @Min(0)
    private Integer idade;

    public PessoaVo() {
    }

    public PessoaVo(long id, String nome, String email, String phone, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.phone = phone;
        this.idade = idade;
    }

    public PessoaVo(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.idade = pessoa.getIdade();
        this.phone = pessoa.getPhone();
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

    public static List<PessoaVo> convert(List<Pessoa> pessoaList){
        return pessoaList.stream().map(PessoaVo::new).collect(Collectors.toList());
    }

    public static PessoaVo convert(Pessoa pessoa){
        return new PessoaVo(pessoa);
    }

}
