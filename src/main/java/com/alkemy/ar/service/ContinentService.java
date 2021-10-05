package com.alkemy.ar.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.ContinentException;
import com.alkemy.ar.mapper.ContinentMapper;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.repository.ContinentRepository;
import com.alkemy.ar.updater.UpdaterEntity;

@Service
public class ContinentService {

	@Autowired
	private ContinentRepository continentRepository;

	@Transactional
	public ContinentDto save(ContinentDto continentDto) throws IllegalArgumentException, Exception {

		Continent continent = continentRepository.save(ContinentMapper.toContinent(continentDto));

		return ContinentMapper.toDtoContinent(continent);

	}

	@Transactional
	public boolean delete(Long id) throws ContinentException,IllegalArgumentException, Exception {

		if (continentRepository.existsById(id)) {

			continentRepository.deleteById(id);

			return true;

		}
			
		throw new ContinentException(ErrorMsg.CONTINENT_NOT_FOUND.toString());

	}

	@Transactional
	public ContinentDto update(Long id, ContinentDto continentDto) throws EntityNotFoundException,IllegalArgumentException,Exception {

		Continent  continent = continentRepository.getById(id);
		
		UpdaterEntity.updateContinent(continent, continentDto);
	
		continent=continentRepository.save(continent);

		return ContinentMapper.toDtoContinent(continent);
	}
	
	@Transactional
	public ContinentDto get(Long id) throws EntityNotFoundException, Exception {

		Continent continent = continentRepository.getById(id);
		
		return ContinentMapper.toDtoContinent(continent);

	}

	@Transactional
	public List<ContinentDto> getAll() throws Exception {

		List<Continent> continents = (List<Continent>) continentRepository.findAll();
		
		return ContinentMapper.toDtoContinentList(continents);

	}

	
	public Continent getById(Long continentId) {
		
		return continentRepository.getById(continentId);
	}

}
