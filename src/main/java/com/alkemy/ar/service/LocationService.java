package com.alkemy.ar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.repository.ContinentRepository;
import com.alkemy.ar.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private ContinentRepository continentRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	//obtendran el dto y lo convierten
}
