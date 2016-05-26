package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int orderNumber;
	private String date;
	private int supId;
	private Status status;
	private int itemNumb;
	private String description;
	private double unitPrice;
	private int amount;
	private double sumPrice;
	private double tax;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Customer.class)
	private Customer customer;

	public CustomerOrder() {

	}

	public CustomerOrder(final String date, final int order_nr, final int supId, final Status status, final int itemNumb,
			final String description, final double unitPrice, final int amount, final double tax) {

		this.date = date;
		this.orderNumber = order_nr;
		this.supId = supId;
		this.status = status;
		this.itemNumb = itemNumb;
		this.description = description;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.sumPrice = (unitPrice * ((tax/100)+1)) * amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSupId() {
		return supId;
	}

	public void setSupId(int supId) {
		this.supId = supId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getItemNumb() {
		return itemNumb;
	}

	public void setItemNumb(int itemNumb) {
		this.itemNumb = itemNumb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getCustomer() {
		return customer.getId();
	}

	public Customer getCustomerObject(){
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getSumPrice() {
		return Math.round(100.0 * sumPrice) / 100.0;
	}

	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
		this.sumPrice = (this.unitPrice * ((tax/100)+1)) * this.amount;
	}

	@Override
	public String toString() {
		return String.valueOf(Math.round(100.0 * sumPrice) / 100.0);
	}
	
	
}
