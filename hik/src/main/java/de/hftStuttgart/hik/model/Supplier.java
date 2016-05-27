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

@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int supplierNumber;
	private String supplierCompanyName;
	private String supplierContactPersonFirstName;
	private String supplierContactPersonLastName;
	
	private int supplierPhoneNumber;
	private String supplierEmail;
	private int supplierFax;
	private String addedDate;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="paymentDetails_id")
	private PaymentDetails supplierPaymentDetails;

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<SupplierOrder> orders;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="postAdress_id")
	private PostAdress supplierAdress;
	
	public Supplier() {
	}

	public Supplier(final int supplierNumber, final String supplierCompanyName,
			final String supplierContactPersonFirstName, final String supplierContactPersonLastName,
			final PostAdress supplierAdress, final int supplierPhoneNumber, final String supplierEmail,
			final int supplierFax, final String date, final PaymentDetails supplierPaymentDetails) {

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

	public PostAdress getSupplierAdress() {
		return supplierAdress;

}
	public void setSupplierAdressStreet(String street){
		this.supplierAdress.setStreet(street);
	}
	
	public void setSupplierAdressHouseNumber(int number){
	this.supplierAdress.setHouseNumber(number);
	}
	
	public void setSupplierAdressPostIndex(int index){
		this.supplierAdress.setPostIndex(index);
	}

	public void setSupplierAdressCity(String city){
		this.supplierAdress.setCity(city);
	}
	
	public void setSupplierAdressCountry(String country){
		this.supplierAdress.setCountry(country);
	}
	
	public void setSupplierAdress(PostAdress supplierAdress) {
		this.supplierAdress = supplierAdress;
	}
	
	public String getSupplierAdressStreet(){
		return this.supplierAdress.getStreet();
	}
	
	public int getSupplierAdressHouseNumber(){
		return this.supplierAdress.getHouseNumber();
	}
	
	public int getSupplierAdressPostIndex(){
		return this.supplierAdress.getPostIndex();
	}

	public String getSupplierAdressCity(){
		return this.supplierAdress.getCity();
	}
	
	public String getSupplierAdressCountry(){
		return this.supplierAdress.getCountry();
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public PaymentDetails getSupplierPaymentDetails() {
		return supplierPaymentDetails;
	}

	public void setSupplierPaymentDetails(PaymentDetails supplierPaymentDetails) {
		this.supplierPaymentDetails = supplierPaymentDetails;
	}

	public void setOrders(List<SupplierOrder> orders) {
		this.orders = orders;
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

	public String getDate() {
		return addedDate;
	}

	public void setDate(String date) {
		this.addedDate = date;
	}

	public void addOrder(SupplierOrder order) {
		if (!orders.contains(order)) {
			orders.add(order);
		}
	}

	public List<SupplierOrder> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		return supplierCompanyName;
	}

}
