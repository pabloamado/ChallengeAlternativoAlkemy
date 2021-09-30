package com.alkemy.ar.dto;

public class LocationDtoGetAll {

	
	private String img;
	private String denomination;
	private int population;
	
	public LocationDtoGetAll(String img, String denomination, int population) {
		
		this.img = img;
		this.denomination = denomination;
		this.population = population;
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
	
}
