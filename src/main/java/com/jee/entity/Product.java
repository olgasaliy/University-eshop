package com.jee.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@Table(name = "PRODUCT")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
        @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
        @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
        @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
        @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category.id LIKE :category")})
//        @NamedQuery(name = "Product.findByImg", query = "SELECT p FROM Product p WHERE p.img = :img")})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Size(min = 3, max = 45)
    @Column(name = "NAME", nullable = false, length = 45)
    private String name;

    @Basic(optional = false)
    @DecimalMax(value = "9999.99")
    @Column(name = "PRICE", nullable = false)
    private double price;

    @Basic(optional = false)
    @Size(min = 3, max = 145)
    @Column(name = "DESCRIPTION", nullable = false, length = 45)
    private String description;


    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Category category;

    public Product() {
        category = new Category();
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id != null ? id.equals(product.id) : product.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
