package com.alkemy.ar.dto;

public class LocationDto {

	private String img;
	private String denomination;
	private int population;
	private float surface;
	private Long continentId;

	public LocationDto(String img, String denomination, int population, float surface, Long continentId) {
	
		this.img = img;
		this.denomination = denomination;
		this.population = population;
		this.surface = surface;
		this.continentId=continentId;
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
	
}
