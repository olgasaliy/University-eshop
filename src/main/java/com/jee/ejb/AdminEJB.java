package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceAdmin;
import com.jee.entity.Administrator;
import com.jee.entity.Group;
import com.jee.entity.Person;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olgasaliy on 14.11.16.
 */
@Stateless
public class AdminEJB  implements InterfaceAdmin<Administrator> {

    @Resource
    private EJBContext context;

    @PersistenceContext(unitName = "MyEntityManager")
    private EntityManager em;


    private boolean lastAdministrator;

    @Override
    public Person getAdministratorByEmail(String email) {
        Query createNamedQuery = em.createNamedQuery("Person.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Person) createNamedQuery.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public Administrator add(Administrator obj) {
        Group adminGroup = (Group) em.createNamedQuery("Group.findByName")
                .setParameter("name", "ADMINS")
                .getSingleResult();
        obj.getGroupList().add(adminGroup);
        adminGroup.getPersonList().add(obj);
        em.persist(obj);
        em.merge(adminGroup);
        return obj;
//        em.persist(admin);
//        em.merge(adminGroup);
    }


    @Override
    public void delete(Administrator obj) {
        Group adminGroup = (Group) em.createNamedQuery("Group.findByName")
                .setParameter("name", "ADMINS")
                .getSingleResult();
        if (adminGroup.getPersonList().size() > 1) {
            adminGroup.getPersonList().remove(obj);
            em.remove(em.merge(obj));
            em.merge(adminGroup);
            lastAdministrator = false;
        } else {
            lastAdministrator = true;
        }
    }

    @Override
    public void delete(int id) {
        delete(find(id));
    }

    @Override
    public void update(Administrator obj) {
            em.merge(obj);
    }

    @Override
    public Administrator find(int id) {
        return em.find(getEntityClass(), id);
    }

    @Override
    public List<Administrator> getAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityClass()));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Class<Administrator> getEntityClass() {
        return Administrator.class;
    }

    @Override
    public void init() {
            Administrator admin = new Administrator();
            admin.setFirstname("Sasha");
            admin.setLastname("Ivanov");
            admin.setEmail("admin@gmail.com");
            admin.setCity("Kharkov");
            admin.setAddress("Nebesnaya 11");
            admin.setPassword("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
            add(admin);
    }


}
