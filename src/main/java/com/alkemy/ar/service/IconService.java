package com.alkemy.ar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.repository.IconRepository;
import com.alkemy.ar.repository.LocationRepository;

@Service
public class IconService {

	@Autowired
	private IconRepository iconRepository;
	
	
	@Autowired
	private LocationRepository locationRepository;
	
	public IconDto save(IconDto iconDto) {
		
		return null;
	}
	
	
	public  boolean delete(Long id) {
		
		
		return false;
	}
	
	public IconDto update (IconDto iconDto) {
		
		return null;
	}
	
	public IconDto get(Long id) {
		
		return null;
	}
	
	public List<IconDto> getAll(){
		
		return null;
	}
	
	
	public IconDto getByName(String name) {
		
		return null;
	}
	
	public IconDto getByDate(Date date) {
		
		return null;
	}
	
	public IconDto getByLocation(Long locationId) {
	
	return null;
}
	
}
