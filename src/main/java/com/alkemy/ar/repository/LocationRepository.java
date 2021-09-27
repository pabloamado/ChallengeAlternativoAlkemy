package com.alkemy.ar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ar.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>{

}
