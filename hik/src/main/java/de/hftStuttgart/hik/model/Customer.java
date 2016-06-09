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

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int customerNumber;
	private String customerCompanyName;
	private String customerContactPersonFirstName;
	private String customerContactPersonLastName;
	private String customerCotactPersonName;
	
	private int customerPhoneNumber;
	private String customerEmail;
	private int customerFax;
	private String customerTitel;
	private String addedDate;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<CustomerOrder> orders;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="postAdress_id")
	private PostAdress customerAdress;

	public Customer() {

	}

	public Customer(final int customerNumber, final String customerCompanyName,
			final String customerContactPersonFirstName, final String customerContactPersonLastName,
			final PostAdress customerAdress,
			final int customerPhoneNumber, final String customerEmail,  final int customerFax, final String customerTitel, final String date) {

		this.customerNumber = customerNumber;
		this.customerCompanyName = customerCompanyName;
		this.customerContactPersonFirstName = customerContactPersonFirstName;
		this.customerContactPersonLastName = customerContactPersonLastName;
		this.customerAdress=customerAdress;
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
	
	
	public String getCustomerContactPersonName(){
		return customerContactPersonFirstName+" "+customerContactPersonLastName;
	}

	public void setCustomerContactPersonName(String customerContactPersonName){
		this.customerContactPersonLastName=customerContactPersonLastName;
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

	
	public PostAdress getCustomerAdress() {
		return customerAdress;
	}

	public void setCustomerAdress(PostAdress customerAdress) {
		this.customerAdress = customerAdress;
	}
	public void setCustomerAdressStreet(String street){
		this.customerAdress.setStreet(street);
	}
	
	public void setCustomerAdressHouseNumber(int number){
	this.customerAdress.setHouseNumber(number);
	}
	
	public void setCustomerAdressPostIndex(int index){
		this.customerAdress.setPostIndex(index);
	}

	public void setCustomerAdressCity(String city){
		this.customerAdress.setCity(city);
	}
	
	public void setCustomerAdressCountry(String country){
		this.customerAdress.setCountry(country);
	}
	
	
	
	public String getCustomerAdressStreet(){
		return this.customerAdress.getStreet();
	}
	
	public int getCustomerAdressHouseNumber(){
		return this.customerAdress.getHouseNumber();
	}
	
	public int getCustomerAdressPostIndex(){
		return this.customerAdress.getPostIndex();
	}

	public String getCustomerAdressCity(){
		return this.customerAdress.getCity();
	}
	
	public String getCustomerAdressCountry(){
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