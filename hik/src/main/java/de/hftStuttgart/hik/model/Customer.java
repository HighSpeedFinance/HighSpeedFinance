package de.hftStuttgart.hik.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int customerNumber;
	private String customerCompanyName;
	private String customerContactPersonFirstName;
	private String customerContactPersonLastName;
	private String customerStreet;
	private int customerPostalCode;
	private String customerCity;
	private int customerPhoneNumber;
	private String customerEmail;
	private int customerHouseNumber;
	private String customerCountry;
	private int customerFax;
	private String customerTitel;
	private String addedDate;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<CustomerOrder> orders;

	public Customer() {

	}

	public Customer(final int customerNumber, final String customerCompanyName,
			final String customerContactPersonFirstName, final String customerContactPersonLastName,
			final String customerStreet, final int customerPostalCode, final String customerCity,
			final int customerPhoneNumber, final String customerEmail, final int customerHouseNumber,
			final String customerCountry, final int customerFax, final String customerTitel, final String date) {

		this.customerNumber = customerNumber;
		this.customerCompanyName = customerCompanyName;
		this.customerContactPersonFirstName = customerContactPersonFirstName;
		this.customerContactPersonLastName = customerContactPersonLastName;
		this.customerStreet = customerStreet;
		this.customerPostalCode = customerPostalCode;
		this.customerCity = customerCity;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
		this.customerHouseNumber = customerHouseNumber;
		this.customerCountry = customerCountry;
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

	public String getCustomerStreet() {
		return customerStreet;
	}

	public void setCustomerStreet(String customerStreet) {
		this.customerStreet = customerStreet;
	}

	public int getCustomerPostalCode() {
		return customerPostalCode;
	}

	public void setCustomerPostalCode(int customerPostalCode) {
		this.customerPostalCode = customerPostalCode;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public int getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(int customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getCustomerHouseNumber() {
		return customerHouseNumber;
	}

	public void setCustomerHouseNumber(int customerHouseNumber) {
		this.customerHouseNumber = customerHouseNumber;
	}

	public String getCustomerCountry() {
		return customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public int getCustomerFax() {
		return customerFax;
	}

	public void setCustomerFax(int customerFax) {
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
		return String.valueOf(this.id);
	}
	
	
}