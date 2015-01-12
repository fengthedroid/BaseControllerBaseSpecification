package panda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private CustomerDao customerDao;

    @PersistenceContext
    EntityManager em;

    @ResponseBody
    @RequestMapping(value = "/")
    public String hello() {
        return "Ok";
    }


    @ResponseBody
    @RequestMapping(value = "/customer", params = {"addressID"})
    public List<Customer> customer(@RequestParam("addressID") String addrID) {
        return customerDao.findAll();
    }

    @ResponseBody
    @RequestMapping("/address")
    public List<Address> address() {

        TypedQuery<Address> q = em.createQuery("Select a from Address a", Address.class);

        return q.getResultList();
    }
}
