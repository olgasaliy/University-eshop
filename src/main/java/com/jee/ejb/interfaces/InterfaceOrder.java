package com.jee.ejb.interfaces;

import com.jee.entity.CustomerOrder;

import java.util.List;

/**
 * Created by olgasaliy on 16.11.16.
 */
public interface InterfaceOrder<T> extends InterfaceEJB<T>  {
    List<CustomerOrder> getOrderByCustomerId(Integer id);

}
