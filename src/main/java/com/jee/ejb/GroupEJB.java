package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceGroup;
import com.jee.entity.Group;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olgasaliy on 14.11.16.
 */
@Stateless
@Local(InterfaceGroup.class)
public class GroupEJB extends AbstractEJB<Group> implements InterfaceGroup<Group> {

    @Override
    public void init() {
        Group first = new Group("USERS", "Clients");
        Group second = new Group("ADMINS", "Administrators");
        add(first);
        add(second);
    }

    @Override
    public List<Group> getAll() {
        Query query = em.createNamedQuery("Group.findAll");
        return query.getResultList();
    }

    @Override
    public Class<Group> getEntityClass() {
        return Group.class;
    }
}
