package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceEJB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractEJB<T> implements InterfaceEJB<T> {

    @PersistenceContext(unitName = "MyEntityManager")
    protected EntityManager em;

    @Override
    public T add(T obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public void delete(int id) {
        em.remove(find(id));
    }

    @Override

    public void delete(T obj) {
        em.remove(em.merge(obj));
    }

    @Override
    public void update(T obj) {
        em.merge(obj);
    }

    @Override

    public T find(int id) {
        return em.find(getEntityClass(), id);
    }

    public List<T> getAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityClass()));
        return em.createQuery(cq).getResultList();
    }
}
