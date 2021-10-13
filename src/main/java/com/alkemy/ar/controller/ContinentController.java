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
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.exception.ContinentException;
import com.alkemy.ar.service.ContinentService;

@RestController
@RequestMapping("/continent")
public class ContinentController {

	@Autowired
	private ContinentService continentService;

	// testeado
	@PostMapping
	public ResponseEntity<?> saveContinent(@RequestBody ContinentDto continentDto) throws ContinentException {

		ContinentDto continent = continentService.save(continentDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(continent);

	}

	@GetMapping("/{idContinent}")
	public ResponseEntity<?> getContinent(@PathVariable Long idContinent) {

		ContinentDto continent = continentService.get(idContinent);

		return ResponseEntity.ok(continent);

	}

	@GetMapping
	public ResponseEntity<?> getContinents() {

		List<ContinentDto> continents = continentService.getAll();

		return ResponseEntity.ok(continents);

	}

	@PutMapping("/{idContinent}")
	public ResponseEntity<?> updateContinent(@PathVariable Long idContinent, @RequestBody ContinentDto continentDto) {

		ContinentDto continent = continentService.update(idContinent, continentDto);

		return ResponseEntity.ok(continent);

	}

	@DeleteMapping("/{idContinent}")
	public ResponseEntity<?> deleteContinent(@PathVariable Long idContinent) {

		continentService.delete(idContinent);

		return ResponseEntity.ok().build();
		
	}

}
