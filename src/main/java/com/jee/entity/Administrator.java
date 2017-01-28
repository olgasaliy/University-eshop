package com.jee.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by olgasaliy on 12.11.16.
 */
@Entity
public class Administrator extends Person implements Serializable {

    public Administrator() {
        this.groupList = new ArrayList<Group>();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
