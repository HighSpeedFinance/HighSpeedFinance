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
import javax.persistence.OneToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Supplier.
 */
@Entity
public class Supplier {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** The supplier number. */
	private int supplierNumber;
	
	/** The supplier company name. */
	private String supplierCompanyName;
	
	/** The supplier contact person first name. */
	private String supplierContactPersonFirstName;
	
	/** The supplier contact person last name. */
	private String supplierContactPersonLastName;
	
	/** The supplier phone number. */
	private String supplierPhoneNumber;
	
	/** The supplier email. */
	private String supplierEmail;
	
	/** The supplier fax. */
	private String supplierFax;
	
	/** The added date. */
	private String addedDate;
	
	/** The supplier payment details. */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="paymentDetails_id")
	private PaymentDetails supplierPaymentDetails;

	/** The orders. */
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<SupplierOrder> orders;
	
	/** The supplier adress. */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="postAdress_id")
	private PostAdress supplierAdress;
	
	/**
	 * Instantiates a new supplier.
	 */
	public Supplier() {
	}

	/**
	 * Instantiates a new supplier.
	 *
	 * @param supplierNumber the supplier number
	 * @param supplierCompanyName the supplier company name
	 * @param supplierContactPersonFirstName the supplier contact person first name
	 * @param supplierContactPersonLastName the supplier contact person last name
	 * @param supplierAdress the supplier adress
	 * @param supplierPhoneNumber the supplier phone number
	 * @param supplierEmail the supplier email
	 * @param supplierFax the supplier fax
	 * @param date the date
	 * @param supplierPaymentDetails the supplier payment details
	 */
	public Supplier(final int supplierNumber, final String supplierCompanyName,
			final String supplierContactPersonFirstName, final String supplierContactPersonLastName,
			final PostAdress supplierAdress, final String supplierPhoneNumber, final String supplierEmail,
			final String supplierFax, final String date, final PaymentDetails supplierPaymentDetails) {

		this.supplierNumber = supplierNumber;
		this.supplierCompanyName = supplierCompanyName;
		this.supplierContactPersonFirstName = supplierContactPersonFirstName;
		this.supplierContactPersonLastName = supplierContactPersonLastName;
		this.supplierAdress = supplierAdress;
		this.supplierPhoneNumber = supplierPhoneNumber;
		this.supplierEmail = supplierEmail;
		this.supplierFax = supplierFax;
		this.addedDate = date;
		this.supplierPaymentDetails = supplierPaymentDetails;
	}

	/**
	 * Gets the supplier adress.
	 *
	 * @return the supplier adress
	 */
	public PostAdress getSupplierAdress() {
		return supplierAdress;

}
	
	/**
	 * Sets the supplier adress street.
	 *
	 * @param street the new supplier adress street
	 */
	public void setSupplierAdressStreet(String street){
		this.supplierAdress.setStreet(street);
	}
	
	/**
	 * Sets the supplier adress house number.
	 *
	 * @param number the new supplier adress house number
	 */
	public void setSupplierAdressHouseNumber(int number){
	this.supplierAdress.setHouseNumber(number);
	}
	
	/**
	 * Sets the supplier adress post index.
	 *
	 * @param index the new supplier adress post index
	 */
	public void setSupplierAdressPostIndex(int index){
		this.supplierAdress.setPostIndex(index);
	}

	/**
	 * Sets the supplier adress city.
	 *
	 * @param city the new supplier adress city
	 */
	public void setSupplierAdressCity(String city){
		this.supplierAdress.setCity(city);
	}
	
	/**
	 * Sets the supplier adress country.
	 *
	 * @param country the new supplier adress country
	 */
	public void setSupplierAdressCountry(String country){
		this.supplierAdress.setCountry(country);
	}
	
	/**
	 * Sets the supplier adress.
	 *
	 * @param supplierAdress the new supplier adress
	 */
	public void setSupplierAdress(PostAdress supplierAdress) {
		this.supplierAdress = supplierAdress;
	}
	
	/**
	 * Gets the supplier adress street.
	 *
	 * @return the supplier adress street
	 */
	public String getSupplierAdressStreet(){
		return this.supplierAdress.getStreet();
	}
	
	/**
	 * Gets the supplier adress house number.
	 *
	 * @return the supplier adress house number
	 */
	public int getSupplierAdressHouseNumber(){
		return this.supplierAdress.getHouseNumber();
	}
	
	/**
	 * Gets the supplier adress post index.
	 *
	 * @return the supplier adress post index
	 */
	public int getSupplierAdressPostIndex(){
		return this.supplierAdress.getPostIndex();
	}

	/**
	 * Gets the supplier adress city.
	 *
	 * @return the supplier adress city
	 */
	public String getSupplierAdressCity(){
		return this.supplierAdress.getCity();
	}
	
	/**
	 * Gets the supplier adress country.
	 *
	 * @return the supplier adress country
	 */
	public String getSupplierAdressCountry(){
		return this.supplierAdress.getCountry();
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
	 * Gets the supplier payment details.
	 *
	 * @return the supplier payment details
	 */
	public PaymentDetails getSupplierPaymentDetails() {
		return supplierPaymentDetails;
	}

	/**
	 * Sets the supplier payment details.
	 *
	 * @param supplierPaymentDetails the new supplier payment details
	 */
	public void setSupplierPaymentDetails(PaymentDetails supplierPaymentDetails) {
		this.supplierPaymentDetails = supplierPaymentDetails;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
	public void setOrders(List<SupplierOrder> orders) {
		this.orders = orders;
	}

	/**
	 * Gets the supplier number.
	 *
	 * @return the supplier number
	 */
	public int getSupplierNumber() {
		return supplierNumber;
	}

	/**
	 * Sets the supplier number.
	 *
	 * @param supplierNumber the new supplier number
	 */
	public void setSupplierNumber(int supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	/**
	 * Gets the supplier company name.
	 *
	 * @return the supplier company name
	 */
	public String getSupplierCompanyName() {
		return supplierCompanyName;
	}

	/**
	 * Sets the supplier company name.
	 *
	 * @param supplierCompanyName the new supplier company name
	 */
	public void setSupplierCompanyName(String supplierCompanyName) {
		this.supplierCompanyName = supplierCompanyName;
	}

	/**
	 * Gets the supplier contact person first name.
	 *
	 * @return the supplier contact person first name
	 */
	public String getSupplierContactPersonFirstName() {
		return supplierContactPersonFirstName;
	}

	/**
	 * Sets the supplier contact person first name.
	 *
	 * @param supplierContactPersonFirstName the new supplier contact person first name
	 */
	public void setSupplierContactPersonFirstName(String supplierContactPersonFirstName) {
		this.supplierContactPersonFirstName = supplierContactPersonFirstName;
	}

	/**
	 * Gets the supplier contact person last name.
	 *
	 * @return the supplier contact person last name
	 */
	public String getSupplierContactPersonLastName() {
		return supplierContactPersonLastName;
	}

	/**
	 * Sets the supplier contact person last name.
	 *
	 * @param supplierContactPersonLastName the new supplier contact person last name
	 */
	public void setSupplierContactPersonLastName(String supplierContactPersonLastName) {
		this.supplierContactPersonLastName = supplierContactPersonLastName;
	}

	/**
	 * Gets the supplier phone number.
	 *
	 * @return the supplier phone number
	 */
	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	/**
	 * Sets the supplier phone number.
	 *
	 * @param supplierPhoneNumber the new supplier phone number
	 */
	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	/**
	 * Gets the supplier email.
	 *
	 * @return the supplier email
	 */
	public String getSupplierEmail() {
		return supplierEmail;
	}

	/**
	 * Sets the supplier email.
	 *
	 * @param supplierEmail the new supplier email
	 */
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	/**
	 * Gets the supplier fax.
	 *
	 * @return the supplier fax
	 */
	public String getSupplierFax() {
		return supplierFax;
	}

	/**
	 * Sets the supplier fax.
	 *
	 * @param supplierFax the new supplier fax
	 */
	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
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

	/**
	 * Adds the order.
	 *
	 * @param order the order
	 */
	public void addOrder(SupplierOrder order) {
		if (!orders.contains(order)) {
			orders.add(order);
		}
	}

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public List<SupplierOrder> getOrders() {
		return orders;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return supplierCompanyName;
	}

}
