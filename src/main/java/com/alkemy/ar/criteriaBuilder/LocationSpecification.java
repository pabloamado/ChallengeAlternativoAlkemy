package com.alkemy.ar.criteriaBuilder;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Location;

@Component
public class LocationSpecification {

	public Specification<Location> getByFilters(String name, Long continentId, String order) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(name)) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("denomination")),
                                "%" + name.toLowerCase() + "%"
                        )
                );
            }

            if (continentId!=null) {
                Join<Continent, Location> join = root.join("continent", JoinType.INNER);
                Expression<String> idContinent = join.get("cId");
                predicates.add(idContinent.in(continentId));
            }

            // Remove duplucates
            query.distinct(true);

            // Order resolver
            String orderByField = "denomination";
            query.orderBy(
                    order.equalsIgnoreCase("asc") ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
