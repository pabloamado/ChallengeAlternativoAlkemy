package com.alkemy.ar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ar.model.Icon;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

@Repository
public interface IconRepository extends JpaRepository<Icon, Long>, JpaSpecificationExecutor<Icon> {

	List<Icon> findAll(Specification<Icon> spec);
		
}
	

