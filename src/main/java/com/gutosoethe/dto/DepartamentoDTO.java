package com.gutosoethe.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.gutosoethe.vo.DepartamentoVo;

public class DepartamentoDTO implements Serializable {

    private static final long serialVersionUID = -2118869655455401551L;

    private long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "O campo nome não pode estar em branco")
    private String descricao;

    public DepartamentoDTO() {

    }

    public DepartamentoDTO(long id, String nome, String desc) {
        this.id = id;
        this.nome = nome;
        this.descricao = desc;
    }

    public DepartamentoDTO(DepartamentoVo departamentoVo) {
        this.id = departamentoVo.getId();
        this.nome = departamentoVo.getNome();
        this.descricao = departamentoVo.getDescricao();
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

    public static List<DepartamentoDTO> convert(List<DepartamentoVo> departamentoVoList){
        return departamentoVoList.stream().map(DepartamentoDTO::new).collect(Collectors.toList());
    }

    public static DepartamentoDTO convert(DepartamentoVo departamentoVo){
        return new DepartamentoDTO(departamentoVo);
    }

    public DepartamentoVo convert(){
        return new DepartamentoVo(id, nome, descricao);
    }

}
