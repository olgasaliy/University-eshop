package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceOrder;
import com.jee.entity.CustomerOrder;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by olgasaliy on 16.11.16.
 */
@Stateless
@Local(InterfaceOrder.class)
public class OrderEJB extends AbstractEJB<CustomerOrder> implements InterfaceOrder<CustomerOrder>, Serializable {


    @Override
    public List<CustomerOrder> getOrderByCustomerId(Integer id) {
        Query createNamedQuery = em.createNamedQuery("CustomerOrder.findByCustomerId");
        createNamedQuery.setParameter("id", id);
        return createNamedQuery.getResultList();
    }

    @Override
    public List<CustomerOrder> getAll() {
        Query query = em.createNamedQuery("CustomerOrder.findAll");
        return query.getResultList();
    }

    @Override
    public Class<CustomerOrder> getEntityClass() {
        return CustomerOrder.class;
    }
}
