package com.jee.controller;

import com.jee.ejb.interfaces.*;
import com.jee.entity.*;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by olgasaliy on 10.11.16.
 */
@ManagedBean(name = "applicationManage", eager = true)
@ApplicationScoped
public class Application {

    Logger logger = Logger.getLogger(getClass().getName());

    @EJB(beanName = "CategoryEJB")
    private InterfaceCategory<Category> categoryEJB;

    @EJB(beanName = "GroupEJB")
    private InterfaceGroup<Group> groupEJB;

    @EJB(beanName = "AdminEJB")
    private InterfaceAdmin<Administrator> adminEJB;

    @EJB(beanName = "CustomerEJB")
    private InterfaceCustomer<Customer> customerEJB;

    @EJB(beanName = "ProductEJB")
    private InterfaceProduct<Product> productEJB;

    @EJB(beanName = "OrderStatusEJB")
    private InterfaceOrderStatus<OrderStatus> statusEJB;

    
    @PostConstruct
    public void init() {
        categoryEJB.init();
        groupEJB.init();
        adminEJB.init();
        customerEJB.init();
        productEJB.init();
        statusEJB.init();

    }
}
