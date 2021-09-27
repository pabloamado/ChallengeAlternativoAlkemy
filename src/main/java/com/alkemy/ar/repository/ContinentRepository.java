package com.alkemy.ar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ar.model.Continent;

@Repository
public interface ContinentRepository extends CrudRepository<Continent, Long>{
	
	

}
