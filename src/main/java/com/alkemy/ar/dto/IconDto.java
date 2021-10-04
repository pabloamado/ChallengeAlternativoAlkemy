package com.alkemy.ar.dto;

import java.time.LocalDate;

public class IconDto {

	private String img;
	private String denomination;
	private LocalDate creationDate;
	private float height;
	private String story;
	
	public IconDto() {}
	
	public IconDto(String img, String denomination, LocalDate creationDate, float height, String story) {
		
		this.img = img;
		this.denomination = denomination;
		this.creationDate = creationDate;
		this.height = height;
		this.story = story;
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
	
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	public String getStory() {
		return story;
	}
	
	public void setStory(String story) {
		this.story = story;
	}
		
}
