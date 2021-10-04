package com.alkemy.ar.service;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.IconDtoGetAll;
import com.alkemy.ar.dto.IconDtoGetOne;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.mapper.IconMapper;
import com.alkemy.ar.mapper.LocationMapper;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;
import com.alkemy.ar.repository.IconRepository;
import com.alkemy.ar.repository.LocationRepository;
import com.alkemy.ar.updater.UpdaterEntity;

@Service
public class IconService {

	@Autowired
	private IconRepository iconRepository;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	SessionFactory sessionFactory;

	//listo
	@Transactional
	public IconDto save(Long iconId, Long locationId) throws LocationException, IllegalArgumentException, Exception {

		Icon icon = iconRepository.getById(iconId);

		Location location = locationService.getById(locationId);

		//el problema esta aca
		location.getIcons().size();

		location.addIcon(icon);

		locationService.save(LocationMapper.toDtoLocation(location));

		return IconMapper.toIconDto(icon);

	}

	//listo
	@Transactional
	public boolean delete(Long id) {

		//no puede borrar
		
		if(iconRepository.existsById(id)) {
	
		iconRepository.deleteById(id);
		
		return true;
		
		}
		
		return false;
	}

	//listo
	@Transactional
	public IconDto update(Long iconId,Long locationId, IconDto iconDto)
			throws EntityNotFoundException, IllegalArgumentException, Exception {
		
		
		Icon icon = iconRepository.getById(iconId);

		Location location=locationService.getById(locationId);
		
		//el problema esta aca
		
		for(int i=0;i<location.getIcons().size();i++ ) {
			
			if(location.getIcons().get(i).getiId()==icon.getiId()) {
				
				UpdaterEntity.updateIcon(location.getIcons().get(i), iconDto);
				
				break;
	
			}
		}
	
		locationService.save(location);
		
		return IconMapper.toIconDto(icon);	
		
	}

	//listo
	@Transactional
	public IconDtoGetOne get(Long id) {

		Icon icon=iconRepository.getById(id);
		
		icon.getLocations().size();
		
		return IconMapper.toIconDtoGetOne(icon);
	}

	//listo
	@Transactional
	public List<IconDtoGetAll> getAll() {

		List<Icon> icons=iconRepository.findAll();
		
		return IconMapper.toIconDtoGetAll(icons);
	}

	//listo
	@Transactional
	public IconDtoGetOne getByName(String name) {

		Session session=sessionFactory.openSession();
		
		Icon icon=(Icon) session.createQuery(" from Icon ic where ic.denomination =:name")
				.setParameter("name", name).getSingleResult();
		
		icon.getLocations().size();
		
		return IconMapper.toIconDtoGetOne(icon);
		
	}

	//listo
	@Transactional
	public IconDtoGetOne getByDate(LocalDate date) {

		Session session=sessionFactory.openSession();
		
		Icon icon=(Icon) session.createQuery(" from Icon ic where ic.creationDate =:date")
				.setParameter("date", date).getSingleResult();
		
		return IconMapper.toIconDtoGetOne(icon);
	}

	//listo
	@Transactional
	public List<IconDtoGetAll> getByLocation(Long locationId) {

		Location location=locationService.getById(locationId);
		
		location.getIcons().size();
		
		return IconMapper.toIconDtoGetAll(location.getIcons());
	}

}
