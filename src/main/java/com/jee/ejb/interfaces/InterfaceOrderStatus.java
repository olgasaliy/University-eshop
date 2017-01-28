package com.jee.ejb.interfaces;

import com.jee.entity.OrderStatus;

/**
 * Created by olgasaliy on 16.11.16.
 */
public interface InterfaceOrderStatus <T> extends InterfaceEJB<T>  {
    OrderStatus getStatusByName(String status);
    void init ();

}