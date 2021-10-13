package com.alkemy.ar.mapper;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.model.Continent;

public class ContinentMapper {
	
		public static Continent toContinent(ContinentDto continentDto) {

			Continent continent = new Continent();
			continent.setImg(continentDto.getImg());
			continent.setDenomination(continentDto.getDenomination());		
			
			return continent;
		}

		public static ContinentDto toDtoContinent(Continent continent) {

			ContinentDto continentDto = new ContinentDto();

			if (continent != null) {

				continentDto.setImg(continent.getImg());

				continentDto.setDenomination(continent.getDenomination());

			}

			return continentDto;
		}

		public static List<ContinentDto> toDtoContinentList(List<Continent> continents) {

			List<ContinentDto> continentsDtos = new ArrayList<>();

			if (continents != null) {

				for (Continent c : continents) {

					ContinentDto continentDto = new ContinentDto();
					continentDto.setImg(c.getImg());		
					continentDto.setDenomination(c.getDenomination());		
			
					continentsDtos.add(continentDto);

				}

			}

			return continentsDtos;
		}
}
