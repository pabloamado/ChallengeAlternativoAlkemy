package com.alkemy.ar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.model.Location;
import com.alkemy.ar.service.LocationService;

@RestController
@RequestMapping("/cities")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@GetMapping
	public ResponseEntity<Location> getLocations(){
		
		//buscar todas las localizaciones y mostrar solo img, denomination y population, hacer un segundo dto
		
		return null;
	}
	
	@PostMapping
	public ResponseEntity<Location> saveLocation(@RequestBody LocationDto locationDto){
		
		//guardar registro location con una lista de iconos
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Location> getLocation(@PathVariable Long id){
		
		//buscar una localizacion y devuelve todos sus campos junto a la lista de iconos asociados
		
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Location> deleteLocation(@PathVariable Long id){
		
		//borrar  registro en location y solo los icons que son referenciados unicamente por este location
		return null;
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody LocationDto locationDto){
		
		//buscar y actualizar el registro location,
		return null;
	}
	
	@GetMapping(params="name")
	public ResponseEntity<Location> getLocationByName(@RequestParam String name){
		
		//busca la localizacio por nombre de denominacion y muestra solo img, denomination y population
		
		return null;
	}
	
	@GetMapping(params="continent")
	public ResponseEntity<Location> getLocationsByContinent(@RequestParam Long continent ){
		
		//buscar todas las localizaciones y mostrar solo img, denomination y population, por continente, 
		//es un group by order asc
		
		return null;
	}
	
	@GetMapping(params="order")
	public ResponseEntity<Location> getLocationsByOrder(@RequestParam String order){
		
		//buscar todas las localizaciones y mostrar solo img, denomination y population, por orden desc o asc
		
		return null;
	}
	
}
