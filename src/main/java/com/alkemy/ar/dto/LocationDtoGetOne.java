package com.alkemy.ar.dto;

import java.util.List;

import com.alkemy.ar.model.Icon;

public class LocationDtoGetOne {

	private String img;
	private String denomination;
	private int population;
	private float surface;
	private Long continentId;
	private List<Icon> icons;

	public LocationDtoGetOne() {}
	
	public LocationDtoGetOne(String img, String denomination, int population, float surface, Long continentId,List<Icon> icons) {
	
		this.img = img;
		this.denomination = denomination;
		this.population = population;
		this.surface = surface;
		this.continentId=continentId;
		this.icons=icons;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getDenomination() {
		return denomination;
	}
	
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public float getSurface() {
		return surface;
	}
	
	public void setSurface(float surface) {
		this.surface = surface;
	}

	public Long getContinentId() {
		return continentId;
	}

	public void setContinentId(Long continentId) {
		this.continentId = continentId;
	}

	public List<Icon> getIcons() {
		return icons;
	}

	public void setIcons(List<Icon> icons) {
		this.icons = icons;
	}
	
	
	
}
