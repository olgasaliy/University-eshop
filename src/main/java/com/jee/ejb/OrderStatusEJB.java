package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceOrderStatus;
import com.jee.entity.OrderStatus;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by olgasaliy on 16.11.16.
 */
@Stateless
@Local(InterfaceOrderStatus.class)
public class OrderStatusEJB extends AbstractEJB<OrderStatus> implements InterfaceOrderStatus<OrderStatus>, Serializable {

    @Override
    public OrderStatus getStatusByName(String status) {
        Query createNamedQuery = em.createNamedQuery("OrderStatus.findByStatus");
        createNamedQuery.setParameter("status", status);
        return (OrderStatus) createNamedQuery.getSingleResult();
    }

    @Override
    public void init() {
        OrderStatus os1 = new OrderStatus();
        os1.setStatus("Обработка");
        os1.setDescription("В ожидании подтверждения администратором");

        OrderStatus os2 = new OrderStatus();
        os2.setStatus("Архив");
        os2.setDescription("Подтвержден администратором");

        add(os1);
        add(os2);
    }

    @Override
    public List<OrderStatus> getAll() {
        Query query = em.createNamedQuery("OrderStatus.findAll");
        return query.getResultList();
    }

    @Override
    public Class<OrderStatus> getEntityClass() {
        return null;
    }
}
