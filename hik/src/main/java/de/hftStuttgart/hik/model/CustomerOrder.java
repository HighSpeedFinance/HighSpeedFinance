package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The Class CustomerOrder. Functions as template for the Database and shows
 * relationships between the Class CustomerOrder and Customer
 *
 */

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int orderNumber;

	private String date;

	private Status status;

	private int itemNumb;

	private String description;

	private double unitPrice;

	private int amount;

	private double sumPrice;

	private double tax;

	/**
	 * The customer. CustomerOrder has a ManyToOne Relatioship to Customer.
	 */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
	private Customer customer;

	/**
	 * Instantiates a new customer order.
	 */
	public CustomerOrder() {

	}

	/**
	 * Instantiates a new customer order.
	 *
	 * @param date
	 *            the date
	 * @param order_nr
	 *            the order_nr
	 * @param status
	 *            the status
	 * @param itemNumb
	 *            the item numb
	 * @param description
	 *            the description
	 * @param unitPrice
	 *            the unit price
	 * @param amount
	 *            the amount
	 * @param tax
	 *            the tax
	 */
	public CustomerOrder(final String date, final int order_nr, final Status status, final int itemNumb,
			final String description, final double unitPrice, final int amount, final double tax) {

		this.date = date;
		this.orderNumber = order_nr;
		this.status = status;
		this.itemNumb = itemNumb;
		this.description = description;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.sumPrice = (unitPrice * ((tax / 100) + 1)) * amount;
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

	public Status getStatus() {
		return status;
	}

	public String getStatusString() {
		switch (this.status.toString()) {
		case "SUCCEEDED":
			return "Bezahlt";
		case "PENDING":
			return "Offen";
		case "ENABLED":
			return "Freigegeben";
		default:
			return " ";
		}
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

	public int getCustomerNumber() {
		return customer.getCustomerNumber();
	}

	public Customer getCustomerObject() {
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
		this.sumPrice = (this.unitPrice * ((tax / 100) + 1)) * this.amount;
	}

	@Override
	public String toString() {
		return String.valueOf(Math.round(100.0 * sumPrice) / 100.0);
	}
}
