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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="loc_id")
	private Long lId;
	
	@Column(name="loc_img")
	private String img;
	
	@Column(name="loc_denomination")
	private String denomination;
	
	@Column(name="loc_population")
	private int population;
	
	@Column(name="loc_surface")
	private float surface;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
			CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name="continent_id")
	private Continent continent;
	
	
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
			CascadeType.REFRESH},fetch=FetchType.LAZY )
	@JoinTable(
	        name = "locations_icons", 
	        joinColumns = { @JoinColumn(name = "location_loc_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "icon_icon_id") } )
	private List<Icon> icons;

	public Location() {}

	public Location(String img, String denomination, int population, float surface) {
		
		this.img = img;
		this.denomination = denomination;
		this.population = population;
		this.surface = surface;
	}

	public Long getlId() {
		return lId;
	}

	public void setlId(Long lId) {
		this.lId = lId;
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

	public List<Icon> getIcons() {
		
		if(icons==null) {
			icons=new ArrayList<>();
		}
		return icons;
	}

	public void addIcon(Icon icon) {

		getIcons().add(icon);
		
		icon.addLocation(this);
	}
	
	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}
	
}
