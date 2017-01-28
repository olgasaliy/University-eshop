package com.jee.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@Table(name = "ORDER_STATUS")
@NamedQueries({
        @NamedQuery(name = "OrderStatus.findAll", query = "SELECT o FROM OrderStatus o"),
        @NamedQuery(name = "OrderStatus.findById", query = "SELECT o FROM OrderStatus o WHERE o.id = :id"),
        @NamedQuery(name = "OrderStatus.findByStatus", query = "SELECT o FROM OrderStatus o WHERE o.status = :status")})
public class OrderStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "STATUS")
    @Size(min = 3, max = 45)
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderStatus")
    private List<CustomerOrder> customerOrderList;

    @Basic(optional = true)
    @Size(min = 0, max = 200, message = "Description has maximum of 200 characters")
    @Column(name = "DESCRIPTION")
    private String description;

    public OrderStatus() {

    }

    public OrderStatus(Integer id) {
        this.id = id;
    }

    public OrderStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
