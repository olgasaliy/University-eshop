package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceOrderDetail;
import com.jee.entity.OrderDetail;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olgasaliy on 16.11.16.
 */
@Stateless
@Local(InterfaceOrderDetail.class)
public class OrderDetailEJB extends AbstractEJB<OrderDetail> implements InterfaceOrderDetail<OrderDetail>{

    @Override
    public List<OrderDetail> findOrderDetailByOrder(int orderId) {
        List<OrderDetail> details = em.createNamedQuery("OrderDetail.findByOrderId").setParameter("orderId", orderId).getResultList();
        return details;
    }

    @Override
    public List<OrderDetail> getAll() {
        Query query = em.createNamedQuery("OrderDetail.findAll");
        return query.getResultList();
    }

    @Override
    public Class<OrderDetail> getEntityClass() {
        return OrderDetail.class;
    }
}
