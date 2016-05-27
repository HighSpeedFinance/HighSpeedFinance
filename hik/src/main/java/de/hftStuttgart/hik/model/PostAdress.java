package de.hftStuttgart.hik.model;

import java.util.List;

import javax.persistence.CascadeType;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PostAdress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String street;
	private int houseNumber;
	private int postIndex;
	private String city;
	private String country;
	

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="supplierAdress")
	private List<Supplier> suppliers;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Customer> customers;
	
	public PostAdress() {

	}

	public PostAdress(final Long id, final String street,final int houseNumber, final int postIndex, final String city, final String country) {

		this.id = id;
		this.street = street;
		this.houseNumber=houseNumber;
		this.postIndex = postIndex;
		this.city = city;
		this.country=country;
	}
	
	

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	
	public void addSupplier(Supplier supplier) {
		if (!suppliers.contains(supplier)) {
			suppliers.add(supplier);
		}
	}

	public void addCustomer(Customer customer) {
		if (!customers.contains(customer)) {
			customers.add(customer);
		}
	}
	
	
}
