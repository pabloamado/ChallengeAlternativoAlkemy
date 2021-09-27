package com.alkemy.ar.dto;

import java.util.Date;

public class IconDto {

private String img;
	
	private String denomination;
	private Date creationDate;
	private float height;
	private String story;
	private Long locationId;
	//con el location id accedo al registro location  para corroborar su existencia, sino devuelvo un error, 
	// en el caso que exista puedo actualizar 
	public IconDto(String img, String denomination, Date creationDate, float height, String story, Long locationId) {
		
		this.img = img;
		this.denomination = denomination;
		this.creationDate = creationDate;
		this.height = height;
		this.story = story;
		this.locationId = locationId;
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
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
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
	
	public Long getLocationId() {
		return locationId;
	}
	
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
}
