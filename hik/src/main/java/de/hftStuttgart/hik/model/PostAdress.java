package de.hftStuttgart.hik.model;

/**
 * @dorothea
 * ich bin mir gar nicht sicher, ob wir das überhaupt brauchen...
 * wenn, dann müssten wir den Customer verändern
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostAdress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	private int postIndex;
	private String city;
	
	public PostAdress(){
		
	}

	public PostAdress(Long id, String street, int postIndex, String city) {
		super();
		this.id = id;
		this.street = street;
		this.postIndex = postIndex;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(int postIndex) {
		this.postIndex = postIndex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	

}
