package com.jee.controller;


import com.jee.ejb.interfaces.InterfaceCategory;
import com.jee.ejb.interfaces.InterfaceProduct;
import com.jee.entity.Category;
import com.jee.entity.Product;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by olgasaliy on 14.11.16.
 */


@Named("productController")
@SessionScoped
public class ProductController implements Serializable {

    private final static Logger logger = Logger.getLogger(ProductController.class.getCanonicalName());

    private int categoryId;
    private Product current;
    private String category;

    @EJB
    private InterfaceProduct<Product> pEJB;

    @EJB
    private InterfaceCategory<Category> cEJB;




//    @PostConstruct
//    public void init () {
//        current = new Product();
//    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCurrent(Product current) {
        this.current = current;
    }

    public InterfaceProduct<Product> getpEJB() {
        return pEJB;
    }

    public void setpEJB(InterfaceProduct<Product> pEJB) {
        this.pEJB = pEJB;
    }


    public ProductController(){}

   // public void add() {
//        pEJB.add(current);
//    }

    public String create () {
        try {

            current.setCategory(cEJB.getByName(category));
            pEJB.add(current);
            logger.log(Level.FINEST, "Creating product {0}", current.getName());
            return "List";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Product getCurrent() {
        if (current == null) {
            current = new Product();
        }

        return current;
    }

    public String prepareView(Product o) {
        current = o;
        return "View";
    }

    public String prepareCreate() {
        current = new Product();
        return "Create?faces-redirect=true";
    }

    public String showAll() {
        categoryId = 0;

        return "product/List";
    }

    public String destroy() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int Id = Integer.parseInt(params.get("id"));
        current = pEJB.find(Id);
        logger.log(Level.FINEST, "Deleting product {0}", current.getName());
//        current.getCategory().getProductList().remove(current);
        pEJB.delete(current);

        return "List";
    }

    public String destroy(Product p) {
//        p.getCategory().getProductList().remove(p);
        //current = p;
        pEJB.delete(p);

        return "List";
    }




    public List<Product> getAllItems () {
        return pEJB.getAll();
    }

    public int getCountItems () {
        return getAllItems().size();
    }

    public List<Product> getAll () {
        return pEJB.findByCategory(categoryId);
    }

    public int getCount () {
        return getAll().size();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String prepareList() {
        return "List?faces-redirect=true";
    }

}
