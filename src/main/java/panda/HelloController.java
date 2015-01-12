package panda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AddressDao addressDao;
    private SingularAttribute<Customer, Address> address;

    @ResponseBody
    @RequestMapping(value = "/")
    public String hello() {
        return "Ok";
    }

    @ResponseBody
    @RequestMapping(value = "/customer")
    public List<Customer> customer(@RequestParam Map<String, String> allRequestParams) {
        return customerDao.findAll(new SpecBuilder<Customer>().filterWithOptions(allRequestParams, Customer.class));
    }

    @ResponseBody
    @RequestMapping("/address")
    public List<Address> address(@RequestParam Map<String, String> allRequestParams) {
        return addressDao.findAll(new SpecBuilder<Address>().filterWithOptions(allRequestParams, Address.class));
    }
}
