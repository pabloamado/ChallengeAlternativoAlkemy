package com.alkemy.ar.updater;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;

public class UpdaterEntity {

	public static void updateContinent(Continent continent,ContinentDto continentDto) {
		
		continent.setImg(continentDto.getImg());

		continent.setDenomination(continentDto.getDenomination());
	}
	
	public static void updateLocation(Location location,LocationDto locationDto) {
		
		location.setImg(locationDto.getImg());
		
		location.setDenomination(locationDto.getDenomination());
		
		location.setPopulation(locationDto.getPopulation());
		
		location.setSurface(locationDto.getSurface());
		
		location.setContinentId(locationDto.getContinentId());
	}
	
	public static void updateIcon (Icon icon,IconDto iconDto) {
		
		icon.setImg(iconDto.getImg());
		
		icon.setDenomination(iconDto.getDenomination());
		
		icon.setCreationDate(iconDto.getCreationDate());
		
		icon.setHeight(iconDto.getHeight());
		
		icon.setStory(iconDto.getStory());
		
	}
		
}
