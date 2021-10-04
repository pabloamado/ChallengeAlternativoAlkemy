package com.alkemy.ar.validator;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.LocationDto;

public class DtoValidator {

	public static boolean validDtoProperties(ContinentDto continentDto) {

		if (continentDto.getImg() != null && continentDto.getDenomination() != null) {

			return true;
		}

		return false;
	}

	// al momento de insertar un pais me obliga a insertar iconos
	public static boolean validDtoProperties(LocationDto locationDto) {

		if (locationDto.getImg() != null && locationDto.getDenomination() != null &&
				locationDto.getPopulation() >= 0 && locationDto.getContinentId()>0 
				&& locationDto.getSurface()> 0 && locationDto.getIcons()!=null) {

			return true;
		}

		return false;
	}
	
	//permite que tenga la lista nula de iconos ,para actualizar solamente los valores  
	public static boolean validDtoPropertiesToUpdate(LocationDto locationDto) {

		if (locationDto.getImg() != null && locationDto.getDenomination() != null &&
				locationDto.getPopulation() >= 0 && locationDto.getContinentId()>0 
				&& locationDto.getSurface()> 0) {

			return true;
		}

		return false;
	}

	public static boolean validDtoProperties(IconDto iconDto) {
		
		if(iconDto.getImg() != null && iconDto.getDenomination() != null &&
				iconDto.getCreationDate()!=null && iconDto.getHeight()>0 &&
				iconDto.getStory()!=null) {
			
			return true;
		}
		
		return false;
	
	}

}
