package com.alkemy.ar.updater;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;

public class UpdaterEntity {

	//listo
	public static void updateContinent(Continent continent,ContinentDto continentDto) {
		
		continent.setImg(continentDto.getImg());

		continent.setDenomination(continentDto.getDenomination());
	}
	
	//listo
	public static void updateLocation(Location location,LocationDto locationDto) {
		
		location.setImg(locationDto.getImg());
		
		location.setDenomination(locationDto.getDenomination());
		
		location.setPopulation(locationDto.getPopulation());
		
		location.setSurface(locationDto.getSurface());
		
		location.setContinentId(locationDto.getContinentId());
	}
	
	//listo
	public static void updateIcon (Icon icon,IconDto iconDto) {
		
		icon.setImg(icon.getImg());
		
		icon.setDenomination(icon.getDenomination());
		
		DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		icon.setCreationDate(LocalDate.parse(iconDto.getCreationDate().toString(), dateFormatter));
		
		icon.setHeight(icon.getHeight());
		
		icon.setStory(icon.getStory());
		
	}
	
	
	
}
