package com.alkemy.ar.validator;

import org.springframework.stereotype.Component;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.LocationDto;

@Component
public class DtoValidator {

	public  boolean validDtoProperties(ContinentDto continentDto) {

		if (continentDto.getImg() != null && continentDto.getDenomination() != null) {

			return true;
		}

		return false;
	}

	// al momento de insertar un pais me obliga a insertar iconos
	public  boolean validDtoProperties(LocationDto locationDto) {

		if (locationDto.getImg() != null && locationDto.getDenomination() != null &&
				locationDto.getPopulation() >= 0 && locationDto.getContinentId()>0 
				&& locationDto.getSurface()> 0 && locationDto.getIcons()!=null) {

			return true;
		}

		return false;
	}
	
	//permite que tenga la lista nula de iconos ,para actualizar solamente los valores  
	public  boolean validDtoPropertiesToUpdate(LocationDto locationDto) {

		if (locationDto.getImg() != null && locationDto.getDenomination() != null &&
				locationDto.getPopulation() >= 0 && locationDto.getContinentId()>0 
				&& locationDto.getSurface()> 0) {

			return true;
		}

		return false;
	}

	public  boolean validDtoProperties(IconDto iconDto) {
		
		if(iconDto.getImg() != null && iconDto.getDenomination() != null &&
				iconDto.getCreationDate()!=null && iconDto.getHeight()>0 &&
				iconDto.getStory()!=null) {
			
			return true;
		}
		
		return false;
	
	}

}
