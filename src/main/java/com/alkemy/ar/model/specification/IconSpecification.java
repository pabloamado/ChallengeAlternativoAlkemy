package com.alkemy.ar.model.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
@Component
public class IconSpecification {

	public Specification<Icon> getByFilters(String name, LocalDate date, Set<Long> cities) {
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
            
            if(date!=null) {
            	
            	if (StringUtils.hasLength(date.toString())) {
              
            		/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            		LocalDate myDate = LocalDate.parse(date, formatter);*/
            		predicates.add(
	                        criteriaBuilder.equal(root.get("creationDate"), date)
	                );
            	}
            }

            if (!CollectionUtils.isEmpty(cities)) {
                Join<Location, Icon> join = root.join("locations", JoinType.INNER);
                Expression<String> citiesId = join.get("lId");
                predicates.add(citiesId.in(cities));
            }

            // Remove duplucates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
