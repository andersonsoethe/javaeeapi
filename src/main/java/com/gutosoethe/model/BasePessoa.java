package com.gutosoethe.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BasePessoa implements Serializable {
    private static final long serialVersionUID = 5864879502621479086L;

    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
