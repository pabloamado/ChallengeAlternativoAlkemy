package com.alkemy.ar.controller;


import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
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
import com.alkemy.ar.error.CustomError;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.service.LocationService;
import com.alkemy.ar.validator.DtoValidator;

@RestController
@RequestMapping("/cities")
public class LocationController {

	@Autowired
	private LocationService locationService;

	//testeado
	@PostMapping
	public ResponseEntity<?> saveLocation(@RequestBody LocationDto locationDto) {

		if (DtoValidator.validDtoProperties(locationDto)) {

			try {

				LocationDto location = locationService.save(locationDto);

				return ResponseEntity.status(HttpStatus.ACCEPTED).body(location);

			} catch (LocationException e) {

				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new CustomError(e.getMessage()));

			} catch (IllegalArgumentException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));

			} catch (Exception e) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(
						e.getMessage()));
			}

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new CustomError(ErrorMsg.WRONG_ENTITY_PARAMETERS_EXCEPTION.toString()));

	}

	//testeado
	@GetMapping("/{idLocation}")
	public ResponseEntity<?> getLocation(@PathVariable String idLocation) {

		try {

			Long id = Long.valueOf(idLocation);

			LocationDto location = locationService.get(id);
			
			return ResponseEntity.ok(location);

		} catch (NumberFormatException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomError(ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION + " " + e.getMessage()));

		} catch (EntityNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(e.getMessage()));
			
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
			
		}

	}

	//testeado
	@GetMapping
	public ResponseEntity<?> getLocations() {

		try {
			
			List<LocationDtoGetAll> locations=locationService.getAll();
			
			return ResponseEntity.ok(locations);
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
		}

	}

	//testeado
	@GetMapping(params = "name")
	public ResponseEntity<?> getLocationByName(@RequestParam String name) {

		try {
			
			LocationDto location=locationService.getByName(name);
			
			return  ResponseEntity.ok(location);
			
		}catch(NoResultException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
			
		}
		
	}

	//testeado
	@GetMapping(params = "continent")
	public ResponseEntity<?> getLocationsByContinent(@RequestParam String continent) {

		try {

			Long continentId = Long.valueOf(continent);
			
			List<LocationDtoGetAll> locations=locationService.getByContinent(continentId);
			
			return ResponseEntity.ok(locations);
			
		}catch(NumberFormatException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomError(ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION + " " + e.getMessage()));

		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
			
		}

	}

	//testeado
	@GetMapping(params = "order")
	public ResponseEntity<?> getLocationsByOrder(@RequestParam String order) {

		if(order.equalsIgnoreCase("desc") || order.equalsIgnoreCase("asc")) {
			
			try {
					
				List<LocationDtoGetAll> locations=locationService.getAllByOrder(order);
				
				return ResponseEntity.ok(locations);
				
			} catch (Exception e) {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
				
			}
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(
				ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION.toString()));
		
	}

	//testeado
	@PutMapping("/{idLocation}")
	public ResponseEntity<?> updateLocation(@PathVariable String idLocation, @RequestBody LocationDto locationDto) {

		if (DtoValidator.validDtoPropertiesToUpdate(locationDto)) {

			try {
				
				Long id = Long.valueOf(idLocation);

				LocationDto location = locationService.update(id, locationDto);
				
				return ResponseEntity.ok(location);

			} catch (NumberFormatException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new CustomError(ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION + " " + e.getMessage()));

			} catch (EntityNotFoundException e) {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(e.getMessage()));
				
			}catch (IllegalArgumentException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));
				
			}catch (Exception e) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
			}

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
				(new CustomError(ErrorMsg.WRONG_ENTITY_PARAMETERS_EXCEPTION.toString()));
	}

	//testeado borro el pais, borro los registros de la join table pero no borro los iconos
	@DeleteMapping("/{idLocation}")
	public ResponseEntity<?> deleteLocation(@PathVariable String idLocation) {

		try {
			
			Long id=Long.valueOf(idLocation);
			
			boolean success=locationService.delete(id);
					
			if (success) {

				return ResponseEntity.ok(success);

			} 

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(success);
			
		}catch(NumberFormatException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new CustomError(ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION + " " + e.getMessage()));
			
		} catch(LocationException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new CustomError(e.getMessage()));
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
			
		}
		
	}

}
