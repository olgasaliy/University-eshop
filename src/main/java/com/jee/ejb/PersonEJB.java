package com.jee.ejb;

import com.jee.ejb.interfaces.InterfacePerson;
import com.jee.entity.Person;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olgasaliy on 14.11.16.
 */
@Stateless
@Local(InterfacePerson.class)
public class PersonEJB extends AbstractEJB<Person> implements InterfacePerson<Person>{

    public Person getPersonByEmail(String email) {
        Query createNamedQuery = em.createNamedQuery("Person.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Person) createNamedQuery.getSingleResult();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Person> getAll() {
        Query query = em.createNamedQuery("Person.findAll");
        return query.getResultList();
    }

    @Override
    public Class<Person> getEntityClass() {
        return Person.class;    }
}
