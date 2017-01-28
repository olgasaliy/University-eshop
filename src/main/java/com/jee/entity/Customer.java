package com.jee.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
        @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
        @NamedQuery(name = "Customer.findByFirstname", query = "SELECT c FROM Customer c WHERE c.firstname = :firstname"),
        @NamedQuery(name = "Customer.findByLastname", query = "SELECT c FROM Customer c WHERE c.lastname = :lastname"),
        @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
        @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address"),
        @NamedQuery(name = "Customer.findByCity", query = "SELECT c FROM Customer c WHERE c.city = :city")})
public class Customer extends Person implements Serializable {
//    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CustomerOrder> customerOrderList;

    public Customer() {
        this.customerOrderList = new ArrayList<CustomerOrder>();
        this.groupList = new ArrayList<Group>();
    }

    public Customer(Integer id) {
        this.id = id;
        this.customerOrderList = new ArrayList<CustomerOrder>();
        this.groupList = new ArrayList<Group>();
    }

    public Customer(Integer id, String firstname, String lastname, String email, String address, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.customerOrderList = new ArrayList<CustomerOrder>();
        this.groupList = new ArrayList<Group>();
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
