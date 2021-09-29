package com.alkemy.ar.validator;

import com.alkemy.ar.dto.ContinentDto;

public class DtoValidator {

	public static boolean validDtoProperties(ContinentDto continentDto) {
		
		if(continentDto.getImg()!=null && continentDto.getDenomination()!=null ) {
			
			return true;
		}
			
		return false;
	}


	
}
