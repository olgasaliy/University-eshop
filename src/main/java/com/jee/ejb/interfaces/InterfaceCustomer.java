package com.jee.ejb.interfaces;

import com.jee.entity.Person;

/**
 * Created by olgasaliy on 14.11.16.
 */
public interface InterfaceCustomer<T> extends InterfaceEJB<T> {
    void init();
    Person getUserByEmail(String email);
}
