package com.alkemy.ar.model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="icon")
public class Icon {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="icon_id")
	private Long iId;
	
	@Column(name="icon_img")
	private String img;
	
	@Column(name="icon_denomination")
	private String denomination;
	
	@Column(name="icon_creation_date")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private LocalDate creationDate;
	
	@Column(name="icon_height")
	private float height;
	
	@Column(name="icon_story")
	private String story;
	
	@ManyToMany(mappedBy = "icons",fetch = FetchType.LAZY)
	private List<Location> locations;
	
	public Icon() {}
	
	public Icon(String img, String denomination, LocalDate creationDate, float height, String story) {
		
		this.img = img;
		this.denomination = denomination;
		this.creationDate = creationDate;
		this.height = height;
		this.story = story;
	}

	public Long getiId() {
		return iId;
	}

	public void setiId(Long iId) {
		this.iId = iId;
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

	
	public List<Location> getLocations() {
		
		if(locations==null) {
			
			locations=new ArrayList<>();
			
		}
		return locations;
	}

	public void addLocation(Location location) {
		
		getLocations().add(location);
		
	}
	
	public void removeLocation(Location location) {
		
		getLocations().remove(location);
	}
	
	public boolean equals(Object object) {
		
		if(object==null) return false;
		
		if(getClass()!=object.getClass()) return false;
		
		final Icon otherLocation=(Icon) object;
		
		return this.iId==otherLocation.getiId();
	}
		
}
