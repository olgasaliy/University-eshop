package com.jee.ejb.interfaces;

import java.util.List;

/**
 * Created by olgasaliy on 09.11.16.
 */
public interface InterfaceEJB<T> {
    T add(T obj);

    void delete(int id);

    void delete(T obj);

    void update(T obj);

    T find(int id);

    List<T> getAll();

    Class<T> getEntityClass();
}
