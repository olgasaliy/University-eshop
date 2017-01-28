package com.jee.ejb.interfaces;

import com.jee.entity.Person;

/**
 * Created by olgasaliy on 14.11.16.
 */
public interface InterfacePerson<T> extends InterfaceEJB<T> {
    Person getPersonByEmail(String email);

}
