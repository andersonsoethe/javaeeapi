package com.gutosoethe.generics;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    void save(T entity) throws SQLException;
    T update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    T findById(ID id);
    List<T> findAll();
    void flush();

}
