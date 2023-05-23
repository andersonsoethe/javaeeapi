package com.gutosoethe.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T, ID extends Serializable> {

    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(ID id) throws SQLException;
    T findById(ID id);
    List<T> findAll();
}
