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
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.ContinentException;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.mapper.LocationMapper;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Location;
import com.alkemy.ar.repository.ContinentRepository;
import com.alkemy.ar.repository.LocationRepository;
import com.alkemy.ar.updater.UpdaterEntity;

@Service
public class LocationService {

	@Autowired
	private ContinentRepository continentRepository;

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public LocationDto save(LocationDto locationDto) throws LocationException,
	IllegalArgumentException, Exception {

		if (continentRepository.existsById(locationDto.getContinentId())) {

			Location location = LocationMapper.toLocation(locationDto);

			location = locationRepository.save(location);
			
			return LocationMapper.toDtoLocation(location);

		} 

		throw new ContinentException(ErrorMsg.NO_CONTINENT_RELATED_TO_LOCATION.toString());

	}

	@Transactional
	public boolean delete(Long id) throws IllegalArgumentException, Exception {
		
		if (locationRepository.existsById(id)) {
			
			locationRepository.deleteById(id);

			return true;

		}

		throw new LocationException(ErrorMsg.LOCATION_NOT_FOUND.toString());

	}


	@Transactional
	public LocationDto update(Long id, LocationDto locationDto)
			throws LocationException,EntityNotFoundException, IllegalArgumentException, Exception {
		
		if(continentRepository.existsById(locationDto.getContinentId())) {
			
			Location location=locationRepository.getById(id);
			
			UpdaterEntity.updateLocation(location, locationDto);
					
			location=locationRepository.save(location);
			
			return LocationMapper.toDtoLocationUpdate(location);
			
		} 
		
		throw new LocationException(ErrorMsg.NO_CONTINENT_RELATED_TO_LOCATION.toString());
		
	}

	//listo PROBAR SI SE VEN LOS ICONOS RELACIONADOS A UN PAIS REVISAR YA QUE  AHORA SE GUARDAN 
	//LOS ICONOS JUNTO AL PAIS
	@Transactional
	public LocationDto get(Long id) throws EntityNotFoundException, Exception {

		Location location=locationRepository.getById(id);
		
		return LocationMapper.toDtoLocation(location);
		
	}

	@Transactional
	public List<LocationDtoGetAll> getAll() throws Exception {

		List<Location> locations=locationRepository.findAll();
		
		return LocationMapper.toDtoLocationGetAll(locations);
		
	}
	

	@Transactional
	public List<LocationDtoGetAll> getAllByOrder(String order) throws Exception {

		List<Location> locations;
			
		if(order.equalsIgnoreCase("asc")){
			
			locations=locationRepository.findAll(Sort.by("lId"));
			
		} else {
			
			locations=locationRepository.findAll(Sort.by(Direction.DESC, "lId"));
			
		}
		
		return LocationMapper.toDtoLocationGetAll(locations);
		
	}

	@Transactional
	public List<LocationDtoGetAll> getByContinent(Long continentId) throws Exception {
		
		/*esto funciona perfecto
		 * Session session=sessionFactory.openSession();
			
		List<Location> locations=session.createQuery(" from Location loc where loc.continentId = :continent")
				.setParameter("continent", continentId).getResultList();*/
		
		//forma mas correcta
		if(continentRepository.existsById(continentId)) {
			
			Continent continent=continentRepository.getById(continentId);
			
			List<Location> locations=continent.getLocations();
			
			return LocationMapper.toDtoLocationGetAll(locations);
			
		}
		
		throw new LocationException(ErrorMsg.NO_CONTINENT_RELATED_TO_LOCATION.toString());
			
	}

	@Transactional
	public LocationDto getByName(String name) throws NoResultException,Exception {
		
		Session session=sessionFactory.openSession();
		
		Location location=(Location) session.createQuery(" from Location loc where loc.denomination =:name")
				.setParameter("name", name).getSingleResult();
		
		return LocationMapper.toDtoLocation(location);
	}

}
