package com.alkemy.ar.validator;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.LocationDto;

public class DtoValidator {

	public static boolean validDtoProperties(ContinentDto continentDto) {

		if (continentDto.getImg() != null && continentDto.getDenomination() != null) {

			return true;
		}

		return false;
	}

	public static boolean validDtoProperties(LocationDto locationDto) {

		if (locationDto.getImg() != null && locationDto.getDenomination() != null && locationDto.getPopulation() >= 0
				&& locationDto.getContinentId()>0 && locationDto.getSurface()> 0) {

			return true;
		}

		return false;
	}

}
