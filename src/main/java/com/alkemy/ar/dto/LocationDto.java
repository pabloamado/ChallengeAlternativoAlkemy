package com.alkemy.ar.dto;

import java.util.List;

public class LocationDto {

	private String img;
	private String denomination;
	private int population;
	private float surface;
	private Long continentId;
	private List<IconDto> icons;

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

	public List<IconDto> getIcons() {
		return icons;
	}

	public void setIcons(List<IconDto> icons) {
		this.icons = icons;
	}
	
	
}
