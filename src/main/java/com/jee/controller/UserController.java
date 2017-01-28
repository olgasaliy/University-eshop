package com.jee.controller;


import com.jee.additional.Authenticated;
import com.jee.ejb.interfaces.InterfaceCustomer;
import com.jee.entity.Customer;
import com.jee.entity.Group;
import com.jee.entity.Person;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by olgasaliy on 14.11.16.
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    Person user;
    @EJB(beanName = "CustomerEJB")
    private InterfaceCustomer<Customer> cEJB;
    private String username;
    private String password;
    private boolean message;
//    @Inject
//    CustomerController customerController;

    public UserController() {
        message = false;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String result;

        try {
            request.login(this.getUsername(), this.getPassword());


            this.user = cEJB.getUserByEmail(getUsername());
            this.getAuthenticatedUser();
            message = false;
            return goAdmin();

        } catch (ServletException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            message = true;
            result = "login";
        }

        return result;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        message = false;
        try {
            this.user = null;

            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();



        } catch (ServletException ex) {


            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            return "/index";
        }
    }

    public boolean getMessage() {
        return message;
    }

    public void setMessage(boolean val) {
        this.message = val;
    }

    //    public CustomerEJB getcEJB() {
//        return cEJB;
//    }

    public @Produces @Authenticated
    Person getAuthenticatedUser() {
        return user;
    }

    public boolean isLogged() {
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
            user = cEJB.getUserByEmail(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString());
            return true;
        } else {
            return false;
        }
    }

    public boolean isAdmin() {
        if (isLogged())
        {for (Group g : user.getGroupList()) {
            if (g.getName().equals("ADMINS")) {
                return true;
            }
        }
        return false;}
        else  return false;
    }

    public void turnOff () {
        message = false;
    }

    public String goAdmin() {
        if (isAdmin()) {
            return "/admin/index";
        } else {
            return "index";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getUser() {
        return user;
    }

}
