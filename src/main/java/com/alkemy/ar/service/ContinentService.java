package com.alkemy.ar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.dto.ContinentDto;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.ContinentException;
import com.alkemy.ar.mapper.ContinentMapper;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.repository.ContinentRepository;
import com.alkemy.ar.updater.UpdaterEntity;
import com.alkemy.ar.validator.DtoValidator;

@Service
public class ContinentService {

	@Autowired
	private ContinentRepository continentRepository;
	
	@Autowired
	private DtoValidator dtoValidator;

	public ContinentDto save(ContinentDto continentDto){

		if(dtoValidator.validDtoProperties(continentDto)) {
			
		 Continent continent = continentRepository.save(ContinentMapper.toContinent(continentDto));
		 
		return ContinentMapper.toDtoContinent(continent);
		
		}
		
		throw new ContinentException(ErrorMsg.WRONG_PARAMETERS_ENTITY_EXCEPTION.toString());
	}

	public void delete(Long id)  {

		if (continentRepository.existsById(id)) {

			continentRepository.deleteById(id);

		}else {
			
			throw new ContinentException(ErrorMsg.CONTINENT_NOT_FOUND.toString());
			
		}
			
	}

	public ContinentDto update(Long id, ContinentDto continentDto)  {

		if(dtoValidator.validDtoProperties(continentDto)) {
			
			Continent  continent = continentRepository.getById(id);
			
			UpdaterEntity.updateContinent(continent, continentDto);
		
			continent=continentRepository.save(continent);

			return ContinentMapper.toDtoContinent(continent);
		}
		
		throw new ContinentException(ErrorMsg.WRONG_PARAMETERS_ENTITY_EXCEPTION.toString());
		
	}
	
	
	public ContinentDto get(Long id){

		Continent continent = continentRepository.getById(id);
		
		return ContinentMapper.toDtoContinent(continent);

	}

	public List<ContinentDto> getAll() {

		List<Continent> continents = (List<Continent>) continentRepository.findAll();
		
		return ContinentMapper.toDtoContinentList(continents);

	}

	public Continent getById(Long continentId) {
		
		return continentRepository.getById(continentId);
	}
	

}
