package panda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Elisabeth on 2015-01-12.
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController<Customer> {

    @Autowired
    CustomerDao customerDao;

    /**
     * Initialize the controller variables in the child class *
     *
     * @param repo
     */
    @Autowired
    public CustomerController(JpaSpecificationExecutor<Customer> repo) {
        super(repo, Customer.class);
    }
}
