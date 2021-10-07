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
	
	@Column(name="continent_id")
	private Long continentId;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
			CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name="continent_id", insertable=false, updatable=false)
	private Continent continent;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
			CascadeType.REFRESH})
	/*@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, 
		org.hibernate.annotations.CascadeType.PERSIST})*/
	@JoinTable(
	        name = "locations_icons", 
	        joinColumns = { @JoinColumn(name = "location_loc_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "icon_icon_id") } )
	private List<Icon> icons=new ArrayList<>();
	
	public Location() {}

	public Location(String img, String denomination, int population, float surface,Long continentId) {
		
		this.img = img;
		this.denomination = denomination;
		this.population = population;
		this.surface = surface;
		this.continentId=continentId;
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
	
	public Long getContinentId() {
		return continentId;
	}

	public void setContinentId(Long continentId) {
		this.continentId = continentId;
	}
	
	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public void setIcons(List<Icon> icons) {
		this.icons = icons;
	}

	public List<Icon> getIcons() {
			
		return icons;
	}

	
	public void addIcon(Icon icon) {
		
		this.icons.add(icon);
			
	}
	
	
	public void removeIcon(Icon icon) {
		
		this.icons.remove(icon);
		
	}
	
}
