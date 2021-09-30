package com.alkemy.ar.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.dto.LocationDtoGetAll;
import com.alkemy.ar.dto.LocationDtoGetOne;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.ContinentException;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;
import com.alkemy.ar.parser.ParserEntity;
import com.alkemy.ar.repository.ContinentRepository;
import com.alkemy.ar.repository.IconRepository;
import com.alkemy.ar.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private ContinentRepository continentRepository;

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private IconRepository iconRepository;

	@Autowired
	SessionFactory sessionFactory;
	
	//testeado
	@Transactional
	public LocationDto save(LocationDto locationDto) throws ContinentException, IllegalArgumentException, Exception {

		if (continentRepository.existsById(locationDto.getContinentId())) {

			Location location = ParserEntity.toLocation(locationDto);

			location = locationRepository.save(location);
			
			LocationDto locationReturn=ParserEntity.toDtoLocation(location);

			return locationReturn;

		} 

		throw new ContinentException(ErrorMsg.CONTINENT_NOT_FOUND.toString());

	}

	//testeado  PROBAR CUANDO SE INSERTE ICONOS
	@Transactional
	public boolean delete(Long id) throws IllegalArgumentException, Exception {
		
		if (locationRepository.existsById(id)) {

			//RECUPERO EL LOCATION
			Location location=locationRepository.getById(id);
			
			//OBTENGO LOS ICONOS DEL PAIS  A BORRAR, Y DE CADA ICONO BORRO DE SU LISTA DE PAISES EL PAIS A BORRAR
			
			//location.getIcons().forEach(icon -> icon.getLocations().remove(location));
			
			for(Icon i:location.getIcons()) {
				
				i.getLocations().remove(location);
			}
			
			iconRepository.saveAll(location.getIcons());
			
			locationRepository.deleteById(id);

			return true;

		}

		throw new LocationException(ErrorMsg.LOCATION_NOT_FOUND.toString());

	}

	// testeado
	@Transactional
	public LocationDto update(Long id, LocationDto locationDto)
			throws LocationException,EntityNotFoundException, IllegalArgumentException, Exception {
		
		if(continentRepository.existsById(locationDto.getContinentId())) {
			
			Location location=locationRepository.getById(id);
			
			location.setImg(locationDto.getImg());
			
			location.setDenomination(locationDto.getDenomination());
			
			location.setPopulation(locationDto.getPopulation());
			
			location.setSurface(locationDto.getSurface());
			
			location.setContinentId(locationDto.getContinentId());
						
			location=locationRepository.save(location);
			
			return ParserEntity.toDtoLocation(location);
			
		} 
		
		throw new LocationException(ErrorMsg.NO_CONTINENT_RELATED_TO_LOCATION.toString());
		
	}

	
	//listo
	@Transactional
	public LocationDtoGetOne get(Long id) throws EntityNotFoundException, Exception {

		Location location=locationRepository.getById(id);
		//chequear que la lista de iconos pueda ser extraida del location
		return ParserEntity.toDtoLocationGetOne(location);
		
	}

	//listo
	@Transactional
	public List<LocationDtoGetAll> getAll() throws Exception {

		List<Location> locations=locationRepository.findAll();
		
		return ParserEntity.toDtoLocationGetAll(locations);
		
	}
	
	//listo
	@Transactional
	public List<LocationDtoGetAll> getAllByOrder(String order) throws Exception {

		List<Location> locations;
		
		//obligatoriamente el ordenamiento debe hacerse por algun campo del objeto
		
		if(order.equalsIgnoreCase("asc")){
			
			locations=locationRepository.findAll(Sort.by("img"));
			
		} else {
			
			locations=locationRepository.findAll(Sort.by(Direction.DESC, "img"));
			
		}
		
		return ParserEntity.toDtoLocationGetAll(locations);
		
	}

	//LISTO DEVUELVE VACIO SI SE INTRODUCE UN ID ERRONEO
	@Transactional
	public List<LocationDtoGetAll> getByContinent(Long continent) throws Exception {
		
		Session session=sessionFactory.openSession();
			
		List<Location> locations=session.createQuery(" from Location loc where loc.continentId = :continent")
				.setParameter("continent", continent).getResultList();
		
		//List<LocationDtoGetAll> locationsDto=ParserEntity.toDtoLocationGetAll(locations);
		
		//return locationsDto;
		
		return ParserEntity.toDtoLocationGetAll(locations);
		
	}

	//listo
	@Transactional
	public LocationDtoGetOne getByName(String name) throws NoResultException,Exception {
		
		Session session=sessionFactory.openSession();
		
		Location location=(Location) session.createQuery(" from Location loc where loc.denomination =:name")
				.setParameter("name", name).getSingleResult();
		

		return ParserEntity.toDtoLocationGetOne(location);
	}

}
