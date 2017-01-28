package com.jee.ejb.interfaces;

import com.jee.entity.OrderDetail;

import java.util.List;

/**
 * Created by olgasaliy on 16.11.16.
 */
public interface InterfaceOrderDetail <T> extends InterfaceEJB<T>  {
    List<OrderDetail> findOrderDetailByOrder(int orderId);

}