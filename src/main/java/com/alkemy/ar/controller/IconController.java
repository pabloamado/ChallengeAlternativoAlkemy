package com.alkemy.ar.controller;

import java.util.Date;

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
import com.alkemy.ar.error.CustomError;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.service.IconService;
import com.alkemy.ar.validator.DtoValidator;

@RestController
@RequestMapping("/icon")
public class IconController {

	@Autowired
	private IconService iconService;
	
	//EMPEZAR POR ACA
	@PostMapping("/{idIcon}/location/{idLocation}")
	public ResponseEntity<?> saveIcon(@PathVariable String idIcon,@PathVariable String idLocation,@RequestBody IconDto iconDto) {

		if (DtoValidator.validDtoProperties(iconDto)) {

			try {

				IconDto icon = iconService.save(iconDto);

				return ResponseEntity.ok(icon);

			} catch (LocationException e) {

				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new CustomError(e.getMessage()));

			} catch (IllegalArgumentException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));

			} catch (Exception e) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));

			}

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new CustomError(ErrorMsg.WRONG_ENTITY_PARAMETERS_EXCEPTION.toString()));
	}

	
	@GetMapping("/{idIcon}")
	public ResponseEntity<?> getIcon(@PathVariable String idIcon) {

		// mostrar todos los datos del icono y la lista de paises que lo contienen
		try {
			
			
		}catch(Exception e) {
			
			
		}

		return null;
	}

	@GetMapping
	public ResponseEntity<?> getIcons() {

		// mostrar solo img y denominacion de los icons
		try {
			
			
		}catch(Exception e) {
			
			
		}

		return null;
	}

	@GetMapping(params = "name")
	public ResponseEntity<?> getIconsByName(@RequestParam String name) {

		try {
			
			
		}catch(Exception e) {
			
			
		}
		
		return null;
	}

	@GetMapping(params = "date")
	public ResponseEntity<?> getIconsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy.MM.dd") Date date) {

		try {
			
			
		}catch(Exception e) {
			
			
		}
		
		return null;
	}

	@GetMapping(params = "location")
	public ResponseEntity<?> getIconsByLocation(@RequestParam Long location) {

		try {
			
			
		}catch(Exception e) {
			
			
		}

		return null;
	}

	//listo
	@PutMapping("/{idIcon}")
	public ResponseEntity<?> updateIcon(@PathVariable String idIcon, @RequestBody IconDto iconDto) {

		if (DtoValidator.validDtoProperties(iconDto)) {

			try {

				Long id = Long.valueOf(idIcon);

				IconDto icon = iconService.update(id, iconDto);

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

	@DeleteMapping("/{idIcon}/location/{idLocation}")
	public ResponseEntity<?> deleteIcon(@PathVariable String idIcon,@PathVariable String idLocation) {

		try {
			
			
		}catch(Exception e) {
			
			
		}
		
		
		return null;
	}

}
