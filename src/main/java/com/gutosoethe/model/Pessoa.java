package com.gutosoethe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String email;

    private String phone;

    private Integer idade;

    public Pessoa() {
    }

    public Pessoa(long id, String nome, String email, String phone, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.phone = phone;
        this.idade = idade;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((o == null) || (getClass() != o.getClass()))
            return false;

        Pessoa pessoa = (Pessoa) o;

        return id == pessoa.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
