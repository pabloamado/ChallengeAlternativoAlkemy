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
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.service.IconService;

@RestController
@RequestMapping("/icon")
public class IconController {

	@Autowired
	private IconService iconService;
	
	
	@PostMapping 
	public ResponseEntity<Icon> saveIcon(@RequestBody IconDto iconDto){
		
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Icon> getIcon(@PathVariable Long id){
		
		//mostrar todos los datos del icono y la lista de paises que lo contienen
		return null;
	}
	
	@GetMapping 
	public ResponseEntity<Icon> getIcons(){
		
		//mostrar solo img y denominacion de los icons
		
		return null;
	}
	
	@GetMapping(params="name")
	public ResponseEntity<Icon> getIconsByName(@RequestParam String name){
		
		return null;
	}
	
	@GetMapping(params="date") 
	public ResponseEntity<Icon> getIconsByDate(@RequestParam @DateTimeFormat(pattern = "yyyy.MM.dd") Date date){
		
		return null;
	}
	
	@GetMapping(params="location") 
	public ResponseEntity<Icon> getIconsByLocation(@RequestParam Long location){
		
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Icon>updateIcon(@PathVariable Long id, @RequestBody IconDto iconDto){
		
		//deberia pedir el id del location en el body o en el path para actualizar?
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Icon> deleteIcon(@PathVariable Long id){
		
		
		return null;
	}
	
	
	
	

}
