package com.alkemy.ar.parser;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;

public class ParserEntity {

	
	//listo
	public static Continent toContinent(ContinentDto continentDto) {
		
		Continent continent=new Continent(
				continentDto.getImg(),
				continentDto.getDenomination()
				);
		
		return continent;
	}
	
	
	//listo
	public static ContinentDto toDtoContinent(Continent continent){
		
		ContinentDto continentDto=new ContinentDto();
		
		if(continent!=null) {
			
			continentDto.setImg(continent.getImg());
			
			continentDto.setDenomination(continent.getDenomination());
			
		}
		
		return continentDto;
	}
	
	//listo
	public static List<ContinentDto> toDtoContinent(List<Continent> continents) {
		
		
		List<ContinentDto> continentsDtos=new ArrayList<>();
		
		if(continents!=null) {
					
			for(Continent c:continents) {
			
				ContinentDto continentDto=new ContinentDto(
						c.getImg(),
						c.getDenomination()
						);	
			
			continentsDtos.add(continentDto);
			
			}
				
		}
		
		return continentsDtos;
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
