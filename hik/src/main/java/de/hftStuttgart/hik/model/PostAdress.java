package de.hftStuttgart.hik.model;

import java.util.List;

import javax.persistence.CascadeType;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class PostAdress.
 */
@Entity
public class PostAdress {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** The street. */
	private String street;
	
	/** The house number. */
	private int houseNumber;
	
	/** The post index. */
	private int postIndex;
	
	/** The city. */
	private String city;
	
	/** The country. */
	private String country;
	

	/** The suppliers. */
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="supplierAdress")
	private List<Supplier> suppliers;

	/** The customers. */
	@OneToMany(cascade = CascadeType.ALL)
	private List<Customer> customers;
	
	/**
	 * Instantiates a new post adress.
	 */
	public PostAdress() {

	}

	/**
	 * Instantiates a new post adress.
	 *
	 * @param street the street
	 * @param houseNumber the house number
	 * @param postIndex the post index
	 * @param city the city
	 * @param country the country
	 */
	public PostAdress( final String street,final int houseNumber, final int postIndex, final String city, final String country) {

		this.street = street;
		this.houseNumber=houseNumber;
		this.postIndex = postIndex;
		this.city = city;
		this.country=country;
	}
	
	

	/**
	 * Gets the house number.
	 *
	 * @return the house number
	 */
	public int getHouseNumber() {
		return houseNumber;
	}

	/**
	 * Sets the house number.
	 *
	 * @param houseNumber the new house number
	 */
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the post index.
	 *
	 * @return the post index
	 */
	public int getPostIndex() {
		return postIndex;
	}

	/**
	 * Sets the post index.
	 *
	 * @param postIndex the new post index
	 */
	public void setPostIndex(int postIndex) {
		this.postIndex = postIndex;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Adds the supplier.
	 *
	 * @param supplier the supplier
	 */
	public void addSupplier(Supplier supplier) {
		if (!suppliers.contains(supplier)) {
			suppliers.add(supplier);
		}
	}

	/**
	 * Adds the customer.
	 *
	 * @param customer the customer
	 */
	public void addCustomer(Customer customer) {
		if (!customers.contains(customer)) {
			customers.add(customer);
		}
	}
	
	
}
