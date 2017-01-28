package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceProduct;
import com.jee.entity.Category;
import com.jee.entity.Product;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olgasaliy on 14.11.16.
 */
@Stateless
@Local(InterfaceProduct.class)
public class ProductEJB extends AbstractEJB<Product> implements InterfaceProduct<Product> {

    @Override
    public void init() {
        Query query = em.createNamedQuery("Category.findAll");
        List <Category> list = query.getResultList();
        Product n1 = new Product();
        n1.setCategory(list.get(0));
        n1.setDescription("Nikon D5200");
        n1.setName("Nikon D5200");
        n1.setPrice(700);
        Product n2 = new Product();
        n2.setCategory(list.get(0));
        n2.setDescription("Nikon D300s");
        n2.setName("Nikon D300s");
        n2.setPrice(1200);

        Product c1 = new Product();
        c1.setCategory(list.get(1));
        c1.setDescription("Canon 600D");
        c1.setName("Canon 600D");
        c1.setPrice(500);
        Product c2 = new Product();
        c2.setCategory(list.get(1));
        c2.setDescription("Canon 5D");
        c2.setName("Canon 5D");
        c2.setPrice(3000);

        Product s1 = new Product();
        s1.setCategory(list.get(2));
        s1.setDescription("Sony Alpha 3500");
        s1.setName("Sony Alpha 3500");
        s1.setPrice(400);
        Product s2 = new Product();
        s2.setCategory(list.get(2));
        s2.setDescription("Sony RX10");
        s2.setName("Sony RX10");
        s2.setPrice(2000);

        Product p1 = new Product();
        p1.setCategory(list.get(3));
        p1.setDescription("Pentax K-3");
        p1.setName("Pentax K-3");
        p1.setPrice(2300);
        Product p2 = new Product();
        p2.setCategory(list.get(3));
        p2.setDescription("Pentax K-50");
        p2.setName("Pentax K-50");
        p2.setPrice(400);
        
        add(n1);
        add(n2);
        add(c1);
        add(c2);
        add(s1);
        add(s2);
        add(p1);
        add(p2);
    }



    @Override
    public List<Product> findByCategory(int categoryId) {
       return em.createNamedQuery("Product.findByCategory")
               .setParameter("category", categoryId)
               .getResultList();
    }

    @Override
    public List<Product> getAll() {
        Query query = em.createNamedQuery("Product.findAll");
        return query.getResultList();
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }
}
