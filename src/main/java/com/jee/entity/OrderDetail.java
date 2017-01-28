package com.jee.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@Table(name = "ORDER_DETAIL")
@NamedQueries({
        @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o"),
        @NamedQuery(name = "OrderDetail.findByOrderId", query = "SELECT o FROM OrderDetail o WHERE o.pk.orderId = :orderId"),
        @NamedQuery(name = "OrderDetail.findByProductId", query = "SELECT o FROM OrderDetail o WHERE o.pk.productId = :productId"),
        @NamedQuery(name = "OrderDetail.findByQty", query = "SELECT o FROM OrderDetail o WHERE o.qty = :qty")})

public class OrderDetail implements Serializable {

    @EmbeddedId
    protected EmbeddedPK pk;

    @Basic(optional = false)
    @Column(name = "QTY")
    private int qty;

    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomerOrder customerOrder;

    public OrderDetail() {
    }

    public OrderDetail(EmbeddedPK PK) {
        this.pk = PK;
    }

    public OrderDetail(EmbeddedPK PK, int qty) {
        this.pk = PK;
        this.qty = qty;
    }

    public EmbeddedPK getPk() {
        return pk;
    }

    public void setPk(EmbeddedPK pk) {
        this.pk = pk;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        return pk != null ? pk.equals(that.pk) : that.pk == null;

    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }
}
