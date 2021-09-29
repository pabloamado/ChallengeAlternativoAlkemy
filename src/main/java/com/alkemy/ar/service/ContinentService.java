package com.alkemy.ar.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.parser.ParserEntity;
import com.alkemy.ar.repository.ContinentRepository;

@Service
public class ContinentService {

	@Autowired
	private ContinentRepository continentRepository;

	
	//listo
	@Transactional
	public ContinentDto save(ContinentDto continentDto) throws IllegalArgumentException, Exception {

		Continent continent = continentRepository.save(ParserEntity.toContinent(continentDto));

		return ParserEntity.toDtoContinent(continent);

	}

	@Transactional
	public boolean delete(Long id) throws IllegalArgumentException, Exception {

		boolean success = false;

		if (continentRepository.existsById(id)) {

			continentRepository.deleteById(id);

			success = true;

		}

		return success;

	}

	//listo
	@Transactional
	public ContinentDto update(Long id, ContinentDto continentDto) throws EntityNotFoundException,IllegalArgumentException,Exception {

		Continent  continent = continentRepository.getById(id);
		
		continent.setImg(continentDto.getImg());

		continent.setDenomination(continentDto.getDenomination());
	
		continent=continentRepository.save(continent);

		return ParserEntity.toDtoContinent(continent);
	}

	//LISTO
	@Transactional
	public ContinentDto get(Long id) throws EntityNotFoundException, Exception {

		Continent continent = continentRepository.getById(id);
		
		ContinentDto continentDto=ParserEntity.toDtoContinent(continent);

		return continentDto;
	}

	//listo
	@Transactional
	public List<ContinentDto> getAll() throws Exception {

		List<Continent> continents = (List<Continent>) continentRepository.findAll();
		
		
		List<ContinentDto> continentsDto = ParserEntity.toDtoContinent(continents);

		return continentsDto;
	}

}
