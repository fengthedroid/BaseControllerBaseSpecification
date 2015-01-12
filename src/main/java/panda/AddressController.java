package panda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Elisabeth on 2015-01-12.
 */
@Controller
@RequestMapping(value = "/address")
public class AddressController extends BaseController<Address> {

    @Autowired
    AddressDao addressDao;

    /**
     * Initialize the controller variables in the child class *
     *
     * @param repo
     */
    @Autowired
    public AddressController(JpaSpecificationExecutor<Address> repo) {
        super(repo, Address.class);
    }
}
