package panda;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Feng on 11-Jan-15.
 */

public abstract class BaseController<T> {

    /**
     * These variables must be override in the child class *
     */
    protected JpaSpecificationExecutor<T> repository;
    protected Class<T> tClass;

    /**
     * Initialize the controller variables in the child class *
     */
    public BaseController(JpaSpecificationExecutor<T> repo, Class<T> tclass) {
        this.repository = repo;
        this.tClass = tclass;
    };

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<T> get(@RequestParam Map<String, String> allRequestParams) {
        Specification<T> filterOptions = filterWithOptions(allRequestParams, tClass);
        System.out.println(filterOptions.toString());
        return repository.findAll(filterOptions);
    }


    /**
     * @param params
     * @param tClass
     * @return
     */
    Specification<T> filterWithOptions(final Map<String, String> params, final Class<T> tClass) {
        return (root, query, cb) -> {
            try {
                List<Predicate> predicates = new ArrayList<>();
                for (String field : params.keySet()) {
                    if (field.contains(".")) {
                        String[] compositeField = field.split("\\.");
                        if (compositeField.length == 2
                                && tClass.getDeclaredField(compositeField[0]).getType().getDeclaredField(compositeField[1]) != null) {
                            predicates.add(cb.equal(root.get(compositeField[0]).get(compositeField[1]), params.get(field)));
                        }
                    } else {

                        if (tClass.getDeclaredField(field) != null) {
                            predicates.add(cb.equal(root.get(field), params.get(field)));
                        }

                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}
