package com.alkemy.ar.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.IconDtoGetAll;
import com.alkemy.ar.dto.IconDtoGetOne;
import com.alkemy.ar.error.CustomError;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.service.IconService;
import com.alkemy.ar.validator.DtoValidator;

@RestController
@RequestMapping("/icons")
public class IconController {

	@Autowired
	private IconService iconService;
	
	
	//FALTA BORRAR UN ICONO POR SI SOLO Y  UPDATEAR
	
	//testeado
	@PostMapping
	public ResponseEntity<?> saveIcon(@RequestBody IconDto iconDto) {

			try {

				IconDto icon = iconService.save(iconDto);

				return ResponseEntity.status(HttpStatus.CREATED).body(icon);

			} catch (LocationException e) {

				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new CustomError(e.getMessage()));

			} catch (IllegalArgumentException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));

			} catch (Exception e) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));

			}
	
	}

	//testeado
	@GetMapping("/{idIcon}")
	public ResponseEntity<?> getIcon(@PathVariable String idIcon) {

		Long iconId=Long.valueOf(idIcon);
		
		IconDtoGetOne icon=iconService.get(iconId);
		
		return ResponseEntity.ok(icon);
	}

	//testeado
	@GetMapping
	public ResponseEntity<?> getIcons() {

		List<IconDtoGetAll> icons=iconService.getAll();
		
		return ResponseEntity.ok(icons);
	}

	//testeado
	@GetMapping(params = "name")
	public ResponseEntity<?> getIconsByName(@RequestParam String name) {

		IconDtoGetOne icon=iconService.getByName(name);
		
		return ResponseEntity.ok(icon);
	}

	
	//testeado
	@GetMapping(params = "date")
	public ResponseEntity<?> getIconsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

		IconDtoGetOne icon=iconService.getByDate(date);
		
		return ResponseEntity.ok(icon);
	}

	//testeado
	@GetMapping(params = "location")
	public ResponseEntity<?> getIconsByLocation(@RequestParam String location) {

		Long locationId=Long.valueOf(location);
		
		List<IconDtoGetAll> icons=iconService.getByLocation(locationId);
		
		return ResponseEntity.ok(icons);

	
	}

	//no funciona
	@PutMapping("/{idIcon}/location/{idLocation}")
	public ResponseEntity<?> updateIcon(@PathVariable String idIcon,@PathVariable String idLocation, @RequestBody IconDto iconDto) {

		if (DtoValidator.validDtoProperties(iconDto)) {

			try {

				Long iconId = Long.valueOf(idIcon);
				
				Long locationId = Long.valueOf(idLocation);
				
				IconDto icon = iconService.update(iconId,locationId, iconDto);

				return ResponseEntity.ok(icon);

			} catch (NumberFormatException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new CustomError(ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION + " " + e.getMessage()));

			} catch (Exception e) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));

			}

		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
				(new CustomError(ErrorMsg.WRONG_ENTITY_PARAMETERS_EXCEPTION.toString()));

	}
	
	

	//testeado agreg una relacion en la jointable
	@PostMapping("/{idIcon}/location/{idLocation}")
	public ResponseEntity<Void> saveIconInLocation(@PathVariable String idIcon,@PathVariable String idLocation) {
		
		Long iconId = Long.valueOf(idIcon);
		
		Long locationId=Long.valueOf(idLocation);
		
		iconService.saveIconInLocation(iconId,locationId);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

	//testeado, borra una relacionen en la join table
	@DeleteMapping("/{idIcon}/location/{idLocation}")
	public ResponseEntity<Void> deleteIconFromLocation(@PathVariable String idIcon,@PathVariable String idLocation) {

		Long iconId = Long.valueOf(idIcon);
		
		Long locationId=Long.valueOf(idLocation);
		
		iconService.deleteIconFromLocation(iconId,locationId);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
