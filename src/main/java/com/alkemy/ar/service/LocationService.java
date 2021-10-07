package com.alkemy.ar.service;


import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.criteriaBuilder.LocationSpecification;
import com.alkemy.ar.dto.LocationDto;
import com.alkemy.ar.dto.LocationDtoGetAll;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.mapper.LocationMapper;
import com.alkemy.ar.model.Location;
import com.alkemy.ar.repository.LocationRepository;
import com.alkemy.ar.updater.UpdaterEntity;
import com.alkemy.ar.validator.DtoValidator;

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
	
	@Autowired
	private DtoValidator dtoValidator;
	
	public LocationDto save(LocationDto locationDto){

		if(dtoValidator.validDtoProperties(locationDto)) {
			
			Location location = LocationMapper.toLocation(locationDto);

			location = locationRepository.save(location);
				
			return LocationMapper.toDtoLocation(location);
		}
		
		throw new LocationException(ErrorMsg.WRONG_PARAMETERS_ENTITY_EXCEPTION.toString());
		
	}

	//elimina el pais y la relacion pero no elimina el o los iconos relacionados
	public void delete(Long id) {
		
		if (locationRepository.existsById(id)) {
			
			locationRepository.deleteById(id);

		}

		throw new LocationException(ErrorMsg.LOCATION_NOT_FOUND.toString());

	}

	public LocationDto update(Long id, LocationDto locationDto) {
		
		if(dtoValidator.validDtoPropertiesToUpdate(locationDto)) {
			
			Location location=locationRepository.getById(id);
			
			UpdaterEntity.updateLocation(location, locationDto);
						
			location=locationRepository.save(location);
				
			return LocationMapper.toDtoLocation(location);
		}
	
		throw new LocationException(ErrorMsg.WRONG_PARAMETERS_ENTITY_EXCEPTION.toString());
			
	}

	public LocationDto get(Long id)  {

		Location location=locationRepository.getById(id);
		
		return LocationMapper.toDtoLocation(location);
		
	}

	
	public List<LocationDtoGetAll> getAll(){

		List<Location> locations=locationRepository.findAll();
		
		return LocationMapper.toDtoLocationGetAll(locations);
		
	}


	public List<LocationDto> getFilteredLocations(String name, Long continent, String order) {
			
		List<Location> locations=locationRepository
				.findAll(locationSpecification.getByFilters(name,continent,order));
			
		return LocationMapper.toDtoLocationList(locations);
	}
		
		
	//METODOS USADOS EN EL ICON SERVICE
	public Location getById(Long locationId) {
			
		return locationRepository.getById(locationId);
	}

	public Location save(Location location) {
		
		return locationRepository.save(location);
			
	}

	public void flush(Location location) {
			
		locationRepository.saveAndFlush(location);
			
	}
		
/* IGNORAR	
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
			
			Session session=sessionFactory.openSession();
				
			List<Location> locations=session.createQuery(" from Location loc where loc.continentId = :continent")
					.setParameter("continent", continentId).getResultList();
			
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
		}*/
}
