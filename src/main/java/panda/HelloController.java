package panda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private CustomerDao customerDao;

    @PersistenceContext
    EntityManager em;

    @ResponseBody
    @RequestMapping("/customer")
    public List<Customer> customer() {

//        String[] beanNames = appContext.getBeanDefinitionNames();
        //display all beans
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }

        TypedQuery<Customer> q = em.createQuery("Select c from Customer c", Customer.class);

        return q.getResultList();
    }

    @ResponseBody
    @RequestMapping("/address")
    public List<Address> address() {

        TypedQuery<Address> q = em.createQuery("Select a from Address a", Address.class);

        return q.getResultList();
    }
}
