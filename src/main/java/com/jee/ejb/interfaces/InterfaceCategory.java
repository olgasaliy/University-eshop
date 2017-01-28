package com.jee.ejb.interfaces;

import com.jee.entity.Category;

/**
 * Created by olgasaliy on 12.11.16.
 */
public interface InterfaceCategory<T> extends InterfaceEJB<T> {
     Category getByName(String name);
    void init ();
}
