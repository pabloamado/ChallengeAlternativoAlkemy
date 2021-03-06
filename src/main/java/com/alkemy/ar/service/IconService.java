package com.alkemy.ar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.dto.IconDtoGetAll;
import com.alkemy.ar.dto.IconDtoGetOne;
import com.alkemy.ar.error.ErrorMsg;
import com.alkemy.ar.exception.IconException;
import com.alkemy.ar.mapper.IconMapper;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.model.Location;
import com.alkemy.ar.model.specification.IconSpecification;
import com.alkemy.ar.repository.IconRepository;
import com.alkemy.ar.updater.UpdaterEntity;
import com.alkemy.ar.validator.DtoValidator;

@Service
public class IconService {

	@Autowired
	private IconRepository iconRepository;

	@Autowired
	private LocationService locationService;
	
	@Autowired
	IconSpecification iconSpecification;

	@Autowired
	private DtoValidator dtoValidator;

	public IconDto save(IconDto iconDto) {

		if (dtoValidator.validDtoProperties(iconDto)) {

			Icon icon = IconMapper.toIcon(iconDto);

			icon = iconRepository.save(icon);

			return IconMapper.toIconDto(icon);
		}

		throw new IconException(ErrorMsg.WRONG_PARAMETERS_ENTITY_EXCEPTION.toString());

	}

	@Transactional
	public void deleteIconFromLocation(Long iconId, Long locationId) {

		Location location = locationService.getById(locationId);

		location.getIcons().size();

		Icon icon = iconRepository.getById(iconId);

		location.removeIcon(icon);
	}
	
	@Transactional
	public void saveIconInLocation(Long iconId, Long locationId) {

		Location location = locationService.getById(locationId);

		location.getIcons().size();

		Icon icon = iconRepository.getById(iconId);
		
		location.addIcon(icon);
	}

	public IconDto update(Long iconId, IconDto iconDto) {

		if (dtoValidator.validDtoProperties(iconDto)) {

			Icon icon=iconRepository.getById(iconId);
	
			UpdaterEntity.updateIcon(icon, iconDto);
			
			icon=iconRepository.save(icon);
			
			return IconMapper.toIconDto(icon);

		}

		throw new IconException(ErrorMsg.WRONG_PARAMETERS_ENTITY_EXCEPTION.toString());

	}

	@Transactional
	public IconDtoGetOne get(Long id) {

		Icon icon = iconRepository.getById(id);

		icon.getLocations().size();

		return IconMapper.toIconDtoGetOne(icon);
	}

	@Transactional
	public List<IconDtoGetAll> getAll() {

		List<Icon> icons = iconRepository.findAll();

		return IconMapper.toIconDtoGetAll(icons);
	}

	public List<IconDtoGetOne> getFilteredIcons(String name, LocalDate date, Set<Long> cities) {

		List<Icon> icons = iconRepository.findAll(iconSpecification.getByFilters(name, date, cities));

		return IconMapper.toIconDtoGetOneList(icons);
	}

	public void deleteIcon(Long iconId) {

		if(!iconRepository.existsById(iconId)) {
		
			throw new IconException(ErrorMsg.ICON_NOT_FOUND.toString());
			
		}
		
		iconRepository.deleteById(iconId);
		
	}

	/*
	 * IGNORAR //listo
	 * 
	 * @Transactional public IconDtoGetOne getByName(String name) {
	 * 
	 * Session session=sessionFactory.openSession();
	 * 
	 * Icon icon=(Icon)
	 * session.createQuery(" from Icon ic where ic.denomination =:name")
	 * .setParameter("name", name).getSingleResult();
	 * 
	 * icon.getLocations().size();
	 * 
	 * return IconMapper.toIconDtoGetOne(icon);
	 * 
	 * }
	 * 
	 * //listo
	 * 
	 * @Transactional public IconDtoGetOne getByDate(LocalDate date) {
	 * 
	 * Session session=sessionFactory.openSession();
	 * 
	 * Icon icon=(Icon)
	 * session.createQuery(" from Icon ic where ic.creationDate =:date")
	 * .setParameter("date", date).getSingleResult();
	 * 
	 * return IconMapper.toIconDtoGetOne(icon); }
	 * 
	 * //listo
	 * 
	 * @Transactional public List<IconDtoGetAll> getByLocation(Long locationId) {
	 * 
	 * Location location=locationService.getById(locationId);
	 * 
	 * location.getIcons().size();
	 * 
	 * return IconMapper.toIconDtoGetAll(location.getIcons()); }
	 */

}
