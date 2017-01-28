package com.jee.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by olgasaliy on 12.11.16.
 */
//пара полей (id заказа и id товара) являются индентификатором для класса orderDetail
@Embeddable
public class EmbeddedPK implements Serializable{

    @Basic(optional = false)
    @Column(name = "ORDER_ID")
    private int orderId;

    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private int productId;

    public EmbeddedPK() {
    }

    public EmbeddedPK(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
