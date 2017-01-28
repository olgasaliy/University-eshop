package com.jee.controller;


import com.jee.additional.SHA256;
import com.jee.ejb.interfaces.InterfaceAdmin;
import com.jee.entity.Administrator;
import com.jee.entity.Person;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "administratorController")
@SessionScoped
public class AdministratorController implements Serializable {


    private Administrator current;
    @EJB
    private InterfaceAdmin<Administrator> aEJB;

    private static final Logger logger = Logger.getLogger(CustomerController.class.getCanonicalName());

    public Administrator getCurrent() {
        return current;
    }

    public void setCurrent(Administrator current) {
        this.current = current;
    }

    public InterfaceAdmin<Administrator> getaEJB() {
        return aEJB;
    }

    public void setaEJB(InterfaceAdmin<Administrator> aEJB) {
        this.aEJB = aEJB;
    }

    public AdministratorController() {
    }

    public Administrator getSelected() {
        if (current == null) {
            current = new Administrator();
        }
        return current;
    }

    public List<Administrator> getAll () {
        return aEJB.getAll();
    }

    public int getCount () {
        return getAll().size();
    }

    private boolean isAdministratorDuplicated(Person p) {
        return (aEJB.getAdministratorByEmail(p.getEmail()) != null);
    }


    public String create() {
        try {
            if (!isAdministratorDuplicated(current)) {
                current.setPassword(SHA256.sha256(current.getPassword()));
                aEJB.add(current);
                return "View";
            } else {
               //logging

            }
            return "View";
        } catch (Exception e) {
            //logging
            return null;
        }
    }


    public String prepareList() {
        return "List";
    }

    public String prepareView(Administrator a) {
        current = a;
        return "View";
    }

    public String prepareCreate() {
        current = new Administrator();
        return "Create";
    }


    public String destroy(Administrator a) {
        current = a;
        aEJB.delete(current);
        return "List";
    }

    public String destroy() {
        aEJB.delete(current);
        return "List";
    }







}
