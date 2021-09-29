package com.alkemy.ar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ar.model.Icon;

@Repository
public interface IconRepository extends JpaRepository<Icon, Long> {

	
	
}
