package com.alkemy.ar.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.exception.LocationException;
import com.alkemy.ar.mapper.IconMapper;
import com.alkemy.ar.model.Icon;
import com.alkemy.ar.repository.IconRepository;
import com.alkemy.ar.repository.LocationRepository;

@Service
public class IconService {

	@Autowired
	private IconRepository iconRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Transactional
	public IconDto save(IconDto iconDto)  throws LocationException, IllegalArgumentException, Exception {
	
		return null;
				
		}
	
	@Transactional
	public  boolean delete(Long id) {
		
		return false;
	}
	
	@Transactional
	public IconDto update (Long id,IconDto iconDto) throws EntityNotFoundException,
	IllegalArgumentException, Exception {
		
		//if(locationRepository.existsById(iconDto.getLocationId())){
			
			if(iconRepository.existsById(id)) {
				
				Icon icon=IconMapper.toIcon(iconDto);
				
				Icon recoveredIcon=iconRepository.getById(id);
				
				recoveredIcon.setImg(icon.getImg());
				recoveredIcon.setDenomination(icon.getDenomination());
				recoveredIcon.setCreationDate(icon.getCreationDate());
				recoveredIcon.setHeight(icon.getHeight());
				recoveredIcon.setStory(icon.getStory());
				
				recoveredIcon=iconRepository.save(recoveredIcon);
				
				//return ParserEntity.toDtoIcon(recoveredIcon);
				
		
			}
			
	
			return null;	
		//}

		//throw new IconException(ErrorMsg.NO_LOCATION_RELATED_TO_ICON.toString());
	}
	
	@Transactional
	public IconDto get(Long id) {
		
		return null;
	}
	
	@Transactional
	public List<IconDto> getAll(){
		
		return null;
	}
	
	@Transactional
	public IconDto getByName(String name) {
		
		return null;
	}
	
	@Transactional
	public IconDto getByDate(Date date) {
		
		return null;
	}
	
	@Transactional
	public IconDto getByLocation(Long locationId) {
	
	return null;
}
	
}
