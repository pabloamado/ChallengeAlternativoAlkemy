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
import com.alkemy.ar.criteriaBuilder.LocationSpecification;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.dto.LocationDtoGetAll;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.mapper.LocationMapper;
import com.alkemy.ar.model.Continent;
import com.alkemy.ar.model.Location;
import com.alkemy.ar.repository.LocationRepository;
import com.alkemy.ar.updater.UpdaterEntity;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	ContinentService continentService;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	LocationSpecification locationSpecification;
	
	//ya no pregunto si el continente existe
	@Transactional
	public LocationDto save(LocationDto locationDto) throws LocationException,
	IllegalArgumentException, Exception {

			Location location = LocationMapper.toLocation(locationDto);

			location = locationRepository.save(location);
			
			return LocationMapper.toDtoLocation(location);

	}

	//elimina el pais y la relacion pero no elimina el o los iconos relacionados
	@Transactional
	public boolean delete(Long id) throws IllegalArgumentException, Exception {
		
		if (locationRepository.existsById(id)) {
			
			locationRepository.deleteById(id);
			
			return true;

		}

		throw new LocationException(ErrorMsg.LOCATION_NOT_FOUND.toString());

	}

	//ya no pregunto si el continente existe
	@Transactional
	public LocationDto update(Long id, LocationDto locationDto)
			throws LocationException,EntityNotFoundException, IllegalArgumentException, Exception {
	
			Location location=locationRepository.getById(id);
			
			UpdaterEntity.updateLocation(location, locationDto);
					
			location=locationRepository.save(location);
			
			return LocationMapper.toDtoLocationUpdate(location);
			
	}

	//listo
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
			
			Continent continent=continentService.getById(continentId);
			
			return LocationMapper.toDtoLocationGetAll(continent.getLocations());
					
	}

	@Transactional
	public LocationDto getByName(String name) throws NoResultException,Exception {
		
		Session session=sessionFactory.openSession();
		
		Location location=(Location) session.createQuery(" from Location loc where loc.denomination =:name")
				.setParameter("name", name).getSingleResult();
		
		return LocationMapper.toDtoLocation(location);
	}
	
	//METODO PARA FILTRAR
		public List<LocationDto> getFilteredLocations(String name, Long continent, String order) {
			
			List<Location> locations=locationRepository.findAll(locationSpecification.getByFilters(name,continent,order));
			
			return LocationMapper.toDtoLocationList(locations);
		}
		
	//METODOS USADOS EN EL ICON SERVICE
		public Location getById(Long locationId) {
			
			return locationRepository.getById(locationId);
		}

		public Location save(Location location) {
			
			return locationRepository.save(location);
			
		}
}
