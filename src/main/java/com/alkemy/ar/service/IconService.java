package com.alkemy.ar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.repository.IconRepository;
import com.alkemy.ar.repository.LocationRepository;

@Service
public class IconService {

	@Autowired
	private IconRepository iconRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	
	//obtendran el dto y lo convierten
}
