package com.pizzajdbc.infrastructure.dao;

import java.util.List;

public interface Dao<T> {
    void insert(T object);
    void update(T object);
    void deleteById(Long id);
    default void deleteById(String id) {}
    T findById(Long id);
    default T findById(String id) {
        return null;
    } //Isso significa que qualquer classe que implemente essa interface pode escolher não fornecer sua própria implementação para o método findById
    List<T> findAll();
}
