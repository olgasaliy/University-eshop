package com.jee.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@Table(name = "CUSTOMER_ORDER")
@NamedQueries({
        @NamedQuery(name = "CustomerOrder.findAll", query = "SELECT c FROM CustomerOrder c"),
        @NamedQuery(name = "CustomerOrder.findById", query = "SELECT c FROM CustomerOrder c WHERE c.id = :id"),
        @NamedQuery(name = "CustomerOrder.findByStatus", query = "SELECT c FROM CustomerOrder c, OrderStatus s WHERE c.orderStatus = s and s.status = :status"),
        @NamedQuery(name = "CustomerOrder.findByStatusId", query = "SELECT c FROM CustomerOrder c, OrderStatus s WHERE c.orderStatus = s and s.id = :id"),
        @NamedQuery(name = "CustomerOrder.findByCustomerId", query = "SELECT c FROM CustomerOrder c WHERE c.customer.id = :id"),
        @NamedQuery(name = "CustomerOrder.findByAmount", query = "SELECT c FROM CustomerOrder c WHERE c.amount = :amount"),
        @NamedQuery(name = "CustomerOrder.findByDateCreated", query = "SELECT c FROM CustomerOrder c WHERE c.dateCreated = :dateCreated")})
public class CustomerOrder implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private double amount;

    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrderStatus orderStatus;

    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Customer customer;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customerOrder")
    private List<OrderDetail> orderDetailList;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer id) {
        this.id = id;
    }

    public CustomerOrder(Integer id, double amount, Date dateCreated) {
        this.id = id;
        this.amount = amount;
        this.dateCreated = dateCreated;
    }

    public boolean isNotConfirmed () {
        return getOrderStatus().getId()==1;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer =  (Customer) customer;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerOrder that = (CustomerOrder) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
