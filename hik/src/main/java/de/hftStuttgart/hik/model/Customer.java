package de.hftStuttgart.hik.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 */
@Entity
public class Customer {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** The customer number. */
	private int customerNumber;
	
	/** The customer company name. */
	private String customerCompanyName;
	
	/** The customer contact person first name. */
	private String customerContactPersonFirstName;
	
	/** The customer contact person last name. */
	private String customerContactPersonLastName;
	
	/** The customer phone number. */
	private String customerPhoneNumber;
	
	/** The customer email. */
	private String customerEmail;
	
	/** The customer fax. */
	private String customerFax;
	
	/** The customer titel. */
	private String customerTitel;
	
	/** The added date. */
	private String addedDate;

	/** The orders. */
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<CustomerOrder> orders;

	/** The customer adress. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postAdress_id")
	private PostAdress customerAdress;

	/**
	 * Instantiates a new customer.
	 */
	public Customer() {

	}

	/**
	 * Instantiates a new customer.
	 *
	 * @param customerNumber the customer number
	 * @param customerCompanyName the customer company name
	 * @param customerContactPersonFirstName the customer contact person first name
	 * @param customerContactPersonLastName the customer contact person last name
	 * @param customerAdress the customer adress
	 * @param customerPhoneNumber the customer phone number
	 * @param customerEmail the customer email
	 * @param customerFax the customer fax
	 * @param customerTitel the customer titel
	 * @param date the date
	 */
	public Customer(final int customerNumber, final String customerCompanyName,
			final String customerContactPersonFirstName, final String customerContactPersonLastName,
			final PostAdress customerAdress, final String customerPhoneNumber, final String customerEmail,
			final String customerFax, final String customerTitel, final String date) {

		this.customerNumber = customerNumber;
		this.customerCompanyName = customerCompanyName;
		this.customerContactPersonFirstName = customerContactPersonFirstName;
		this.customerContactPersonLastName = customerContactPersonLastName;
		this.customerAdress = customerAdress;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
		this.customerFax = customerFax;
		this.customerTitel = customerTitel;
		this.addedDate = date;
	}

	/**
	 * Gets the customer titel.
	 *
	 * @return the customer titel
	 */
	public String getCustomerTitel() {
		return customerTitel;
	}

	/**
	 * Sets the customer titel.
	 *
	 * @param customerTitel the new customer titel
	 */
	public void setCustomerTitel(String customerTitel) {
		this.customerTitel = customerTitel;
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
	 * Gets the customer number.
	 *
	 * @return the customer number
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * Sets the customer number.
	 *
	 * @param customerNumber the new customer number
	 */
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * Gets the customer company name.
	 *
	 * @return the customer company name
	 */
	public String getCustomerCompanyName() {
		return customerCompanyName;
	}

	/**
	 * Sets the customer company name.
	 *
	 * @param customerCompanyName the new customer company name
	 */
	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	/**
	 * Gets the customer contact person first name.
	 *
	 * @return the customer contact person first name
	 */
	public String getCustomerContactPersonFirstName() {
		return customerContactPersonFirstName;
	}

	/**
	 * Sets the customer contact person first name.
	 *
	 * @param customerContactPersonFirstName the new customer contact person first name
	 */
	public void setCustomerContactPersonFirstName(String customerContactPersonFirstName) {
		this.customerContactPersonFirstName = customerContactPersonFirstName;
	}

	/**
	 * Gets the customer contact person last name.
	 *
	 * @return the customer contact person last name
	 */
	public String getCustomerContactPersonLastName() {
		return customerContactPersonLastName;
	}

	/**
	 * Sets the customer contact person last name.
	 *
	 * @param customerContactPersonLastName the new customer contact person last name
	 */
	public void setCustomerContactPersonLastName(String customerContactPersonLastName) {
		this.customerContactPersonLastName = customerContactPersonLastName;
	}

	/**
	 * Gets the customer contact person name.
	 *
	 * @return the customer contact person name
	 */
	public String getCustomerContactPersonName() {
		return this.customerContactPersonFirstName + " " + this.customerContactPersonLastName;
	}

	/**
	 * Gets the customer phone number.
	 *
	 * @return the customer phone number
	 */
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	/**
	 * Sets the customer phone number.
	 *
	 * @param customerPhoneNumber the new customer phone number
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	/**
	 * Gets the customer email.
	 *
	 * @return the customer email
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * Sets the customer email.
	 *
	 * @param customerEmail the new customer email
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * Gets the customer adress.
	 *
	 * @return the customer adress
	 */
	public PostAdress getCustomerAdress() {
		return customerAdress;
	}

	/**
	 * Sets the customer adress.
	 *
	 * @param customerAdress the new customer adress
	 */
	public void setCustomerAdress(PostAdress customerAdress) {
		this.customerAdress = customerAdress;
	}

	/**
	 * Sets the customer adress street.
	 *
	 * @param street the new customer adress street
	 */
	public void setCustomerAdressStreet(String street) {
		this.customerAdress.setStreet(street);
	}

	/**
	 * Sets the customer adress house number.
	 *
	 * @param number the new customer adress house number
	 */
	public void setCustomerAdressHouseNumber(int number) {
		this.customerAdress.setHouseNumber(number);
	}

	/**
	 * Sets the customer adress post index.
	 *
	 * @param index the new customer adress post index
	 */
	public void setCustomerAdressPostIndex(int index) {
		this.customerAdress.setPostIndex(index);
	}

	/**
	 * Sets the customer adress city.
	 *
	 * @param city the new customer adress city
	 */
	public void setCustomerAdressCity(String city) {
		this.customerAdress.setCity(city);
	}

	/**
	 * Sets the customer adress country.
	 *
	 * @param country the new customer adress country
	 */
	public void setCustomerAdressCountry(String country) {
		this.customerAdress.setCountry(country);
	}

	/**
	 * Gets the customer adress street.
	 *
	 * @return the customer adress street
	 */
	public String getCustomerAdressStreet() {
		return this.customerAdress.getStreet();
	}

	/**
	 * Gets the customer adress house number.
	 *
	 * @return the customer adress house number
	 */
	public int getCustomerAdressHouseNumber() {
		return this.customerAdress.getHouseNumber();
	}

	/**
	 * Gets the customer adress post index.
	 *
	 * @return the customer adress post index
	 */
	public int getCustomerAdressPostIndex() {
		return this.customerAdress.getPostIndex();
	}

	/**
	 * Gets the customer adress city.
	 *
	 * @return the customer adress city
	 */
	public String getCustomerAdressCity() {
		return this.customerAdress.getCity();
	}

	/**
	 * Gets the customer adress country.
	 *
	 * @return the customer adress country
	 */
	public String getCustomerAdressCountry() {
		return this.customerAdress.getCountry();
	}

	/**
	 * Gets the added date.
	 *
	 * @return the added date
	 */
	public String getAddedDate() {
		return addedDate;
	}

	/**
	 * Sets the added date.
	 *
	 * @param addedDate the new added date
	 */
	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}

	/**
	 * Gets the customer fax.
	 *
	 * @return the customer fax
	 */
	public String getCustomerFax() {
		return customerFax;
	}

	/**
	 * Sets the customer fax.
	 *
	 * @param customerFax the new customer fax
	 */
	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}

	/**
	 * Adds the order.
	 *
	 * @param order the order
	 */
	public void addOrder(CustomerOrder order) {
		if (!orders.contains(order)) {
			orders.add(order);
		}
	}

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public List<CustomerOrder> getOrders() {
		return orders;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return addedDate;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.addedDate = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(this.customerNumber);
	}

}