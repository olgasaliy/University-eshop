package com.jee.controller;

import com.jee.ejb.interfaces.InterfaceOrderStatus;
import com.jee.entity.OrderStatus;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;

//import static java.util.logging.Level.*;

/**
 * Created by olgasaliy on 16.11.16.
 */
@Named(value = "orderStatusController")
@RequestScoped
public class OrderStatusController {


    private OrderStatus current;

    @EJB(beanName = "OrderStatusEJB")
    private InterfaceOrderStatus<OrderStatus> osEJB;

    public OrderStatus getSelected() {
        if (current == null) {
            current = new OrderStatus();
        }
        return current;
    }

    public void add() {
        try {
            osEJB.add(current);
        } catch (Exception e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public OrderStatus getCurrent() {
        return current;
    }

    public void setCurrent(OrderStatus current) {
        this.current = current;
    }

    public InterfaceOrderStatus<OrderStatus> getOsEJB() {
        return osEJB;
    }

    public void setOsEJB(InterfaceOrderStatus<OrderStatus> osEJB) {
        this.osEJB = osEJB;
    }
}
