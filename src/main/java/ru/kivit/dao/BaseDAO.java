package ru.kivit.dao;

import java.util.List;

public interface BaseDAO<T>{

    List<T> findAll();

    T findById(Long id);

    T save(T t);

    void update(T t, Long id);

    void deleteById(Long id);
}
