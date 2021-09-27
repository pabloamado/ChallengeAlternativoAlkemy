package com.alkemy.ar.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ar.model.Icon;

@Repository
public interface IconRepository extends CrudRepository<Icon, Long> {

	
	
}
