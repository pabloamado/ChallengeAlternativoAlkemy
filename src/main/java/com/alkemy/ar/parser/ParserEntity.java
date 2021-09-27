package com.alkemy.ar.parser;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;

public class ParserEntity {

	
	public static Continent toContinent(ContinentDto continentDto) {
		
		Continent continent=new Continent(
				continentDto.getImg(),
				continentDto.getDenomination()
				);
		
		return continent;
	}
	
	public static Location toLocation(LocationDto locationDto) {
		
		//revisar bien que atributo deberia tener el locationdto, una lista de icons dto?
		Location location=new Location(
				locationDto.getImg(),
				locationDto.getDenomination(),
				locationDto.getPopulation(),
				locationDto.getSurface()
				);
		
		return location;
	}
	
	
	public static Icon toIcon(IconDto iconDto) {
		
		Icon icon=new Icon();
		//deberia tener una lista de locationsdto?
		
		return null;
	}
}
