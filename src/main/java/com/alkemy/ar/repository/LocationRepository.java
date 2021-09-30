package com.alkemy.ar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ar.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{


	
}
