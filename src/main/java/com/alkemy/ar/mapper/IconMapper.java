package com.alkemy.ar.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.alkemy.ar.dto.IconDto;
import com.alkemy.ar.model.Icon;

public class IconMapper {
	
	
	public static Icon toIcon(IconDto iconDto) {

		Icon icon = new Icon();
		
		setIconValues(icon, iconDto);	

		return icon;
	}

	//listo
	public static List<Icon> toIconList(List<IconDto> iconDtos) {

		List<Icon> icons = new ArrayList<>();

		for (IconDto iconDto : iconDtos) {

			Icon icon = new Icon();
			
			setIconValues(icon, iconDto);		
			
			icons.add(icon);
		}

		return icons;
	}

	
	public static IconDto toDtoIcon(Icon icon) {

		IconDto iconDto = new IconDto();

		if (icon != null) {

			setIconDtoValues(iconDto, icon);
		}

		return iconDto;
	}
	
	//listo
	public static List<IconDto> toDtoIconList(List<Icon> icons) {

		List<IconDto> iconDtos = new ArrayList<>();

		if (icons != null) {

			for (Icon icon : icons) {

				IconDto dto = new IconDto();
				
				setIconDtoValues(dto, icon);

				iconDtos.add(dto);
			}

		}

		return iconDtos;
	}

	private static void setIconDtoValues(IconDto iconDto,Icon icon) {
		
		iconDto.setImg(icon.getImg());
		iconDto.setDenomination(icon.getDenomination());
		DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		iconDto.setCreationDate(LocalDate.parse(icon.getCreationDate().toString(), dateFormatter));
		iconDto.setHeight(icon.getHeight());
		iconDto.setStory(icon.getStory());
	}
	
	private static void setIconValues(Icon icon, IconDto iconDto) {
		icon.setImg(iconDto.getImg());		
		icon.setDenomination(iconDto.getDenomination());
		DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		icon.setCreationDate(LocalDate.parse(iconDto.getCreationDate().toString(), dateFormatter));
		icon.setHeight(iconDto.getHeight());		
		icon.setStory(iconDto.getStory());
	}
	
	
}
