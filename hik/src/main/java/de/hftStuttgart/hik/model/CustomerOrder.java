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
	private int date;
	private int supId;
	private String status;
	private int itemNumb;
	private String description;
	private double unitPrice;
	private int amount;
	private double sumPrice;
	
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Customer.class)
	private Customer customer;

	public CustomerOrder() {

	}

	public CustomerOrder(final int date, final int supId, final String status, final int itemNumb,
			final String description, final double unitPrice, final int amount, final double sum) {

		this.date = date;
		this.supId = supId;
		this.status = status;
		this.itemNumb = itemNumb;
		this.description = description;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.sumPrice = sum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getSupId() {
		return supId;
	}

	public void setSupId(int supId) {
		this.supId = supId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public double getSum() {
		return sumPrice;
	}

	public void setSum(double sum) {
		this.sumPrice = sum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
