package com.gutosoethe.dto;

import com.gutosoethe.vo.DepartamentoVo;
import com.gutosoethe.vo.PessoaVo;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PessoaDTO implements Serializable {
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

    @NotNull(message = "O campo email não pode ser nulo")
    private DepartamentoDTO departamento;

    public PessoaDTO() {
    }

    public PessoaDTO(long id, String nome, String email, String phone, Integer idade, DepartamentoDTO departamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.phone = phone;
        this.idade = idade;
        this.departamento = departamento;
    }

    public PessoaDTO(PessoaVo pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.idade = pessoa.getIdade();
        this.phone = pessoa.getPhone();
        this.departamento = new DepartamentoDTO(pessoa.getDepartamento());
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

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public static List<PessoaDTO> convert(List<PessoaVo> pessoaList){
        return pessoaList.stream().map(PessoaDTO::new).collect(Collectors.toList());
    }

    public static PessoaDTO convert(PessoaVo pessoaVo){
        return new PessoaDTO(pessoaVo);
    }

    public PessoaVo convert(){
        DepartamentoVo vo = departamento.convert();
        return new PessoaVo(id, nome, email, phone, idade, vo);
    }
}
