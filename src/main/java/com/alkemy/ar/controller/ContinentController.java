package com.alkemy.ar.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
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
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.error.CustomError;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.ContinentException;
import com.alkemy.ar.service.ContinentService;
import com.alkemy.ar.validator.DtoValidator;

@RestController
@RequestMapping("/continent")
public class ContinentController {

	@Autowired
	private ContinentService continentService;

	// testeado
	@PostMapping
	public ResponseEntity<?> saveContinent(@RequestBody ContinentDto continentDto) {

		if (DtoValidator.validDtoProperties(continentDto)) {

			try {

				ContinentDto continent = continentService.save(continentDto);

				return ResponseEntity.status(HttpStatus.CREATED).body(continent);

			} catch (IllegalArgumentException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));

			} catch (Exception e) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));

			}

		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(
				ErrorMsg.WRONG_ENTITY_PARAMETERS_EXCEPTION.toString()));
	
	}

	// testeado
	@GetMapping("/{idContinent}")
	public ResponseEntity<?> getContinent(@PathVariable String idContinent) {

		try {
			
			Long id=Long.valueOf(idContinent);
			
			ContinentDto continent = continentService.get(id);

			return ResponseEntity.ok(continent);

		} catch(NumberFormatException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION
					+ " " + new CustomError(e.getMessage()));
			
		}catch (EntityNotFoundException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(e.getMessage()));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));

		} 

	}

	// testeado
	@GetMapping
	public ResponseEntity<?> getContinents() {

		try {

			List<ContinentDto> continents = continentService.getAll();

			return ResponseEntity.ok(continents);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));
		}

	}

	// testeado
	@PutMapping("/{idContinent}")
	public ResponseEntity<?> updateContinent(@PathVariable String idContinent, @RequestBody ContinentDto continentDto) {

		if(DtoValidator.validDtoProperties(continentDto)) {
			
			try {
				
				Long id=Long.valueOf(idContinent);

				ContinentDto continent = continentService.update(id, continentDto);

				return ResponseEntity.ok(continent);

			} catch(NumberFormatException e) {
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(
						ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION + " " + e.getMessage()));
				
			}catch (EntityNotFoundException e) {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomError(e.getMessage()));

			} catch (IllegalArgumentException e) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));

			} catch (Exception e) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));

			}
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(
				ErrorMsg.WRONG_ENTITY_PARAMETERS_EXCEPTION.toString()));
	
	}

	// testeado
	@DeleteMapping("/{idContinent}")
	public ResponseEntity<?> deleteContinent(@PathVariable String idContinent) {

		try {

			Long id=Long.valueOf(idContinent);
			
			boolean success = continentService.delete(id);

			if (success) {

				return ResponseEntity.ok(success);

			} 

			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(success);

		}catch(NumberFormatException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMsg.WRONG_PATH_VARIABLE_EXCEPTION
					+ " " + new CustomError(e.getMessage()));
			
		}catch (IllegalArgumentException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(e.getMessage()));

		}  catch (ContinentException e) {

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new CustomError(e.getMessage()));

		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomError(e.getMessage()));

		}

	}

}
