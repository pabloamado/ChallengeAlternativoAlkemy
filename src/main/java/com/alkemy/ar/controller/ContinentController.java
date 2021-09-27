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
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.service.ContinentService;

@RestController
@RequestMapping("/continent")
public class ContinentController {
	
	@Autowired
	private ContinentService continentService;
	
	@GetMapping
	public ResponseEntity<Continent> getContinents(){
	// recuperar todos los continentes con un select
	
		return null;
	}
	
	@PostMapping
	public ResponseEntity<Continent> saveContinent(@RequestBody ContinentDto continentDto){
		
		// guarda el continente
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Continent> getContinent(@PathVariable Long id){
	
		//recuperar el continente con el id
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Continent> updateContinent(@PathVariable Long id, @RequestBody ContinentDto continentDto){
		
		//buscar primero el continente y si existe actualizarlo
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Continent> deleteContinent(@PathVariable Long id,@RequestBody ContinentDto continentDto){
		
		//borrar el continente y borrar en cascada
		return null;
	}
	
}
