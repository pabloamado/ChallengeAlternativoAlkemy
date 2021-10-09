package com.alkemy.ar.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.alkemy.ar.dto.LocationDtoGetAll;
import com.alkemy.ar.service.LocationService;

@RestController
@RequestMapping("/cities")
public class LocationController {

	@Autowired
	private LocationService locationService;

	//testeado
	@PostMapping
	public ResponseEntity<?> saveLocation(@RequestBody LocationDto locationDto) {

		LocationDto location = locationService.save(locationDto);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(location);

	}

	//testeado
	@GetMapping("/{idLocation}")
	public ResponseEntity<?> getLocation(@PathVariable Long idLocation) {

		LocationDto location = locationService.get(idLocation);
			
		return ResponseEntity.ok(location);

	}

	//testeado
	@GetMapping
	public ResponseEntity<?> getLocations() {
			
		List<LocationDtoGetAll> locations=locationService.getAll();
			
		return ResponseEntity.ok(locations);
	
	}
	
	//testeado
		@GetMapping("/cities")
	public ResponseEntity<?> getLocationByNameAndFilters(@RequestParam(required = false) String name,
	       @RequestParam(required = false) Long continent,
	       @RequestParam(required = false, defaultValue = "ASC") String order) {

		List<LocationDto> locationsFiltered=locationService.getFilteredLocations(name,continent,order);
			
		return ResponseEntity.ok(locationsFiltered);
			
		}

	//testeado
	@PutMapping("/{idLocation}")
	public ResponseEntity<?> updateLocation(@PathVariable Long idLocation, @RequestBody LocationDto locationDto) {

		LocationDto location = locationService.update(idLocation, locationDto);
				
		return ResponseEntity.ok(location);

	}

	//testeado borro el pais, borro los registros de la join table pero no borro los iconos
	@DeleteMapping("/{idLocation}")
	public ResponseEntity<?> deleteLocation(@PathVariable Long idLocation) {
		
		locationService.delete(idLocation);
		
		return ResponseEntity.ok().build();
			
	}
	
	
	/* IGNORAR   
	@GetMapping(params = "name")  // exception NoResultException
	public ResponseEntity<?> getLocationByName(@RequestParam String name) {
	
			LocationDto location=locationService.getByName(name);
			
			return  ResponseEntity.ok(location);
			
	}
	
	//testeado
	@GetMapping(params = "continent")
	public ResponseEntity<?> getLocationsByContinent(@RequestParam String continent) {
	
			Long continentId = Long.valueOf(continent);
			
			List<LocationDtoGetAll> locations=locationService.getByContinent(continentId);
			
			return ResponseEntity.ok(locations);
	}

	//testeado
	@GetMapping(params = "order")
	public ResponseEntity<?> getLocationsByOrder(@RequestParam String order) {

		if(order.equalsIgnoreCase("desc") || order.equalsIgnoreCase("asc")) {
						
			List<LocationDtoGetAll> locations=locationService.getAllByOrder(order);
				
			return ResponseEntity.ok(locations);
				
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(
				ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION.toString()));
		
	}
*/

}
