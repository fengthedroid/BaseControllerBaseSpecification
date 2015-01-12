package panda;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Feng on 11-Jan-15.
 */

public class SpecBuilder<T> {

    public Specification<T> filterWithOptions(final Map<String, String> params, Class<T> tClass) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return getTPredicate(root, query, cb, params, tClass);
            }
        };
    }

    public Predicate getTPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, Map<String, String> params, Class<T> tClass) {
        List<Predicate> predicates = new ArrayList<>();
        query.distinct(true);

        for (String field : params.keySet()) {
            if (field.contains(".")) {
                String[] compositeField = field.split("\\.");
                if (compositeField.length == 2) {
                    predicates.add(cb.equal(root.get(compositeField[0]).get(compositeField[1]), params.get(field)));
                }
            } else {
                try {
                    if (tClass.getDeclaredField(field) != null) {
                        predicates.add(cb.equal(root.get(field), params.get(field)));
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
