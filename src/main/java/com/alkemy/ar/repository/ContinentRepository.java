package com.alkemy.ar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ar.model.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long>{
	
	

}
