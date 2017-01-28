package com.jee.ejb;

import com.jee.ejb.interfaces.InterfaceCustomer;
import com.jee.entity.Customer;
import com.jee.entity.Group;
import com.jee.entity.Person;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olgasaliy on 14.11.16.
 */
@Stateless
@Local(InterfaceCustomer.class)
public class CustomerEJB  implements InterfaceCustomer<Customer> {

    @Resource
    private EJBContext context;

    @PersistenceContext(unitName = "MyEntityManager")
    private EntityManager em;

    @Override
    public Person getUserByEmail(String email) {
        Query createNamedQuery = em.createNamedQuery("Person.findByEmail");

        createNamedQuery.setParameter("email", email);

        if (createNamedQuery.getResultList().size() > 0) {
            return (Person) createNamedQuery.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public Customer add(Customer obj) {
        Group userGroup = (Group) em.createNamedQuery("Group.findByName").setParameter("name", "USERS").getSingleResult();
        obj.getGroupList().add(userGroup);
        userGroup.getPersonList().add(obj);
        em.persist(obj);
        em.merge(userGroup);
        return obj;
//        em.merge(userGroup);
    }

    @Override
    public void delete(int id) {
        delete(find(id));
    }

    @Override
    public void update(Customer obj) {
        em.merge(obj);
    }

    @Override
    public Customer find(int id) {
        return em.find(getEntityClass(), id);
    }

    @Override
    public void delete(Customer obj) {
        Group userGroup = (Group) em.createNamedQuery("Group.findByName").setParameter("name", "USERS").getSingleResult();
        userGroup.getPersonList().remove(obj);
        em.remove(em.merge(obj));
        em.merge(userGroup);
//        em.remove(em.merge(user));
//        em.merge(userGroup);
    }

    @Override
    public List<Customer> getAll() {
        Query query = em.createNamedQuery("Customer.findAll");
        return query.getResultList();
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public void init() {
        Customer customer = new Customer();
        customer.setFirstname("Olga");
        customer.setLastname("Salij");
        customer.setEmail("olia.white.1996@gmail.com");
        customer.setCity("Kiev");
        customer.setPassword("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
        customer.setAddress("Dashavskaya 25");
        add(customer);
    }
}
