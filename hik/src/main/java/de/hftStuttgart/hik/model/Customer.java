package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Customer() {
		
	}

	public Customer(int customerNumber, String customerCompanyName, String customerContactPersonFirstName,
			String customerContactPersonLastName, String customerStreet, int customerPostalCode, String customerCity,
			int customerPhoneNumber, String customerEmail, int customerHouseNumber, String customerCountry,
			int customerFax, String customerTitel) {
		super();
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
	
}