package com.alkemy.ar.parser;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.dto.LocationDtoGetAll;
import com.alkemy.ar.dto.LocationDtoGetOne;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;

public class ParserEntity {

	// listo
	public static Continent toContinent(ContinentDto continentDto) {

		Continent continent = new Continent(
				continentDto.getImg(),
				continentDto.getDenomination()
				);

		return continent;
	}

	// listo
	public static ContinentDto toDtoContinent(Continent continent) {

		ContinentDto continentDto = new ContinentDto();

		if (continent != null) {

			continentDto.setImg(continent.getImg());

			continentDto.setDenomination(continent.getDenomination());

		}

		return continentDto;
	}

	// listo
	public static List<ContinentDto> toDtoContinent(List<Continent> continents) {

		List<ContinentDto> continentsDtos = new ArrayList<>();

		if (continents != null) {

			for (Continent c : continents) {

				ContinentDto continentDto = new ContinentDto(
						c.getImg(),
						c.getDenomination()
						);

				continentsDtos.add(continentDto);

			}

		}

		return continentsDtos;
	}

	// listo
	public static Location toLocation(LocationDto locationDto) {

		Location location = new Location(
				locationDto.getImg(),
				locationDto.getDenomination(),
				locationDto.getPopulation(),
				locationDto.getSurface(),
				locationDto.getContinentId());

		return location;
	}

	// listo
	public static LocationDto toDtoLocation(Location location) {

		LocationDto locationDto = new LocationDto(
				location.getImg(),
				location.getDenomination(),
				location.getPopulation(),
				location.getSurface(),
				location.getContinentId());

		return locationDto;
	}

	//listo
	public static List<LocationDtoGetAll> toDtoLocationGetAll(List<Location> locations) {

		List<LocationDtoGetAll> locationsDto = new ArrayList<>();

		if (locations != null) {

			for (Location loc : locations) {

				LocationDtoGetAll locationDto = new LocationDtoGetAll(
						loc.getImg(),
						loc.getDenomination(),
						loc.getPopulation());

				locationsDto.add(locationDto);

			}
		}

		return locationsDto;

	}
	
	//listo
	public static LocationDtoGetOne toDtoLocationGetOne(Location location) {
		
		LocationDtoGetOne locationDto=new LocationDtoGetOne(
				location.getImg(),
				location.getDenomination(),
				location.getPopulation(),
				location.getSurface(),
				location.getContinentId(),
				location.getIcons()
				);
		
		return locationDto;
	}

	public static Icon toIcon(IconDto iconDto) {

		Icon icon = new Icon();
		// deberia tener una lista de locationsdto?

		return null;
	}

	

}
