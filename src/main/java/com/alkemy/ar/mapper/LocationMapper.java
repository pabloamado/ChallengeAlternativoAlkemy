package com.alkemy.ar.mapper;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.dto.LocationDtoGetAll;
import com.alkemy.ar.model.Location;

public class LocationMapper {

	// listo
	public static Location toLocation(LocationDto locationDto) {

		Location location = new Location();
		location.setImg(locationDto.getImg());
		location.setDenomination(locationDto.getDenomination());
		location.setPopulation(locationDto.getPopulation());
		location.setSurface(locationDto.getSurface());
		location.setContinentId(locationDto.getContinentId());

		location.setIcons(IconMapper.toIconList(locationDto.getIcons()));

		return location;
	}

	// listo
	public static LocationDto toDtoLocation(Location location) {

		LocationDto locationDto = new LocationDto();

		if (location != null) {

			setLocationDtoValues(locationDto, location);

			locationDto.setIcons(IconMapper.toDtoIconList(location.getIcons()));
		}

		return locationDto;
	}

	// listo
	public static List<LocationDtoGetAll> toDtoLocationGetAll(List<Location> locations) {

		List<LocationDtoGetAll> locationsDto = new ArrayList<>();

		if (locations != null) {

			for (Location location : locations) {

				LocationDtoGetAll locationDto = new LocationDtoGetAll();
				locationDto.setImg(location.getImg());
				locationDto.setDenomination(location.getDenomination());
				locationDto.setPopulation(location.getPopulation());

				locationsDto.add(locationDto);

			}
		}

		return locationsDto;

	}

	public static LocationDto toDtoLocationUpdate(Location location) {
		
		LocationDto locationDto = new LocationDto();

		if (location != null) {

			setLocationDtoValues(locationDto, location);
			
		}
		
		return locationDto;
		
	}

	private static void setLocationDtoValues(LocationDto locationDto, Location location) {
		locationDto.setImg(location.getImg());
		locationDto.setDenomination(location.getDenomination());
		locationDto.setPopulation(location.getPopulation());
		locationDto.setSurface(location.getSurface());
		locationDto.setContinentId(location.getContinentId());
	}
	
	

}
