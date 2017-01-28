package com.jee.controller;

import com.jee.additional.Authenticated;
import com.jee.additional.SHA256;
import com.jee.ejb.interfaces.InterfaceCustomer;
import com.jee.entity.Customer;
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
 * Created by olgasaliy on 15.11.16.
 */
@Named(value = "customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @Inject @Authenticated
    Person authenticated;
    private Customer current;

    private static final Logger logger = Logger.getLogger(CustomerOrderController.class.getCanonicalName());

    @EJB(beanName = "CustomerEJB")
    private InterfaceCustomer<Customer> cEJB;

    private boolean isUserDuplicated(Person p) {
        return (cEJB.getUserByEmail(p.getEmail()) != null);
    }

    public Customer getSelected() {
        if (current == null) {
            current = new Customer();
        }
        return current;
    }

    public InterfaceCustomer<Customer> getcEJB() {
        return cEJB;
    }

    public void setcEJB(InterfaceCustomer<Customer> cEJB) {
        this.cEJB = cEJB;
    }
//    @PostConstruct
//    public void init() {
//        current = new Customer();
//    }

    public List<Customer> getAll () {
        return cEJB.getAll();
    }

    public int getCount () {
        return getAll().size();
    }

    public String create() {
        try {
            if (!isUserDuplicated(current)) {
                current.setPassword(SHA256.sha256(current.getPassword()));
                cEJB.add(current);
                return "/index.xhtml";
                //logging
            } else {
                //logging
            }

            return "/index.xhtml";
        } catch (Exception e) {
            return null;
            //logging
        }
    }

    public String  edit() {
        try {
            logger.log(Level.INFO, "Updating customer ID:{0}", authenticated.getId());
            authenticated.setPassword(SHA256.sha256(authenticated.getPassword()));
            cEJB.update((Customer) authenticated);
            return "View";
        } catch (Exception e) {
            return null;
        }
    }

    public String prepareList() {
        return "List";
    }

    public String prepareView(Customer a) {
        current = a;
        return "View";
    }

    public String prepareCreate() {
        current = new Customer();
        return "Create";
    }

    public String destroy(Customer a) {
        current = a;
        cEJB.delete(current);
        return "List";
    }

    public String destroy() {
        cEJB.delete(current);
        return "List";
    }


}
