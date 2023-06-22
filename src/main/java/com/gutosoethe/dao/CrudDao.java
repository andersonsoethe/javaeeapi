package com.gutosoethe.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<P, ID extends Serializable> {

    void save(P entity) throws SQLException;
    void update(P entity) throws SQLException;
    void delete(ID id) throws SQLException;
    P findById(ID id);
    List<P> findAll();
}
