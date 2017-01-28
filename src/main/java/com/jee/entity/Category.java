package com.jee.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@Table(name = "CATEGORY")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
        @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id"),
        @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
        @NamedQuery(name = "Category.findByTags", query = "SELECT c FROM Category c WHERE c.tags = :tags")})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Size(min = 3, max = 45)
    @Column(name = "NAME", nullable = false, length = 45)
    private String name;

    @Size(min = 3, max = 45)
    @Column(name = "TAGS", length = 45)
    private String tags;

    @OneToMany( mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> productList;

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }

    public Category(String name, String tags) {
        this.name = name;
        this.productList = new ArrayList<Product>();
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id != null ? id.equals(category.id) : category.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
