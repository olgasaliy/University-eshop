package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceCategory;
import com.jee.entity.Category;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Stateless
@Local(InterfaceCategory.class)
public class CategoryEJB extends AbstractEJB<Category> implements InterfaceCategory<Category>{

    @Override
    public Category getByName(String name) {
        Query createNamedQuery = em.createNamedQuery("Category.findByName");
        createNamedQuery.setParameter("name", name);
        return (Category) createNamedQuery.getSingleResult();
    }

    @Override
    public void init() {
        Category category1 = new Category("Nikon", "camera nikon");
        Category category2 = new Category("Canon", "camera canon");
        Category category3 = new Category("Sony", "camera sony");
        Category category4 = new Category("Pentax", "camera pentax");
        add(category1);
        add(category2);
        add(category3);
        add(category4);
    }

    @Override
    public List<Category> getAll() {
        Query query = em.createNamedQuery("Category.findAll");
        return query.getResultList();
    }

    @Override
    public Class<Category> getEntityClass() {
        return Category.class;
    }
}
