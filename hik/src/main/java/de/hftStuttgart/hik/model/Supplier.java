package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int supplierNumber;
	private String supplierCompanyName;
	private String supplierContactPersonFirstName;
	private String supplierContactPersonLastName;
	private String supplierStreet;
	private int supplierPostalCode;
	private String supplierCity;
	private int supplierPhoneNumber;
	private String supplierEmail;
	private int supplierHouseNumber;
	private String supplierCountry;
	private int supplierFax;

	public Supplier() {
	}

	public Supplier(int supplierNumber, String supplierCompanyName, String supplierContactPersonFirstName,
			String supplierContactPersonLastName, String supplierStreet, int supplierPostalCode, String supplierCity,
			int supplierPhoneNumber, String supplierEmail, int supplierHouseNumber, String supplierCountry,
			int supplierFax) {
		super();
		this.supplierNumber = supplierNumber;
		this.supplierCompanyName = supplierCompanyName;
		this.supplierContactPersonFirstName = supplierContactPersonFirstName;
		this.supplierContactPersonLastName = supplierContactPersonLastName;
		this.supplierStreet = supplierStreet;
		this.supplierPostalCode = supplierPostalCode;
		this.supplierCity = supplierCity;
		this.supplierPhoneNumber = supplierPhoneNumber;
		this.supplierEmail = supplierEmail;
		this.supplierHouseNumber = supplierHouseNumber;
		this.supplierCountry = supplierCountry;
		this.supplierFax = supplierFax;
	}

	public int getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(int supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierCompanyName() {
		return supplierCompanyName;
	}

	public void setSupplierCompanyName(String supplierCompanyName) {
		this.supplierCompanyName = supplierCompanyName;
	}

	public String getSupplierContactPersonFirstName() {
		return supplierContactPersonFirstName;
	}

	public void setSupplierContactPersonFirstName(String supplierContactPersonFirstName) {
		this.supplierContactPersonFirstName = supplierContactPersonFirstName;
	}

	public String getSupplierContactPersonLastName() {
		return supplierContactPersonLastName;
	}

	public void setSupplierContactPersonLastName(String supplierContactPersonLastName) {
		this.supplierContactPersonLastName = supplierContactPersonLastName;
	}

	public String getSupplierStreet() {
		return supplierStreet;
	}

	public void setSupplierStreet(String supplierStreet) {
		this.supplierStreet = supplierStreet;
	}

	public int getSupplierPostalCode() {
		return supplierPostalCode;
	}

	public void setSupplierPostalCode(int supplierPostalCode) {
		this.supplierPostalCode = supplierPostalCode;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

	public int getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	public void setSupplierPhoneNumber(int supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public int getSupplierHouseNumber() {
		return supplierHouseNumber;
	}

	public void setSupplierHouseNumber(int supplierHouseNumber) {
		this.supplierHouseNumber = supplierHouseNumber;
	}

	public String getSupplierCountry() {
		return supplierCountry;
	}

	public void setSupplierCountry(String supplierCountry) {
		this.supplierCountry = supplierCountry;
	}

	public int getSupplierFax() {
		return supplierFax;
	}

	public void setSupplierFax(int supplierFax) {
		this.supplierFax = supplierFax;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
