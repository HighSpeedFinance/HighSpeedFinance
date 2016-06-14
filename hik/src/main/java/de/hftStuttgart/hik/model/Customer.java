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

/**
 * The Class Customer. Functions as template for the Database and shows
 * relationships between the Class Customer and CustomerOrder and Customer and
 * PostAdress
 *
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int customerNumber;

	private String customerCompanyName;

	private String customerContactPersonFirstName;

	private String customerContactPersonLastName;

	private String customerPhoneNumber;

	private String customerEmail;

	private String customerFax;

	private String customerTitel;

	private String addedDate;

	/**
	 * The orders. Customer has a OneToMany Relatioship to CustomerOrder. CascadeType
	 * means, that all Relationships will be deleted by deleting the Customer Object
	 */
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<CustomerOrder> orders;

	/**
	 * The customer adress. Customer has a ManyToOne Relationship to PostAdress
	 */
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
	 * @param customerNumber
	 *            the customer number
	 * @param customerCompanyName
	 *            the customer company name
	 * @param customerContactPersonFirstName
	 *            the customer contact person first name
	 * @param customerContactPersonLastName
	 *            the customer contact person last name
	 * @param customerAdress
	 *            the customer adress
	 * @param customerPhoneNumber
	 *            the customer phone number
	 * @param customerEmail
	 *            the customer email
	 * @param customerFax
	 *            the customer fax
	 * @param customerTitel
	 *            the customer titel
	 * @param date
	 *            the date
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

	public String getCustomerTitel() {
		return customerTitel;
	}

	public void setCustomerTitel(String customerTitel) {
		this.customerTitel = customerTitel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerCompanyName() {
		return customerCompanyName;
	}

	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	public String getCustomerContactPersonFirstName() {
		return customerContactPersonFirstName;
	}

	public void setCustomerContactPersonFirstName(String customerContactPersonFirstName) {
		this.customerContactPersonFirstName = customerContactPersonFirstName;
	}

	public String getCustomerContactPersonLastName() {
		return customerContactPersonLastName;
	}

	public void setCustomerContactPersonLastName(String customerContactPersonLastName) {
		this.customerContactPersonLastName = customerContactPersonLastName;
	}

	public String getCustomerContactPersonName() {
		return this.customerContactPersonFirstName + " " + this.customerContactPersonLastName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public PostAdress getCustomerAdress() {
		return customerAdress;
	}

	public void setCustomerAdress(PostAdress customerAdress) {
		this.customerAdress = customerAdress;
	}

	public void setCustomerAdressStreet(String street) {
		this.customerAdress.setStreet(street);
	}

	public void setCustomerAdressHouseNumber(int number) {
		this.customerAdress.setHouseNumber(number);
	}

	public void setCustomerAdressPostIndex(int index) {
		this.customerAdress.setPostIndex(index);
	}

	public void setCustomerAdressCity(String city) {
		this.customerAdress.setCity(city);
	}

	public void setCustomerAdressCountry(String country) {
		this.customerAdress.setCountry(country);
	}

	public String getCustomerAdressStreet() {
		return this.customerAdress.getStreet();
	}

	public int getCustomerAdressHouseNumber() {
		return this.customerAdress.getHouseNumber();
	}

	public int getCustomerAdressPostIndex() {
		return this.customerAdress.getPostIndex();
	}

	public String getCustomerAdressCity() {
		return this.customerAdress.getCity();
	}

	public String getCustomerAdressCountry() {
		return this.customerAdress.getCountry();
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}

	public String getCustomerFax() {
		return customerFax;
	}

	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}

	public void addOrder(CustomerOrder order) {
		if (!orders.contains(order)) {
			orders.add(order);
		}
	}

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public String getDate() {
		return addedDate;
	}

	public void setDate(String date) {
		this.addedDate = date;
	}

	@Override
	public String toString() {
		return String.valueOf(this.customerNumber);
	}

}