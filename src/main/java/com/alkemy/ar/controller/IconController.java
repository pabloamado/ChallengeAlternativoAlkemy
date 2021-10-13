package com.alkemy.ar.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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
import com.alkemy.ar.service.IconService;

@RestController
@RequestMapping("/icons")
public class IconController {

	@Autowired
	private IconService iconService;
	
	@PostMapping
	public ResponseEntity<?> saveIcon(@RequestBody IconDto iconDto) {
			
			IconDto icon = iconService.save(iconDto);

			return ResponseEntity.status(HttpStatus.CREATED).body(icon);
		
	}

	@GetMapping("/{idIcon}")
	public ResponseEntity<?> getIcon(@PathVariable Long idIcon) {

		IconDtoGetOne icon=iconService.get(idIcon);
		
		return ResponseEntity.ok(icon);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getIcons() {

		List<IconDtoGetAll> icons=iconService.getAll();
		
		return ResponseEntity.ok(icons);
	}

		@GetMapping
		public ResponseEntity<?> getIconsByNameAndFilters(@RequestParam(required = false) String name,
	            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
	            @RequestParam(required = false) Set<Long> cities) {
		
			List<IconDtoGetOne> icons=iconService.getFilteredIcons(name,date,cities);
			
			return ResponseEntity.ok(icons);
		}

	
	@PutMapping("/{idIcon}")
	public ResponseEntity<?> updateIcon(@PathVariable Long idIcon, @RequestBody IconDto iconDto) {

		IconDto icon = iconService.update(idIcon,iconDto);

		return ResponseEntity.ok(icon);

	}

	@PostMapping("/{idIcon}/location/{idLocation}")
	public ResponseEntity<Void> saveIconInLocation(@PathVariable Long idIcon,@PathVariable Long idLocation) {
	
		iconService.saveIconInLocation(idIcon,idLocation);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@DeleteMapping("/{idIcon}/location/{idLocation}")
	public ResponseEntity<Void> deleteIconFromLocation(@PathVariable Long idIcon,@PathVariable Long idLocation) {

		iconService.deleteIconFromLocation(idIcon,idLocation);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{idIcon}")
	public ResponseEntity<Void> deleteIcon(@PathVariable Long idIcon) {

		iconService.deleteIcon(idIcon);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	/* IGNORAR
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
		public ResponseEntity<?> getIconsByLocation(@RequestParam Long location) {

			
			List<IconDtoGetAll> icons=iconService.getByLocation(location);
			
			return ResponseEntity.ok(icons);

		}*/

}
