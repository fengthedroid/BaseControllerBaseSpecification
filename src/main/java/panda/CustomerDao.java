package panda;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class CustomerDao {

    @PersistenceContext
    EntityManager em;

    public List<Customer> findAll() {
        TypedQuery<Customer> q = em.createQuery("SELECT c from Customer c", Customer.class);
        return q.getResultList();
    }


    @Transactional
    public void save(Customer customer) {
        em.persist(customer);
    }

    public Customer findOne(long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.where(cb.equal(root.get(Customer_.id), id)).distinct(true);
        TypedQuery<Customer> q = em.createQuery(cq);
        return q.getSingleResult();
    }
}