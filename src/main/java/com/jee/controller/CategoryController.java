package com.jee.controller;


import com.jee.ejb.interfaces.InterfaceCategory;
import com.jee.entity.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by olgasaliy on 14.11.16.
 */

@ManagedBean(name = "categoryController")
@RequestScoped
public class CategoryController implements Serializable {


    @EJB(beanName = "CategoryEJB")
    private InterfaceCategory<Category> cEJB;


    private Category category;

    @PostConstruct
    public void init () {
        category = new Category();
    }

    public void add (Category c) {
        try {
            cEJB.add(c);
            //loggigng
        } catch (Exception e) {
            //logging
        }

    }

    public  void delete (int id) {
        cEJB.delete(id);
    }

    public List<Category> getAll () {
        return cEJB.getAll();
    }

    public int getCount () {
        return getAll().size();
    }

    public InterfaceCategory<Category> getcEJB() {
        return cEJB;
    }

    public void setcEJB(InterfaceCategory<Category> cEJB) {
        this.cEJB = cEJB;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
