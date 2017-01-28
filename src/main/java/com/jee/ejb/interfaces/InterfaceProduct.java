package com.jee.ejb.interfaces;

import com.jee.entity.Product;

import java.util.List;

/**
 * Created by olgasaliy on 14.11.16.
 */
public interface InterfaceProduct<T> extends InterfaceEJB<T>  {
    void init ();
    List<Product> findByCategory(int categoryId);

}
