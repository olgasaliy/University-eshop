package com.jee.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
@Table(name = "GROUPS")
@NamedQueries({
        @NamedQuery(name = "Group.findAll", query = "SELECT g FROM Group g"),
        @NamedQuery(name = "Group.findById", query = "SELECT g FROM Group g WHERE g.id = :id"),
        @NamedQuery(name = "Group.findByName", query = "SELECT g FROM Group g WHERE g.name = :name"),
        @NamedQuery(name = "Group.findByDescription", query = "SELECT g FROM Group g WHERE g.description = :description")})

public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message = "{groups.name}")
    @Column(name = "NAME")
    private String name;

    @Size(max = 300, message = "{groups.description}")
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groupList")
    @XmlTransient
    private List<Person> personList;

    public Group() {
    }

    public Group(Integer id) {
        this.id = id;
    }

    public Group(String name, String description) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return id != null ? id.equals(group.id) : group.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
