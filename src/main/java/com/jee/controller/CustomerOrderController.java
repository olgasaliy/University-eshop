package com.jee.controller;

import com.jee.additional.Authenticated;
import com.jee.ejb.interfaces.InterfaceOrder;
import com.jee.entity.CustomerOrder;
import com.jee.entity.OrderStatus;
import com.jee.entity.Person;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olgasaliy on 16.11.16.
 */
@Named(value = "customerOrderController")
@SessionScoped
public class CustomerOrderController implements Serializable {

    private static final Logger logger = Logger.getLogger(CustomerOrderController.class.getCanonicalName());

    @Inject @Authenticated
    private Person user;

    @EJB
    private InterfaceOrder<CustomerOrder> oEJB;

    private List<CustomerOrder> myOrders;

    private CustomerOrder current;

    public CustomerOrder getSelected() {
        if (current == null) {
            current = new CustomerOrder();
        }
        return current;
    }

    public void create() {
        try {
            oEJB.add(current);
            //logging
        } catch (Exception e) {
            //logging
        }
    }


    public List<CustomerOrder> getMyOrders() {

        if (user != null) {

            myOrders = oEJB.getOrderByCustomerId(user.getId());
            if (myOrders.isEmpty()) {

                logger.log(Level.FINEST, "Customer {0} has no orders to display.", user.getEmail());
                return null;
            } else {
                logger.log(Level.FINEST, "Order amount:{0}", myOrders.get(0).getAmount());
                return myOrders;
            }

        } else {
            logger.log(Level.FINEST, "Order amount:{0}", myOrders.get(0).getAmount());
//

            return null;
        }
    }

    public List<CustomerOrder> getAll () {
        return oEJB.getAll();
    }

    public int getCount () {
        return oEJB.getAll().size();
    }

    public String prepareView(CustomerOrder o) {
        current = o;
        return "View";
    }

    public String prepareList() {

        return "List";
    }

    public String destroy(CustomerOrder o) {
        oEJB.delete(o);


        return "List";
    }

    public String destroy() {
//        current.setOrderStatus(null);
//        current.getOrderStatus().getCustomerOrderList().remove(current);
//        current.getCustomer().getCustomerOrderList().remove(current);
        oEJB.delete(current);
        return "List";
    }




    public String confirm (CustomerOrder o) {
        current = o;
        current.setOrderStatus(new OrderStatus(2));
        oEJB.update(current);
        return "List";
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public InterfaceOrder<CustomerOrder> getoEJB() {
        return oEJB;
    }

    public void setoEJB(InterfaceOrder<CustomerOrder> oEJB) {
        this.oEJB = oEJB;
    }

    public void setMyOrders(List<CustomerOrder> myOrders) {
        this.myOrders = myOrders;
    }

    public CustomerOrder getCurrent() {
        return current;
    }

    public void setCurrent(CustomerOrder current) {
        this.current = current;
    }
}
