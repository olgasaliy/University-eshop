package com.jee.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
        @NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM Person p WHERE p.firstname = :firstname"),
        @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname"),
        @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
        @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address"),
        @NamedQuery(name = "Person.findByCity", query = "SELECT p FROM Person p WHERE p.city = :city")})
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    protected Integer id;

    @Basic(optional = false)
    @Size(min = 3, max = 50)
    @Column(name = "FIRSTNAME")
    protected String firstname;

    @Basic(optional = false)
    @Size(min = 3, max = 100)
    @Column(name = "LASTNAME")
    protected String lastname;

    @Pattern(regexp = ".+@.+\\.[a-z]+")
    @Size(min = 3, max = 45)
    @Basic(optional = false)
    @Column(name = "EMAIL")
    protected String email;

    @Basic(optional = false)
    @Size(min = 3, max = 45)
    @Column(name = "ADDRESS")
    protected String address;

    @Basic(optional = false)
    @Size(min = 3, max = 45)
    @Column(name = "CITY")
    protected String city;

    @Basic(optional = false)
    @Size(min = 4, max = 100)
    @Column(name = "PASSWORD")
    protected String password;

    @JoinTable(name = "PERSON_GROUPS",
            joinColumns = {@JoinColumn(name = "PERSON_EMAIL", referencedColumnName = "EMAIL")},
            inverseJoinColumns = {@JoinColumn(name = "GROUPS_NAME", referencedColumnName = "NAME")})
    @ManyToMany (fetch = FetchType.EAGER)
    protected List <Group> groupList;

    public Person() {
        this.groupList = new ArrayList<Group>();
    }

    public Person(Integer id) {
        this.id = id;
        this.groupList = new ArrayList<Group>();
    }

    public Person(Integer id,
                  String firstName,
                  String lastName,
                  String email,
                  String address,
                  String city) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.groupList = new ArrayList<Group>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id != null ? !id.equals(person.id) : person.id != null;


    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }
}
