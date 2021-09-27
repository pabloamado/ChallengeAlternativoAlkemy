package com.alkemy.ar.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="continent")
public class Continent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cont_id")
	private Long cId;
	
	@Column(name="cont_img")
	private String img;
	
	@Column(name="cont_denomination")
	private String denomination;
	
	@OneToMany(mappedBy ="continent", cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
			CascadeType.REFRESH},fetch=FetchType.LAZY)
	private List<Location> locations;
	
	public Continent() {}
	
	public Continent(String img, String denomination) {
		
		this.img = img;
		this.denomination = denomination;
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

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public List<Location> getLocations() {
		
		if(locations==null) {
			locations=new ArrayList<>();
		}
		
		return locations;
	}

	public void setLocations(Location location) {
		
		getLocations().add(location);
		
		location.setContinent(this);
	}	
	
}
