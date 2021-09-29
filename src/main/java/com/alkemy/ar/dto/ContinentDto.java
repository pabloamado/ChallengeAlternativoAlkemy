package com.alkemy.ar.dto;

public class ContinentDto {

	private String img;
	private String denomination;
	
	public ContinentDto(String img, String denomination) {
		
		this.img = img;
		this.denomination = denomination;
		
	}

	public ContinentDto() {}

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
	
}
